package com.wk.wkshop.service;

import com.wk.wkshpo.common.dto.MessageResult;

public interface LoginService {

    MessageResult userLogin(String username, String password);
}
