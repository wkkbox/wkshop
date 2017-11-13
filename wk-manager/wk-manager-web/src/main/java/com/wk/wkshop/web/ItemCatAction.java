package com.wk.wkshop.web;

import com.wk.wkshop.service.ItemCatService;
import com.wk.wkshpo.common.dto.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatAction {

    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping("/itemCats")
    public List<TreeNode> listItemCats(@RequestParam("parentId") Long parentId){
        return itemCatService.listItemCatsByPid(parentId);
    }
}
