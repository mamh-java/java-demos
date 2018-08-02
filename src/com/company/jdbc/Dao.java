package com.company.jdbc;

import java.util.List;

public interface Dao {

    void update(String sql, Object... args);

    <T> T get(Class<T> clazz, String sql, Object... args);

    <T> List<T> getAll(Class<T> clazz, String sql, Object... args);//这个是查询多条记录，返回一个列表

    <E> E get(String sql, Object... args);//返回一个具体的记录的具体的某个列的值
}
