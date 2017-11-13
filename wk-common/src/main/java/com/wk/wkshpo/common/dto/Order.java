package com.wk.wkshpo.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI的DataGrid排序通用类
 */
public class Order {
    /**
     * order:排序规则,asc,desc
     */
    private String order;
    /**
     * sort:要排序的字段
     */
    private String sort;

    public List<String> getOrderParams() {
        List<String> list = new ArrayList<>();
        String[] orders = order.split(",");//asc,desc
        String[] sorts = sort.split(",");//id,title
        for (int i = 0; i < orders.length; i++) {
            String temp = sorts[i] + " " + orders[i];//id asc    title desc
            list.add(temp);
        }
        return list;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
