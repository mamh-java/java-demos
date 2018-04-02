package com.mamh.struts2.demo;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

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
public class Product {
    /**
     * 这些成员变量名称和form表单中的要一致 .真正对应的是和setter方法对应。
     */
    private int id;
    private String name;
    private String desc;
    private String price;


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

    public String save() throws Exception {
        System.out.println("save() in class Product......." + this);
        //获取ActionContext对象
        ActionContext actionContext = ActionContext.getContext();


        //获取application对应的map。添加属性。
        Map<String, Object> applicationMap = actionContext.getApplication();
        applicationMap.put("applicationKey", "applicationValue");


        //获取session
        Map<String, Object> sessionMap = actionContext.getSession();
        System.out.println(sessionMap);

        //获取request
        Map<String, Object> requestMap = (Map<String, Object>) actionContext.get("request");
        System.out.println(requestMap);


        //获取请求参数
        HttpParameters parameters = actionContext.getParameters();
        System.out.println(parameters);


        return "success";
    }


}
