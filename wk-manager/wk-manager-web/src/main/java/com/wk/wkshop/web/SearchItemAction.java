package com.wk.wkshop.web;

import com.wk.wkshop.service.SearchItemService;
import com.wk.wkshpo.common.dto.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchItemAction {

    @Autowired
    private SearchItemService searchItemService;

    @ResponseBody
    @RequestMapping("/item/search/import")
    public MessageResult searchItemIndex(){
        //通过调用service层方法将采集到的数据导入到索引库
        boolean bool = false;
        try {
            bool = searchItemService.importAllItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MessageResult mr = new MessageResult();
        if (bool) {
            mr.setSuccess(true);
            mr.setMessage("导入索引库成功");
        }else {
            mr.setSuccess(false);
            mr.setMessage("导入索引库失败");
        }
        return mr;
    }
}
