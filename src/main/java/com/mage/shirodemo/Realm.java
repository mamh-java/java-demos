package com.mage.shirodemo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 授权需要继承 AuthorizingRealm 类， 他继承 AuthenticatingRealm类。
 * 认证和授权值需要继承 AuthorizingRealm 类，同时实现
 * doGetAuthorizationInfo(PrincipalCollection principals) 和
 * doGetAuthenticationInfo(AuthenticationToken token)  方法。
 */
public class Realm extends AuthorizingRealm {
    private static final transient Logger log = LoggerFactory.getLogger(Realm.class);

    //用于授权的方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //用于认证的方法
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
        Object principal = username + "ShiroRealm";
        //Object credentials = "21d489dc169e1f9a07c26fba312269a9";//b9255bbad1f1b8b55c7a36635539849e
        Object credentials = null;//"b9255bbad1f1b8b55c7a36635539849e";//b9255bbad1f1b8b55c7a36635539849e

        if ("admin".equals(username)) {
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        } else if ("user".equals(username)) {
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }

        String realmName = getName();
        ByteSource salt = ByteSource.Util.bytes(username); //加盐


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
        return info;
    }
}
