package com.wk.wkshop.web;

import com.wk.wkshop.pojo.vo.TbItemParamCustom;
import com.wk.wkshop.service.ItemParamService;
import com.wk.wkshpo.common.dto.Order;
import com.wk.wkshpo.common.dto.Page;
import com.wk.wkshpo.common.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemParamService itemParamService;

    @ResponseBody
    @RequestMapping("/itemParams")
    public Result<TbItemParamCustom> listItemParams(Page page, Order order) {
        Result<TbItemParamCustom> result = null;
        try {
            result = itemParamService.listItemParamsByPage(page, order);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}
