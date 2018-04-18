package com.mamh.struts2.demo;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * 在Action中怎么访问web资源？
 * 例如httpServletRequest，httpSession，servletContext等servlet的api。
 * <p>
 * 和servelt解耦方法：
 * 使用ActionContext
 * <p>
 * 实现 xxxAware接口
 */
public class Product implements RequestAware, SessionAware {
    /**
     * 这些成员变量名称和form表单中的要一致 .真正对应的是和setter方法对应。
     */
    private int id;
    private String name;
    private String desc;
    private String price;
    private Map<String, Object> requestMap;
    private Map<String, Object> sessionMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


    public void setRequest(Map<String, Object> request) {
        this.requestMap = request;
    }

    public void setSession(Map<String, Object> session) {
        this.sessionMap = session;
    }


    public String save() {

        System.out.println("save..............");

        return "success";
    }
}
