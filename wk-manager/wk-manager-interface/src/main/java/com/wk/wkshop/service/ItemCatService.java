package com.wk.wkshop.service;

import com.wk.wkshpo.common.dto.TreeNode;

import java.util.List;

public interface ItemCatService {
    List<TreeNode> listItemCatsByPid(Long parentId);
}
