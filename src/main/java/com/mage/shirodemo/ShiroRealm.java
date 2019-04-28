package com.mage.shirodemo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

public class ShiroRealm implements Realm {
    public String getName() {
        System.out.println("getname");
        return null;
    }

    public boolean supports(AuthenticationToken token) {
        System.out.println("supports");
        return false;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("getAuthenticationInfo");
        return null;
    }
}
