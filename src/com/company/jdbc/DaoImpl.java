package com.company.jdbc;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao {

    static Dao dao;

    static {
        dao = new DaoImpl();
    }


    @Test
    public void testUpdate() {
        String sql = "insert into customers(name,address,phone) values(?,?,?)";
        update(sql, "tom", "beijing", "123456123");
    }

    @Test
    public void testGetAll() {
        String sql = "SELECT id , name , address , phone FROM customers";
        List<Customer> list = getAll(Customer.class, sql);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }



    @Override
    public void update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(null, preparedStatement, connection);
        }
    }

    @Override
    public <T> T get(Class<T> clazz, String sql, Object... args) {
        //获取 id=4 的 customers 数据表的记录, 并打印
        T object = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultSetMetaData resultSetMetaData = null;
        try {
            connection = JdbcTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();

            if (resultSet.next()) {
                object = clazz.newInstance();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String fieldName = resultSetMetaData.getColumnLabel(i);
                    Object fieldValue = resultSet.getObject(fieldName);
                    //ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
                    BeanUtils.setProperty(object, fieldName, fieldValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6. 关闭数据库资源.
            JdbcTools.release(resultSet, preparedStatement, connection);
        }

        return object;
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz, String sql, Object... args) {
        //获取 id=4 的 customers 数据表的记录, 并打印
        List<T> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultSetMetaData resultSetMetaData = null;
        try {
            connection = JdbcTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                T obj = clazz.newInstance();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String fieldName = resultSetMetaData.getColumnLabel(i);
                    Object fieldValue = resultSet.getObject(fieldName);
                    //ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
                    BeanUtils.setProperty(obj, fieldName, fieldValue);
                    System.out.println("    obj = " + obj);
                }
                System.out.println("");
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6. 关闭数据库资源.
            JdbcTools.release(resultSet, preparedStatement, connection);
        }

        return list;
    }


    @Override
    public <E> E get(String sql, Object... args) {
        return null;
    }
}
