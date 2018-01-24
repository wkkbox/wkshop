package com.wk.wkshop.search.message;

import com.wk.wkshop.dao.TbItemSearchCustomMapper;
import com.wk.wkshop.pojo.vo.TbItemSearchCustom;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * 1 接收消息
 * 2 按照ID查询商品
 * 3 添加到索引库
 */
public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private TbItemSearchCustomMapper tbItemSearchCustomMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {
        try {
            //接收消息，并且获取到商品ID
            Long itemId = Long.valueOf(((TextMessage)message).getText());
            //按照商品ID查询出指定商品
            TbItemSearchCustom tbItemSearchCustom = tbItemSearchCustomMapper.getById(itemId);
            //添加到索引库
            //添加到document
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", tbItemSearchCustom.getId());
            document.addField("item_title", tbItemSearchCustom.getTitle());
            document.addField("item_sell_point", tbItemSearchCustom.getSellPoint());
            document.addField("item_price", tbItemSearchCustom.getPrice());
            document.addField("item_image", tbItemSearchCustom.getImage());
            document.addField("item_category_name", tbItemSearchCustom.getCatName());
            //添加到索引库
            solrServer.add(document);
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
