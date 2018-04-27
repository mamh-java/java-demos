package com.mamh.ssh.demo.dao;

import com.mamh.ssh.demo.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        System.out.println(sessionFactory);
        return this.sessionFactory.getCurrentSession();
    }

    public void delete(Integer id){
        String hql = "delete from Employee e where e.id=?";
        getSession().createQuery(hql).setInteger(0, id).executeUpdate();
    }

    public List<Employee> getAll() {
        //迫切左外链接
        String hql = "FROM Employee e left outer  join fetch e.department";
        return getSession().createQuery(hql).list();
    }

    public void saveOrUpdate(Employee employee){
        getSession().saveOrUpdate(employee);
    }

}
