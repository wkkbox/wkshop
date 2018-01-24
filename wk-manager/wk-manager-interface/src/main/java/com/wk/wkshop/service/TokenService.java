package com.wk.wkshop.service;

import com.wk.wkshpo.common.dto.MessageResult;

public interface TokenService {

    MessageResult getUserByToken(String tokenId);
}
