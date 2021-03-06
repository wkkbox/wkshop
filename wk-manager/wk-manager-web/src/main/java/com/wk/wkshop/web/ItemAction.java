package com.wk.wkshop.web;

import com.wk.wkshop.pojo.po.TbItem;
import com.wk.wkshop.pojo.vo.TbItemCustom;
import com.wk.wkshop.pojo.vo.TbItemQuery;
import com.wk.wkshop.service.ItemService;
import com.wk.wkshpo.common.dto.MessageResult;
import com.wk.wkshpo.common.dto.Order;
import com.wk.wkshpo.common.dto.Page;
import com.wk.wkshpo.common.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "topicDestination")
    private Destination destination;

    @RequestMapping(value = {"/item/{itemId}"}, method = RequestMethod.GET)
    @ResponseBody
    public TbItem getById(@PathVariable("itemId") Long itemId) {
        System.out.println(itemId);
        return itemService.getById(itemId);
    }

    /*@ResponseBody
    @RequestMapping("/items")
    public List<TbItem> listItems() {
        List<TbItem> list = null;
        try {
            list = itemService.listItems();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }*/

    @ResponseBody
    @RequestMapping(value = {"/items"})
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {
        System.out.println("当前页：" + page.getPage() + "...每页大小：" + page.getRows() + "...偏移量：" + page.getOffset());
        System.out.println("order=" + order + "排序字段：" + order.getSort() + "..." + "排序规则：" + order.getOrder());
        System.out.println("title=" + query.getTitle() + "..." + "status=" + query.getStatus());
        /*try {
            if (query.getTitle() != null) {
                System.out.println(new String(query.getTitle().getBytes("ISO-8859-1"), "utf-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        Result<TbItemCustom> result = null;
        try {
            result = itemService.listItemsByPage(page, order, query);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = {"/item"}, method = {RequestMethod.POST})
    public MessageResult saveItem(TbItem tbItem, String content, String paramData) {
        MessageResult result = new MessageResult();
        try {
            //保存商品
            final Long itemId = itemService.saveItem(tbItem, content, paramData);
            //发送消息
            jmsTemplate.send(destination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(itemId+"");
                    return textMessage;
                }
            });
            result.setSuccess(true);
            result.setMessage("添加商品成功！");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("添加商品失败！");
            System.out.println(logger);
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = {"/items/removeItemBatch", "/items/upItemBatch", "/items/downItemBatch"}, method = {RequestMethod.POST})
    public Integer updateItemsByIds(@RequestParam("ids[]") List<Long> ids, HttpServletRequest request) {
        System.out.println(ids);

        Integer affect = 0;
        try {
            if (request.getRequestURI().indexOf("removeItemBatch") > 0) {
                affect = itemService.removeItemsByIds(ids);
            } else if (request.getRequestURI().indexOf("downItemBatch") > 0) {
                affect = itemService.downItemsByIds(ids);
            } else if (request.getRequestURI().indexOf("upItemBatch") > 0) {
                affect = itemService.upItemsByIds(ids);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return affect;
    }

}
