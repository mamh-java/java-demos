package com.mamh.hibernate.dao;

import com.mamh.hibernate.hql.entities.Department;
import com.mamh.hibernate.utils.HibernateUtils;
import org.hibernate.Session;

public class DepartmentDao {
    private Session session;





    /**
     * 这里session不能通过参数传来了，就需要类内部获取
     * 马哥的淘宝店铺 https://shop592330910.taobao.com/
     * <p>
     * hibernate 自身提供了三种管理session对象的方法
     * sesion对象的生命周期与本地线程绑定
     * sesion对象的生命周期与JTA事务绑定
     * hibernate委托程序管理session对象的生命周期
     * <p>
     * 在hibernate的配置文件中
     * hibernate.current_session_context_class 属性用于指定session管理方式，可选择的值有：
     * thread  ， session对象生命周期与本地线程绑定
     * jta*   ， session对象生命周期与JTA事务绑定
     * managed:，hibernate委托程序来管理session。
     *
     * @param department
     */
    public void save(Department department) {
        //获取和当前线程绑定的session对象
        //不需要从外部传入session对象
        //多个dao方法也可以使用一个事务
        Session session = HibernateUtils.getInstance().getSession();
        System.out.println("session hashcode = " + session.hashCode());
        //session.save(department);

    }


    /**
     * 这里传入一个session对象，则意味着上一层（service）需要获取到session对象，
     * 这说明上一层需要和hibernate的api紧密耦合，所以不推荐这种方式。
     *
     * @param session
     * @param department
     */
    public void save(Session session, Department department) {

    }
}
