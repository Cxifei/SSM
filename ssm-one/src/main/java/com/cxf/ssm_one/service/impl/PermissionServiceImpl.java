package com.cxf.ssm_one.service.impl;

import com.cxf.ssm_one.dao.PermissionMapper;
import com.cxf.ssm_one.pojo.Permission;
import com.cxf.ssm_one.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author always_on_the_way
 * @date 2019-06-29
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> findByRoleId(Long id) {
        return permissionMapper.selectByRoleId(id);
    }
}
