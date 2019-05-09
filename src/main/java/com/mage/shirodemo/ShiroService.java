package com.mage.shirodemo;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShiroService {

    @RequiresRoles({"admin"})
    public void testService(){
        System.out.println("test service: " + new Date());
    }
}
