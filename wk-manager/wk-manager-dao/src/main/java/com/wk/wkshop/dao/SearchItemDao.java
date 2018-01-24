package com.wk.wkshop.dao;

import com.wk.wkshop.pojo.vo.TbItemSearchCustom;
import com.wk.wkshop.pojo.vo.TbSearchItemResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchItemDao {

    @Autowired
    private SolrServer solrServer;

    /**
     * 根据查询条件查询索引库
     */
    public TbSearchItemResult search(SolrQuery query) throws Exception {
        //根据query查询索引库
        QueryResponse queryResponse = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //取查询结果总记录数
        Long numFound = solrDocumentList.getNumFound();
        TbSearchItemResult result = new TbSearchItemResult();
        result.setRecordCount(numFound);
        //取商品列表，需要取高亮显示
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        List<TbItemSearchCustom> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            TbItemSearchCustom tbItemSearchCustom = new TbItemSearchCustom();
            tbItemSearchCustom.setId((String) solrDocument.get("id"));
            tbItemSearchCustom.setCatName((String) solrDocument.get("item_category_name"));
            tbItemSearchCustom.setImage((String) solrDocument.get("item_image"));
            tbItemSearchCustom.setPrice((Long) solrDocument.get("item_price"));
            tbItemSearchCustom.setSellPoint((String) solrDocument.get("item_sell_point"));
            //取高亮显示
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            if (list != null && list.size() > 0) {
                tbItemSearchCustom.setTitle(list.get(0));
            } else {
                tbItemSearchCustom.setTitle((String) solrDocument.get("item_title"));
            }
            //添加到商品列表
            itemList.add(tbItemSearchCustom);
        }
        result.setItemList(itemList);
        //返回结果
        return result;
    }
}
