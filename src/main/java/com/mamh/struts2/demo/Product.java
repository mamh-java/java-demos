package com.mamh.struts2.demo;

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
        return "success";
    }
}
