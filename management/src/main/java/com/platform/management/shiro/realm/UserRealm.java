package com.platform.management.shiro.realm;

import com.platform.management.service.SystemUserService;
import com.platform.orm.entity.SystemUser;
import com.platform.orm.mapper.SystemUserMapper;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangying
 * Created on 2019/10/29.
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemUserService systemUserService;

    /**
     * 执行授权逻辑
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行授权逻辑");
        SystemUser user = (SystemUser) principals.getPrimaryPrincipal();
        String username = user.getUsername();
        log.info("从数据库获取权限信息");
        Set <String> roles = systemUserService.findRoleNameByUserName(username);
        Set <String> perms = systemUserService.findPermissionsByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        log.info("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SystemUser user = systemUserMapper.findByUsername(token.getUsername());
        if (Objects.isNull(user)) {
            throw new UnknownAccountException(token.getUsername());
        }
        return new SimpleAccount(user.getId(), user.getPassword(), user.getUsername());
    }
}
