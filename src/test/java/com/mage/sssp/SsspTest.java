package com.mage.sssp;

import com.mage.sssp.entity.Department;
import com.mage.sssp.repository.DepartmentRepository;
import org.hibernate.jpa.QueryHints;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SsspTest {

    private ApplicationContext context = null;
    private DepartmentRepository departmentRepository;
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        departmentRepository = context.getBean(DepartmentRepository.class);
        entityManagerFactory = context.getBean(EntityManagerFactory.class);
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource datasource = context.getBean(DataSource.class);
        System.out.println(datasource.getConnection());
    }

    @Test
    public void testCache() {

//        List<Department> all = departmentRepository.findAll();
        List<Department> all = departmentRepository.getAll();

        System.out.println(all);
        System.out.println("\n");
//        List<Department> all1 = departmentRepository.findAll();

        List<Department> all1 = departmentRepository.getAll();
        System.out.println(all1);
    }

    @Test
    public void testJpaCache() {
        String jpql = "From Department d";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(jpql);
        List l = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
        System.out.println(l);
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql);
        l = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
        System.out.println(l);
        entityManager.close();
    }


}
