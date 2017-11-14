package com.wk.wkshop.service;

import com.wk.wkshop.pojo.po.TbItem;
import com.wk.wkshop.pojo.vo.TbItemCustom;
import com.wk.wkshop.pojo.vo.TbItemQuery;
import com.wk.wkshpo.common.dto.Order;
import com.wk.wkshpo.common.dto.Page;
import com.wk.wkshpo.common.dto.Result;

import java.util.List;

public interface ItemService {

    TbItem getById(Long itemId);

    Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query);

    Integer removeItemsByIds(List<Long> ids);

    Integer downItemsByIds(List<Long> ids);

    Integer upItemsByIds(List<Long> ids);

    Integer saveItem(TbItem tbItem, String content, String paramData) throws Exception;

    //List<TbItem> listItems();
}
