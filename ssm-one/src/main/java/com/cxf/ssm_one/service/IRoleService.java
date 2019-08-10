package com.cxf.ssm_one.service;

import com.cxf.ssm_one.pojo.Role;

import java.util.List;

/**
 * @author always_on_the_way
 * @date 2019-06-28
 */
public interface IRoleService {

    List<Role> findByUserId(Long id);
}
