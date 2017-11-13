package com.wk.wkshpo.common.dto;

/**
 * 封装分页请求的参数类
 */
public class Page {
    /**
     * 当前页的页码
     */
    private Integer page;
    /**
     * 每页显示的条数
     */
    private Integer rows;

    /**
     * 偏移量（当前页的第一条记录的索引号）
     */
    //private Integer offset;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * 获取偏移量，不需要手工设值
     *
     * @return
     */
    public Integer getOffset() {
        return (page - 1) * rows;
    }
}
