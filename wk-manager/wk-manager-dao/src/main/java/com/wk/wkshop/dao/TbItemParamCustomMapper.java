package com.wk.wkshop.dao;



import com.wk.wkshop.pojo.vo.TbItemParamCustom;

import java.util.List;
import java.util.Map;

public interface TbItemParamCustomMapper {


    List<TbItemParamCustom> listItemParamsByPage(Map<String, Object> map);

    Integer countItemParams();
}
