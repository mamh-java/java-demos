package com.cil.Model;


import com.cil.Global.GlobalConstant;

import javax.persistence.*;


@Entity
@Table(name = "subscribe_list", schema = GlobalConstant.dbName, catalog = "")
public class SubscribeListEntity {
    private int id;
    private String openid;
    private String city;
    private String province;
    private String country;
    private String nickname;
    private String sex;
    private String subscribeTime;
    private String score;
    private String unionid;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "openid", nullable = false, length = 50)
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Basic
    @Column(name = "city", nullable = true, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "province", nullable = true, length = -1)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "country", nullable = true, length = -1)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = -1)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = -1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "subscribe_time", nullable = true, length = -1)
    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    @Basic
    @Column(name = "score", nullable = true, length = 8)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscribeListEntity entity = (SubscribeListEntity) o;

        if (id != entity.id) return false;
        if (openid != null ? !openid.equals(entity.openid) : entity.openid != null) return false;
        if (city != null ? !city.equals(entity.city) : entity.city != null) return false;
        if (province != null ? !province.equals(entity.province) : entity.province != null) return false;
        if (country != null ? !country.equals(entity.country) : entity.country != null) return false;
        if (nickname != null ? !nickname.equals(entity.nickname) : entity.nickname != null) return false;
        if (sex != null ? !sex.equals(entity.sex) : entity.sex != null) return false;
        if (subscribeTime != null ? !subscribeTime.equals(entity.subscribeTime) : entity.subscribeTime != null)
            return false;
        if (score != null ? !score.equals(entity.score) : entity.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (subscribeTime != null ? subscribeTime.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "unionid", nullable = true, length = 50)
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
