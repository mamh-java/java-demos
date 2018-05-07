package com.mamh.springmvc.demo.views;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Component
public class HelloView implements View {
    @Override
    public void render(@Nullable Map<String, ?> model, HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        response.getWriter().println("hello view, time: " + new Date());

    }

    @Nullable
    @Override
    public String getContentType() {
        return "text/html";
    }
}
