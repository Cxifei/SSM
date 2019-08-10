package com.cxf.ssm_one.shiro;

import com.cxf.ssm_one.pojo.Permission;
import com.cxf.ssm_one.pojo.Role;
import com.cxf.ssm_one.pojo.User;
import com.cxf.ssm_one.service.IPermissionService;
import com.cxf.ssm_one.service.IRoleService;
import com.cxf.ssm_one.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义shiro
 *
 * @author always_on_the_way
 * @date 2019-06-27
 */
public class MyRealm extends AuthorizingRealm {

    /**
     * 用户
     */
    @Autowired
    private IUserService userService;

    /**
     * 角色
     */
    @Autowired
    private IRoleService roleService;

    /**
     * 权限
     */
    @Autowired
    private IPermissionService permissionService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取当前正在使用的用户，获得的值取决于在登录认证的方法中存入的认证信息中获取
        User principal = (User) principalCollection.getPrimaryPrincipal();
        if (principal != null){

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            //根据用户的Id获取用户的权限
            List<Role> roles = roleService.findByUserId(principal.getId());
            List<String> roleList= new ArrayList<>(roles.size());
            List<String> permissionList = new ArrayList<>();
            for (Role role : roles) {
                //获取角色的名称
                String name = role.getName();
                roleList.add(name);

                //根据角色ID获取该角色所对应的权限
               List<Permission> permissions = permissionService.findByRoleId(role.getId());
                for (Permission permission : permissions) {
                    String expression = permission.getExpression();
                    permissionList.add(expression);
                }
            }

            info.addRoles(roleList);
            info.addStringPermissions(permissionList);

            return info;

        }


        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户名
        UsernamePasswordToken passwordToken = (UsernamePasswordToken)authenticationToken;
        String username = passwordToken.getUsername();
        //TODO 根据用户名查询数据库中是否存在该用户
        User user = userService.findByUsername(username);
        if (user == null){
             throw new UnknownAccountException("用户"+username+"不存在");
        }

        //获取用户名
        String userUsername = user.getUsername();
        //获取密码
        String password = user.getPassword();
        //获取盐
        String salt = user.getSalt();

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        //在授权方法中存入认证的用户信息，存入user对象取对象， 存入userUsername取字符串
        info.setPrincipals(new SimplePrincipalCollection(user,this.getName()));
        info.setCredentials(password);
        info.setCredentialsSalt(ByteSource.Util.bytes(salt));

        return info;
    }
}
