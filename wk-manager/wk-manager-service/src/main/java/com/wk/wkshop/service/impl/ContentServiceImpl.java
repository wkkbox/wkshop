package com.wk.wkshop.service.impl;

import com.wk.wkshop.common.jedis.JedisClient;
import com.wk.wkshop.common.util.JsonUtils;
import com.wk.wkshop.dao.TbContentMapper;
import com.wk.wkshop.pojo.po.TbContent;
import com.wk.wkshop.pojo.po.TbContentExample;
import com.wk.wkshop.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<TbContent> listContentByCid(Long cid) {
        List<TbContent> list = null;
        //查询缓存部分
        try {
            String json = jedisClient.hget("CONTENT_LIST", String.valueOf(cid));
            if (StringUtils.isNotBlank(json)) {//判断非null和非空字符串""
                System.out.println("从缓存服务器拿数据");
                return JsonUtils.jsonToList(json, TbContent.class);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        //主体业务部分
        //创建模板
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        System.out.println("从数据库查数据");
        list = contentMapper.selectByExample(example);
        System.out.println(JsonUtils.objectToJson(list));
        //存入缓存部分
        try {
            jedisClient.hset("CONTENT_LIST", String.valueOf(cid), JsonUtils.objectToJson(list));
            System.out.println("存数据到缓存服务器");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return list;
    }
}
