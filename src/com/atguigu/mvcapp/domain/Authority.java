package com.atguigu.mvcapp.domain;

import java.io.Serializable;

public class Authority implements Serializable{

    private String displayName;

    private String url;

    public Authority() {
    }

    public Authority(String displayName, String url) {
        this.displayName = displayName;
        this.url = url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        return url != null ? url.equals(authority.url) : authority.url == null;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "displayName='" + displayName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
