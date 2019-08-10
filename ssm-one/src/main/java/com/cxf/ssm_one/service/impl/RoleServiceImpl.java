package com.cxf.ssm_one.service.impl;

import com.cxf.ssm_one.dao.RoleMapper;
import com.cxf.ssm_one.pojo.Role;
import com.cxf.ssm_one.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author always_on_the_way
 * @date 2019-06-29
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findByUserId(Long id) {
        return roleMapper.selectByUserId(id);
    }
}
