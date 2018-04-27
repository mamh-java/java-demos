package com.mamh.ssh.demo.dao;

import com.mamh.ssh.demo.entities.Department;
import com.mamh.ssh.demo.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DepartmentDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        System.out.println(sessionFactory);
        return this.sessionFactory.getCurrentSession();
    }


    public List<Department> getAll() {
        String hql = "FROM Department";
        return getSession().createQuery(hql).list();
    }

}
