package com.mage.shirodemo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroRealm extends AuthenticatingRealm {
    private static final transient Logger log = LoggerFactory.getLogger(ShiroRealm.class);

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken 类型
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //2.从UsernamePasswordToken获取username
        String username = usernamePasswordToken.getUsername();

        //3.从数据库中查询username对应的记录
        log.info("从数据库中查询username对应的记录 " + username);

        //4.用户不存在可以抛出异常
        if ("unkonw".equals(username)) {
            throw new UnknownAccountException("用户不存在 " + username);
        }
        //5.根据用户信息情况，决定是否抛出其他异常
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定 " + username);
        }
        //6.根据用户信息构建AuthenticationInfo信息
        Object principal = username;
        Object credentials = "passwd";
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        return info;
    }
}
