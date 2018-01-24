package com.wk.wkshop.service.impl;

import com.wk.wkshop.common.jedis.JedisClient;
import com.wk.wkshop.common.util.JsonUtils;
import com.wk.wkshop.dao.TbUserMapper;
import com.wk.wkshop.pojo.po.TbUser;
import com.wk.wkshop.pojo.po.TbUserExample;
import com.wk.wkshop.service.LoginService;
import com.wk.wkshpo.common.dto.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult userLogin(String username, String password) {
        MessageResult result = new MessageResult();
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            //返回登录失败
            result.setSuccess(false);
            result.setMessage("用户名不存在");
            return result;
        }
        //用户名已存在，这个是从数据库来的
        TbUser user = list.get(0);
        //把前台传递过来密码进行MD5加密一次
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5Password.equals(user.getPassword())) {
            result.setSuccess(false);
            result.setMessage("用户名或者密码错误");
            return result;
        }
        //把登录成功的信息写入到redis中
        //将用户信息中密码置空
        user.setPassword(null);
        String token = UUID.randomUUID().toString();
        //用户信息存入缓存，并且设置过期时间，1800s(半小时)
        jedisClient.set("TT_TOKEN:" + token, JsonUtils.objectToJson(user));
        jedisClient.expire("TT_TOKEN:" + token,1800);
        //正确情况下返回MessageResult
        result.setSuccess(true);
        result.setMessage("登录成功");
        result.setData(token);
        return result;
    }
}
