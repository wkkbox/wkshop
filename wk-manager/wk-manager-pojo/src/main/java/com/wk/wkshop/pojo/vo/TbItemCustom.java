package com.wk.wkshop.pojo.vo;

import com.wk.wkshop.pojo.po.TbItem;

/**
 * 自定义的商品显示类(VO)
 */
public class TbItemCustom extends TbItem {

    private String catName;

    private String priceView;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getPriceView() {
        return priceView;
    }

    public void setPriceView(String priceView) {
        this.priceView = priceView;
    }
}
