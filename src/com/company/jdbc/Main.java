package com.company.jdbc;

import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.Random;


public class Main {
    @Test
    public void testTransaction() {
        //事务操作
        Dao dao = new DaoImpl();
        String sql = null;
        Connection connection = null;//为了保证事务操作，需要这个connection是单例的。

        try {
            connection = JdbcTools.getConnection();
            connection.setAutoCommit(false);//设置默认提交为false，不自动提交
            connection.setTransactionIsolation(Connection.TRANSACTION_NONE);

            sql = "update users set  balance = (balance - 500) where id = 1";
            //dao.update(connection, sql);

            int a = 10 / 0;
            sql = "update users set  balance = (balance + 500) where id = 2";
            //dao.update(connection, sql);

            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    @Test
    public void testReadBlob() {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = JdbcTools.getConnection();
            String sql = "SELECT id,name,address, phone,picture FROM customers WHERE  id = 30";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getObject(1));
                System.out.println(resultSet.getObject(2));
                System.out.println(resultSet.getObject(3));
                System.out.println(resultSet.getObject(4));
                Blob blob = resultSet.getBlob(5);
                InputStream binaryStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream("/home/mamh/1.jpg");

                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = binaryStream.read(buffer)) != -1) {
                    outputStream.write(buffer);
                }
                binaryStream.close();
                outputStream.close();

                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertBlob() {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = JdbcTools.getConnection();
            String sql = "INSERT INTO customers(name, address, phone, picture) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "abc" + new Random().nextInt(10000));
            preparedStatement.setObject(2, "address + " + new Random().nextInt(10000));
            preparedStatement.setObject(3, "phone + " + new Random().nextInt(10000));
            InputStream inputStream = new FileInputStream("/home/mamh/QQ图片20170815153044.jpg");
            //插入一个图片文件
            preparedStatement.setBlob(4, inputStream);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetKeyValue() {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = JdbcTools.getConnection();
            String sql = "INSERT INTO customers(name,address,phone) VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, "abcdefghg");
            preparedStatement.setObject(2, "abc@gamil.com");
            preparedStatement.setObject(3, "sex");

            preparedStatement.executeUpdate();

            //获取主键的值
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                System.out.println(generatedKeys.getObject(1));
            }

            //主键列的名称，GENERATED_KEY
            ResultSetMetaData metaData = generatedKeys.getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                System.out.println(metaData.getColumnName(i + 1));
                System.out.println(metaData.getColumnLabel(i + 1));
                System.out.println();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testDatabaseMeta() {
        //DatabaseMeta 描述数据库的元数据对象
        Connection connection = null;
        try {
            connection = JdbcTools.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();


            //可以得到数据库本身的一些基本信息
            //数据库版本号
            int databaseMajorVersion = metaData.getDatabaseMajorVersion();
            System.out.println(databaseMajorVersion);

            String userName = metaData.getUserName();
            System.out.println(userName);

            ResultSet catalogs = metaData.getCatalogs();
            while (catalogs.next()) {
                System.out.println(catalogs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public Customer getCustomer(String sql, Object... args) {
        Customer customer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(resultSet, preparedStatement, connection);
        }

        return customer;
    }


    public Student getSudent(String sql, Object... args) {
        Student student = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()) {
                student = new Student(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(resultSet, preparedStatement, connection);
        }

        return student;
    }


    @Test
    public void testResultSetMetaData() {

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //1. 获取 Connection
            conn = JdbcTools.getConnection();
            //2. 获取 Statement
            statement = conn.createStatement();
            //3. 准备 SQL
            String sql = "SELECT id id_card, name my_name, address addr, phone ph FROM customers";

            //4. 执行查询, 得到 ResultSet
            resultSet = statement.executeQuery(sql);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                System.out.println(resultSetMetaData.getColumnLabel(i + 1));
                System.out.println(resultSetMetaData.getColumnName(i + 1));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6. 关闭数据库资源.
            JdbcTools.release(resultSet, statement, conn);
        }
    }

    @Test
    public void testResultSet() {
        //获取 id=4 的 customers 数据表的记录, 并打印

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //1. 获取 Connection
            conn = JdbcTools.getConnection();
            System.out.println(conn);

            //2. 获取 Statement
            statement = conn.createStatement();
            System.out.println(statement);

            //3. 准备 SQL
            String sql = "SELECT id, name, address, phone FROM customers";

            //4. 执行查询, 得到 ResultSet
            resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);

            //5. 处理 ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);

                System.out.println(id);
                System.out.println(name);
                System.out.println(address);
                System.out.println(phone);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6. 关闭数据库资源.
            JdbcTools.release(resultSet, statement, conn);
        }

    }


    @Test
    public void testStatement() {
        //1.获取数据库链接
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JdbcTools.getConnection();
            //2.准备插入的sql语句
            String insertsql = "INSERT INTO customers(name, address, phone) VALUES('abc','aaa','bbbb')";
            //3.执行插入。获取操作sql的statement对象
            // 调用statement的 executeUpdate(sql)方法,这个可以执行insert，update，delete，但不能是select。
            statement = connection.createStatement();
            statement.executeUpdate(insertsql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(null, statement, connection);
        }

    }

}
