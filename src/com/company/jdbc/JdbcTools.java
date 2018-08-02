package com.company.jdbc;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//工具类方法
public class JdbcTools {

    @Test
    public void testSetProperty() {
        Student student = new Student();
        try {
            BeanUtils.setProperty(student, "idCard", "xxxxxxxxxxxxxxxxxxxx");
            System.out.println(student);


            String student1 = BeanUtils.getProperty(student, "idCard");
            System.out.println(student1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGet() {
        String sql = null;

        sql = "SELECT flow_id flowId, type, id_card idCard, "
                + "exam_card examCard, student_name studentName, "
                + "location, grade "
                + "FROM examstudent where flow_id = ? ";
        Student student = get(Student.class, sql, 1);
        System.out.println(student);


        sql = "select id,name,address,phone from customers where id = ? ";

        Customer customer = get(Customer.class, sql, 16);
        System.out.println(customer);

    }

    public static <T> T get(Class<T> clazz, String sql, Object... args) {
        //获取 id=4 的 customers 数据表的记录, 并打印
        T object = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcTools.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            Map<String, Object> map = new HashMap<>();
            if (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String label = resultSetMetaData.getColumnLabel(i);
                    Object value = resultSet.getObject(label);
                    map.put(label, value);
                }
            }
            if (map.size() > 0) {
                object = clazz.newInstance();
                //给对象一个一个的赋值，对map进行遍历
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String fieldName = entry.getKey();
                    Object fieldValue = entry.getValue();
                    ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
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


    /**
     * 执行 SQL 语句, 使用 PreparedStatement
     *
     * @param sql
     * @param args: 填写 SQL 占位符的可变参数
     */
    public static void update(String sql, Object... args) {
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

    /**
     * 通用的一个 更新方法，包括insert，update，delete
     *
     * @param sql
     */
    public static void update(String sql) {
        //1.获取数据库链接
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JdbcTools.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(null, statement, connection);
        }
    }

    public static void release(ResultSet rs, Statement statement, Connection connection) {
        //1.关闭resultSet
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //2.关闭statement
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //3.关闭数据库链接
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 编写一个通用的方法，在不修改源程序的情况下，可以获取任何数据库的链接
     * 解决方法：把数据库驱动Driver的全类名，url，user，password放入到一个
     * 配置文件中。
     *
     * @return
     */
    public static Connection getConnection() throws Exception {
        String className = "com.mysql.jdbc.Driver";
        String jdbcUrl = "jdbc:mysql://localhost/atguigu";
        String user = "atguigu";
        String password = "123456";

//        InputStream in = getClass().getResourceAsStream("jdbc.prop");
//        Properties properties = new Properties();
//        properties.load(in);
//
//        className = properties.getProperty("className");
//        jdbcUrl = properties.getProperty("jdbcUrl");
//        user = properties.getProperty("user");
//        password = properties.getProperty("password");


        Driver driver = (Driver) Class.forName(className).newInstance();

        Properties info = new Properties();
        info.put("user", user);
        info.put("password", password);

        Connection c = driver.connect(jdbcUrl, info);
        return c;
    }

    public Connection getConnection2() throws Exception {
        //driver的管理类，一般开发中用这个，不直接使用driver。
        String className = null;
        String jdbcUrl = null;
        String user = null;
        String password = null;

        InputStream in = getClass().getResourceAsStream("jdbc.prop");
        Properties properties = new Properties();
        properties.load(in);

        className = properties.getProperty("className");
        jdbcUrl = properties.getProperty("jdbcUrl");
        user = properties.getProperty("user");
        password = properties.getProperty("password");


        /*
         *
         * 加载 JDBC 驱动需调用 Class 类的静态方法 forName()，向其传递要加载的 JDBC 驱动的类名
         *DriverManager 类是驱动程序管理器类，负责管理驱动程序
         *通常不用显式调用 DriverManager 类的 registerDriver() 方法来注册驱动程序类的实例，
         *因为 Driver 接口的驱动程序类都包含了静态代码块，在这个静态代码块中，
         *会调用 DriverManager.registerDriver() 方法来注册自身的一个实例
         *
         */
        Class.forName(className);
        Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
        return connection;

    }
}
