package com.cxf.ssm_one.service;

import com.cxf.ssm_one.pojo.Permission;

import java.util.List;

/**
 * @author always_on_the_way
 * @date 2019-06-29
 */
public interface IPermissionService {
    List<Permission> findByRoleId(Long id);

}
