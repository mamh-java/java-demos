package com.company.jdbc.dao;

import com.company.jdbc.ReflectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 子类需要传入的泛型
 *
 * @param <T>
 */
public class JdbcDaoImpl<T> implements Dao<T> {
    private QueryRunner queryRunner = null;
    private Class<T> type;

    public JdbcDaoImpl() {
        queryRunner = new QueryRunner();
        type = ReflectionUtils.getSuperGenericType(this.getClass());
    }


    @Override
    public void batch(Connection connection, String sql, Object[]... args) {

    }

    @Override
    public <E> E getForValue(Connection connection, String sql, Object... args) {
        return null;
    }

    @Override
    public List<T> getForList(Connection connection, String sql, Object... args) {
        return null;
    }

    @Override
    public T get(Connection connection, String sql, Object... args) {
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Connection connection, String sql, Object... args) {

    }
}
