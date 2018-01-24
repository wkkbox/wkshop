package com.wk.wkshop.service.impl;

import com.wk.wkshop.dao.SearchItemDao;
import com.wk.wkshop.dao.TbItemSearchCustomMapper;
import com.wk.wkshop.pojo.vo.TbItemSearchCustom;
import com.wk.wkshop.pojo.vo.TbSearchItemResult;
import com.wk.wkshop.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SearchItemDao searchItemDao;

    @Autowired
    private TbItemSearchCustomMapper itemSearchCustomMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public boolean importAllItems() throws Exception {
        //采集数据：查询数据到List
        List<TbItemSearchCustom> list = itemSearchCustomMapper.listSearchItems();
        //创建索引库  documentList
        //遍历
        for (TbItemSearchCustom tbItemSearchCustom : list) {
            //创建solr的文档对象
            //先形成文档对象，再形成文档域
            //文档对象Field的name与scheme.xml配置的内容保持一致
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", tbItemSearchCustom.getId());
            document.addField("item_title", tbItemSearchCustom.getTitle());
            document.addField("item_sell_point", tbItemSearchCustom.getSellPoint());
            document.addField("item_price", tbItemSearchCustom.getPrice());
            document.addField("item_image", tbItemSearchCustom.getImage());
            document.addField("item_category_name", tbItemSearchCustom.getCatName());

            //写入索引库
            solrServer.add(document);

        }

        //提交
        solrServer.commit();

        return true;
    }

    @Override
    public TbSearchItemResult search(String keyword, Integer page, int rows) throws Exception{

        //创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(keyword);
        //设置分页条件
        if (page <=0 ) page = 1;
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        //设置默认搜索域
        query.set("df","item_keywords");
        //开启高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        //调用dao执行查询
        TbSearchItemResult result = searchItemDao.search(query);

        //计算总页数
        long recordCount = result.getRecordCount();
        System.out.println("总记录="+recordCount);
        result.setTotalPages((int) ((recordCount+rows-1)/rows));
        return result;
    }
}
