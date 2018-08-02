package com.company.jdbc.dao;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {
    /**
     * 批量处理的方法
     * @param connection
     * @param sql
     * @param args 填充占位符的object[]数组的可变参数
     */
    void batch(Connection connection, String sql, Object[] ...args);

    <E> E getForValue(Connection connection, String sql, Object ...args);

    List<T> getForList(Connection connection, String sql, Object ...args);

    T get(Connection connection, String sql, Object... args);

    //新的
    void update(Connection connection, String sql, Object... args);


}
