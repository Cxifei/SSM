package com.cxf.ssm_one.dao;

import com.cxf.ssm_one.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author always_on_the_way
 * @date 2019-06-28
 */
public interface UserMapper {
    User selectByUsername(String username);

    void insertByUsernameAndPassword(User user);
}
