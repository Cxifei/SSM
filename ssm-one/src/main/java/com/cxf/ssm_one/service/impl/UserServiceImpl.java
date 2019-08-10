package com.cxf.ssm_one.service.impl;

import com.cxf.ssm_one.dao.UserMapper;
import com.cxf.ssm_one.pojo.User;
import com.cxf.ssm_one.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author always_on_the_way
 * @date 2019-06-28
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
