package com.mamh.bank.demo;

public class Item {
    private String name;
    private String path;
    private String stringBase;
    private String stringTranslation;

    public Item(String name, String path, String stringBase, String stringTranslation) {
        this.name = name;
        this.path = path;
        this.stringBase = stringBase;
        this.stringTranslation = stringTranslation;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStringBase() {
        return this.stringBase;
    }

    public void setStringBase(String stringBase) {
        this.stringBase = stringBase;
    }

    public String getStringTranslation() {
        return this.stringTranslation;
    }

    public void setStringTranslation(String stringTranslation) {
        this.stringTranslation = stringTranslation;
    }
}