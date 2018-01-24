package com.wk.wkshop.search.web;

import com.wk.wkshop.pojo.vo.TbSearchItemResult;
import com.wk.wkshop.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchIndexAction {

    @Autowired
    private SearchItemService searchItemService;

    /**
     * 查询系统首页查询
     * @param keyword 跟页面的控件name保持一致
     * @param page 默认为1 @RequestParam(defaultValue = "1")
     * @param model
     */
    @RequestMapping("/")
    public String searchIndex(String keyword,
                              @RequestParam(defaultValue = "1") Integer page, Model model) throws Exception {
        if (keyword != null) {
            try {
                keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(keyword);
            //查询商品列表
            TbSearchItemResult searchResult = searchItemService.search(keyword, page, 60);//每页60条，可以以后写到properties文件里，用PropKit来取值
            //把结果传递给页面
            model.addAttribute("query", keyword);
            model.addAttribute("totalPages", searchResult.getTotalPages());
            model.addAttribute("page", page);
            model.addAttribute("recordCount", searchResult.getRecordCount());
            model.addAttribute("itemList", searchResult.getItemList());
        }
        //返回逻辑视图
        return "search";
    }
}
