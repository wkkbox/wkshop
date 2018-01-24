package com.wk.wkshop.pojo.vo;

import java.util.List;

/**
 * 全文检索查询结果集
 */
public class TbSearchItemResult {

    /**
     * 总记录数
     */
    private long recordCount;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 查出的记录集合
     */
    private List<TbItemSearchCustom> itemList;

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TbItemSearchCustom> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbItemSearchCustom> itemList) {
        this.itemList = itemList;
    }
}
