package com.wk.wkshop.sso.web;

import com.wk.wkshop.common.util.CookieUtils;
import com.wk.wkshop.service.LoginService;
import com.wk.wkshpo.common.dto.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SSOLoginAction {

    @Autowired
    private LoginService loginService;

    @ResponseBody
    @RequestMapping("/user/login")
    public MessageResult login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        MessageResult result = null;
        try {
            result = loginService.userLogin(username, password);
            if (result.isSuccess()) {
                //登录成功
                String token = result.getData().toString();
                //写入cookie
                CookieUtils.setCookie(request, response, "wk_token", token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
