package com.mage.springboot.mapper;


import com.mage.springboot.entities.Department;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DepartmentMapper {

    @Select("select * from sp_department where id=#{id}")
    Department getDeptById(Integer id);


    @Delete("delete from sp_department where id=#{id}")
    int delDeptById(Integer id);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sp_department(departmentName) value(#{departmentName})")
    int insertDept(Department department);


    @Update("update sp_department set departmentName=#{departmentName} where id=#{id}")
    int updateDept(Department department);
}
