package com.wk.wkshpo.common.dto;

import java.util.List;

/**
 * 封装分页请求的响应参数类
 */
public class Result<T> {
    /**
     * 符合条件的总记录数
     */
    private Integer total;
    /**
     * 指定页码显示的记录集合
     */
    private List<T> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
