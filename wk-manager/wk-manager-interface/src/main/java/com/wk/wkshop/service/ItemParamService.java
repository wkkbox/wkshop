package com.wk.wkshop.service;

import com.wk.wkshop.pojo.vo.TbItemParamCustom;
import com.wk.wkshpo.common.dto.Order;
import com.wk.wkshpo.common.dto.Page;
import com.wk.wkshpo.common.dto.Result;

public interface ItemParamService {
    Result<TbItemParamCustom> listItemParamsByPage(Page page, Order order) throws Exception;
}
