package com.wk.wkshop.dao;


import com.wk.wkshop.pojo.vo.TbItemSearchCustom;

import java.util.List;

public interface TbItemSearchCustomMapper {
    List<TbItemSearchCustom> listSearchItems();

    TbItemSearchCustom getById(Long itemId);
}
