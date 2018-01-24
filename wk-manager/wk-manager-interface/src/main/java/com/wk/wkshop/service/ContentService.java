package com.wk.wkshop.service;


import com.wk.wkshop.pojo.po.TbContent;

import java.util.List;

public interface ContentService {
    List<TbContent> listContentByCid(Long cid);
}
