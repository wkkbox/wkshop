package com.wk.wkshop.dao;

import com.wk.wkshop.pojo.vo.TbItemCustom;
import com.wk.wkshop.pojo.vo.TbItemQuery;
import com.wk.wkshpo.common.dto.Order;
import com.wk.wkshpo.common.dto.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义的商品实体数据访问层接口
 */
public interface TbItemCustomMapper {
    /**
     * 查询商品表中所有记录的数量
     */
    Integer countItems(Map<String, Object> map);

    /**
     * 查询指定页码显示的记录集合
     */
    //List<TbItemCustom> listItemsByPage(@Param("page") Page page, @Param("order") Order order);
    List<TbItemCustom> listItemsByPage(Map<String, Object> map);
}
