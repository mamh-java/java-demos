package com.mamh.mybatis.demo.mapper;

import com.mamh.mybatis.demo.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Insert("INSERT INTO mb_users (name, age) VALUE (#{name}, #{age})")
    int add(User user);

    @Delete("DELETE FROM mb_users WHERE id = #{id}")
    int delete(Integer id);

    @Update("UPDATE mb_users  SET name = #{name}, age = #{age} WHERE id = #{id}")
    int update(User user);

    @Select("SELECT * FROM mb_users WHERE id = #{id}")
    User get(Integer id);
}
