package com.atguigu.mvcapp.dao;

import com.atguigu.mvcapp.domain.Authority;
import com.atguigu.mvcapp.domain.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionDAO {

    private static Map<String, Permission> users;

    private static ArrayList<Authority> authorities;

    static {
        authorities = new ArrayList<>();
        authorities.add(new Authority("ariticle-1", "/permission/article-1.jsp"));
        authorities.add(new Authority("ariticle-2", "/permission/article-2.jsp"));
        authorities.add(new Authority("ariticle-3", "/permission/article-3.jsp"));
        authorities.add(new Authority("ariticle-4", "/permission/article-4.jsp"));
        authorities.add(new Authority("ariticle-5", "/permission/article-5.jsp"));
        authorities.add(new Authority("ariticle-6", "/permission/ariticle-6.jsp"));


        users = new HashMap<>();
        users.put("AAA", new Permission("AAA", authorities.subList(0, 2)));
        users.put("BBB", new Permission("BBB", authorities.subList(2, 4)));
    }


    public Permission get(String username) {
        return users.get(username);
    }


    public void update(String username, List<Authority> authorities) {
        users.get(username).setAuthorities(authorities);
    }

    public static ArrayList<Authority> getAuthorities() {
        return authorities;
    }

    public List<Authority> getAuthorities(String[] urls) {
        List<Authority> newauthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            if (urls != null) {
                for (String url : urls) {
                    if (url.equalsIgnoreCase(authority.getUrl())) {
                        newauthorities.add(authority);
                    }
                }//end for
            } else {

            }//end if
        }//end for

        return newauthorities;
    }
}
