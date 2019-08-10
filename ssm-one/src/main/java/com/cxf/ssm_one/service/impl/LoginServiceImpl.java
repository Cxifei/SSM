package com.cxf.ssm_one.service.impl;

import com.cxf.ssm_one.dao.UserMapper;
import com.cxf.ssm_one.pojo.User;
import com.cxf.ssm_one.service.ILoginService;
import com.cxf.ssm_one.utils.ShiroUtil;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author always_on_the_way
 * @date 2019-06-28
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 注册功能
     * @param username
     * @param password
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String username, String password) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            User user = new User();

            //获取盐
            String salt = ShiroUtil.getSalt();
            String pwd = ShiroUtil.getPwd(password, salt);
            user.setUsername(username);
            user.setPassword(pwd);
            user.setSalt(salt);
            userMapper.insertByUsernameAndPassword(user);
        }


    }
}
