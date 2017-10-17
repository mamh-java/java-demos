package com.mamh.hibernate.demo.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "hb_news", schema = "atguigu", catalog = "")
public class HbNewsEntity {
    private int hbId;
    private String hbTitle;
    private String hbAuthor;
    private Date hbDate;

    @Id
    @Column(name = "hb_id", nullable = false)
    public int getHbId() {
        return hbId;
    }

    public void setHbId(int hbId) {
        this.hbId = hbId;
    }

    @Basic
    @Column(name = "hb_title", nullable = true, length = 1024)
    public String getHbTitle() {
        return hbTitle;
    }

    public void setHbTitle(String hbTitle) {
        this.hbTitle = hbTitle;
    }

    @Basic
    @Column(name = "hb_author", nullable = true, length = 2048)
    public String getHbAuthor() {
        return hbAuthor;
    }

    public void setHbAuthor(String hbAuthor) {
        this.hbAuthor = hbAuthor;
    }

    @Basic
    @Column(name = "hb_date", nullable = true)
    public Date getHbDate() {
        return hbDate;
    }

    public void setHbDate(Date hbDate) {
        this.hbDate = hbDate;
    }

    @Override
    public int hashCode() {
        int result = hbId;
        result = 31 * result + (hbTitle != null ? hbTitle.hashCode() : 0);
        result = 31 * result + (hbAuthor != null ? hbAuthor.hashCode() : 0);
        result = 31 * result + (hbDate != null ? hbDate.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HbNewsEntity that = (HbNewsEntity) o;

        if (hbId != that.hbId) return false;
        if (hbTitle != null ? !hbTitle.equals(that.hbTitle) : that.hbTitle != null) return false;
        if (hbAuthor != null ? !hbAuthor.equals(that.hbAuthor) : that.hbAuthor != null) return false;
        if (hbDate != null ? !hbDate.equals(that.hbDate) : that.hbDate != null) return false;

        return true;
    }
}
