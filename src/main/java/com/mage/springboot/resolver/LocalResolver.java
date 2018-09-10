package com.mage.springboot.resolver;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LocalResolver implements LocaleResolver {

    /**
     * 点击链接切换语言，这里自定义了一个LocaleResolver。
     *
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String local = request.getParameter("l");
        if (!StringUtils.isEmpty(local)) {
            String[] split = local.split("_");
            return new Locale(split[0], split[1]);
        } else {
            return request.getLocale();
        }
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
