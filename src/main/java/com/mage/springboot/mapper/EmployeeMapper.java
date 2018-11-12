package com.mage.springboot.mapper;


import com.mage.springboot.entities.Employee;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    @Select("select * from sp_employee where id = #{id}")
    Employee getEmpById(Integer id);

    @Update("update sp_employee set lastName=#{lastName}, email=#{email},gender=#{gender},d_id=#{department.id} where id = #{id}")
    void updateEmp(Employee employee);

    @Delete("delete from sp_employee where id = #{id}")
    void deleteEmp(Integer id);

    @Insert("insert into sp_employee(lastName, email, gender, d_id) values(#{lastName}, #{email}, #{gender}, #{department.id})")
    int insertEmp(Employee employee);
}
