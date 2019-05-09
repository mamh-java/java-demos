package com.mage.shirodemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/shiro")
@Controller
public class ShiroHandler {
    private static final transient Logger log = LoggerFactory.getLogger(ShiroHandler.class);

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();
        log.info("==username of " + username);

        // let's login the current user so we can check against roles and permissions:
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                log.info("login: " + token);
                currentUser.login(token);
            } catch (AuthenticationException ae) {
                //所有认证异常的父类
//                ae.printStackTrace();
                log.info("登录失败 " + token.getPrincipal() + "");

            }
        }

        return "redirect:/list.jsp";
    }

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/test")
    public String test() {
        shiroService.testService();
        return "redirect:/list.jsp";
    }
}
