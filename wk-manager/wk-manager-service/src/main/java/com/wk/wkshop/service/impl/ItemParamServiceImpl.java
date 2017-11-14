package com.wk.wkshop.service.impl;

import com.wk.wkshop.dao.TbItemParamCustomMapper;
import com.wk.wkshop.pojo.vo.TbItemParamCustom;
import com.wk.wkshop.service.ItemParamService;
import com.wk.wkshpo.common.dto.Order;
import com.wk.wkshpo.common.dto.Page;
import com.wk.wkshpo.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamCustomMapper tbItemParamCustomMapper;

    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page, Order order) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("order", order);
        List<TbItemParamCustom> list = tbItemParamCustomMapper.listItemParamsByPage(map);
        Integer total = tbItemParamCustomMapper.countItemParams();
        Result<TbItemParamCustom> result = new Result<>();
        result.setTotal(total);
        result.setRows(list);
        return result;
    }
}
