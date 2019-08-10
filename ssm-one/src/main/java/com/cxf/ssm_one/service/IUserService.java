package com.cxf.ssm_one.service;

import com.cxf.ssm_one.pojo.User;

/**
 * @author always_on_the_way
 * @date 2019-06-28
 */
public interface IUserService {

    User findByUsername(String username);

}
