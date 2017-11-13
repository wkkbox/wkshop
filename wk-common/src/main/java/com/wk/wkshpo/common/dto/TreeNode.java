package com.wk.wkshpo.common.dto;

/**
 * 通用的树节点实体类
 */
public class TreeNode {

    private Long id;
    private String text;
    private String state;//是否打开节点，父节点不打开"closed" ,子节点打开"open"

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
