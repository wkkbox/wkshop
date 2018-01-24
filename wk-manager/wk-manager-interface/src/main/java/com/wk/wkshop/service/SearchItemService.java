package com.wk.wkshop.service;

import com.wk.wkshop.pojo.vo.TbSearchItemResult;

public interface SearchItemService {
    boolean importAllItems() throws Exception;

    TbSearchItemResult search(String keyword, Integer page, int i) throws Exception;
}
