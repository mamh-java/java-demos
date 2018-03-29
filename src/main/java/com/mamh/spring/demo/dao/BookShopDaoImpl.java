package com.mamh.spring.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookShopDaoImpl implements BookShopDao {
    /**
     * 这里这边使用hibernate
     * 这里不推荐使用spring的api。HibernateTemplate , HibernateDaoSupport
     * 这样会导致dao和spring耦合，不利于移植
     */

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取当前线程绑定的session
     *
     * @return
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public Float findBookPriceByIsbn(String isbn) {
        String hql = "select b.price from Book b where b.isbn = ?";
        Query query = getSession().createQuery(hql).setParameter(0, isbn);

        return (Float) query.uniqueResult();

    }

    @Override
    public void updateBookStock(String isbn) {
        //需要手动检查库存是否充足。不够就抛出异常
        String hql;
        hql = "SELECT b.stock FROM Book b WHERE isbn = ?";
        Integer result = (Integer) getSession().createQuery(hql).setParameter(0, isbn).uniqueResult();
        if (result == 0) {
            throw new RuntimeException("库存不足");
        }

        hql = "update Book b set b.stock = b.stock -1 where b.isbn = ?";
        getSession().createQuery(hql).setParameter(0, isbn).executeUpdate();

    }

    @Override
    public void updateAccountBalance(String username, float price) {
        String hql;

        //验证余额是否足够
        hql = "select a.balance from Account a where username = ?";
        Float result = (Float) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
        if (result < price) {
            throw new RuntimeException("余额不足");
        }

        hql = "update Account  a set a.balance = a.balance - ? where a.username = ?";
        getSession().createQuery(hql).setParameter(0, price).setParameter(1, username).executeUpdate();

    }
}
