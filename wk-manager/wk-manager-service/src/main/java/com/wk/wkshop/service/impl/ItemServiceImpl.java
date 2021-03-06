package com.wk.wkshop.service.impl;

import com.wk.wkshop.common.util.IDUtils;
import com.wk.wkshop.dao.TbItemCustomMapper;
import com.wk.wkshop.dao.TbItemDescMapper;
import com.wk.wkshop.dao.TbItemMapper;
import com.wk.wkshop.dao.TbItemParamItemMapper;
import com.wk.wkshop.pojo.po.TbItem;
import com.wk.wkshop.pojo.po.TbItemDesc;
import com.wk.wkshop.pojo.po.TbItemExample;
import com.wk.wkshop.pojo.po.TbItemParamItem;
import com.wk.wkshop.pojo.vo.TbItemCustom;
import com.wk.wkshop.pojo.vo.TbItemQuery;
import com.wk.wkshop.service.ItemService;
import com.wk.wkshpo.common.dto.Order;
import com.wk.wkshpo.common.dto.Page;
import com.wk.wkshpo.common.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemCustomMapper tbItemCustomMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItem getById(Long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("order", order);
            map.put("query", query);
            //1 创建一个响应参数实体类
            result = new Result<>();
            Integer total = tbItemCustomMapper.countItems(map);
            List<TbItemCustom> list = tbItemCustomMapper.listItemsByPage(map);
            //2 对total进行设值(符合条件的总记录数)
            result.setTotal(total);
            //3 对rows进行设值(指定页码显示的记录集合)
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer removeItemsByIds(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte) 3);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return tbItemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer downItemsByIds(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte) 2);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return tbItemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer upItemsByIds(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte) 1);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        //System.out.println(1/0);
        return tbItemMapper.updateByExampleSelective(record, example);
    }

    //加上注解@Transactional之后，这个方法就变成了事务方法
    //并不是事务方法越多越好，查询方法不需要添加为事务方法
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Long saveItem(TbItem tbItem, String content, String paramData) throws Exception {
        Integer i = 0;
        //这个方法中需要处理三张表格tb_item，tb_item_desc，tb_item_param_item
        //调用工具类生成商品的ID
        //处理tb_item
        Long id = IDUtils.getItemId();
        tbItem.setId(id);
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        i = tbItemMapper.insert(tbItem);
        //处理tb_item_desc
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setItemId(id);
        /*System.out.println("异常了");
        System.out.println(1 / 0);*/
        tbItemDesc.setItemDesc(content);
        i += tbItemDescMapper.insert(tbItemDesc);
        //处理tb_item_param_item
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setUpdated(new Date());
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(paramData);
        i += tbItemParamItemMapper.insertSelective(tbItemParamItem);

        return id;
    }

    /*@Override
    public List<TbItem> listItems() {
        List<TbItem> list = null;
        try {
            list = tbItemMapper.selectByExample(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }*/
}
