package com.mamh.hibernate.demo;


import com.mamh.hibernate.hql.entities.Department;
import com.mamh.hibernate.hql.entities.Employee;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.sql.HSQLCaseFragment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.awt.X11.Depth;


import javax.persistence.PersistenceUnit;
import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;


public class HibernateHqlTest {
    SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;


    @Before
    public void init() {
        System.out.println("=init=");
        //1.创建一个SessionFactory 对象，创建session的工厂的一个类
        //1.1创建一个Configuration对象，对应hibernate的基本配置信息和对象关系映射信息
        Configuration configuration = new Configuration().configure();
        //1.2创建一个ServiceRegistry对象，hibernate4.x新添加的对象，hibernate任何的配置和服务都要在该对象中注册才能有效。
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        //1.3
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        //2.创建一个Session对象,这个和jdbc中的connection很类似
        session = sessionFactory.openSession();
        //3.开启事务
        transaction = session.beginTransaction();
    }


    @After
    public void destroy() {
        System.out.println("=destroy=");
        //5.提交事务
        transaction.commit();

        //6.关闭session对象
        session.close();

        //7.关闭SessionFactory 对象
        sessionFactory.close();

    }


    @Test
    public void testHQL() {         //参数使用占位符的

        String hql = "from Employee e where e.salary > ? and e.email like ?";
        Query query = session.createQuery(hql);

        query.setFloat(0, 6000).setString(1, "%B%");

        List list = query.list();
        System.out.println(list);
        System.out.println(list.size());


    }


    @Test
    public void testHQL1() {  //参数使用命名参数
        String hql = "from Employee e where e.salary > :sal and e.email like :email and e.dept = :dept order by e.salary";
        Query query = session.createQuery(hql);


        Department dept = new Department();
        dept.setId(80);
        query.setFloat("sal", 6000)
                .setString("email", "%B%")
                .setEntity("dept", dept);

        List list = query.list();
        //System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void testHQL2() {//分页查询
        String hql = "from Employee";
        Query query = session.createQuery(hql);
        int pageNo = 22;
        int pageSize = 5;

        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List list = query.list();
        System.out.println(list);


    }


    @Test
    public void testHQL3() {
        Query query = session.getNamedQuery("salary");

        query.setFloat("minSalary", 5000);
        query.setFloat("maxSalary", 10000);

        List list = query.list();
        System.out.println(list.size());
    }

    @Test
    public void testHQL4() {
        String hql = "" +
                "select new Employee (e.id, e.name,e.salary,e.email) " +
                "from Employee e " +
                "where e.dept= :dept";
        Query query = session.createQuery(hql);

        Department dept = new Department();
        dept.setId(80);

        List<Employee> list = query.setEntity("dept", dept).list();

        for (Employee o : list) {
            System.out.println(o);
        }

    }


    @Test
    public void testHQLGroupBy() {
        String hql = "select min(e.salary), max(e.salary) " +
                "from Employee e " +
                "group by e.dept " +
                "having min(salary) > :minSalary";
        Query query = session.createQuery(hql);

        query.setFloat("minSalary", 8000);

        List<Object[]> list = query.list();

        for (Object[] o : list) {
            System.out.println(Arrays.asList(o));
        }
    }


    /**
     * 迫切左外链接：使用left join fetch 关键字
     * list()方法返回的集合中存放实体对象引用，每个department对象关联的employee集合都被
     * 初始化，存放所有关联的employee的实体对象
     * 查询结果中可能会有重复元素，可以通过hashset来过滤重复元素
     */
    @Test
    public void testHQLLeftJoin() {
        String hql = "select  distinct d from  Department d left join fetch d.emps";
        Query query = session.createQuery(hql);

        List list = query.list();
        //System.out.println(list);
        System.out.println(list.size());

    }


    @Test
    public void testHQLLeftJoin1() {
        String hql = "from  Department d left join fetch d.emps";
        Query query = session.createQuery(hql);

        List list = query.list();
        System.out.println(new LinkedHashSet(list).size());
        System.out.println(list.size());

    }


    /**
     * 左外链接：使用left join 关键字
     * list()方法返回的集合中存放的是对象数组类型
     * 根据配置文件来决定employee结合的检索策略
     * 如果希望list()方法返回的集合中仅包含department对象，
     * 可以在hql查询语句中使用select关键字
     */
    @Test
    public void testHQLLeftJoin2() {
        String hql = "select  distinct  d from  Department d left join d.emps";
        Query query = session.createQuery(hql);

        List<Department> list = query.list();
        System.out.println(list.size());

        for (Department department : list) {
            System.out.println(department);
            //System.out.println(department.getEmps());  这里关联department的employee没有被初始化
        }

    }

    /**
     * 迫切内连接：
     * inner join fetch关键字表示迫切内连接，也可以省略inner关键字
     * list()方法返回的集合中存放department对象的引用，每个department对象的employee集合都被初始化
     * 存放所有关联的employee对象
     * <p>
     * 不返回左表不满足条件的记录
     */
    @Test
    public void testHQLInnerJoin() {
        String hql = "select  distinct d from  Department d inner join fetch d.emps";
        //  String hql = " from  Department d inner join fetch d.emps";
        Query query = session.createQuery(hql);

        List list = query.list();
        System.out.println(list.size());

    }

    /**
     * 内连接：
     * inner join关键字表示内连接，也可以省略inner关键字
     * list()方法的集合中存放的每个元素对应查询结果的一条记录，每个元素都是对象数组类型
     * 如果希望list()方法返回的集合仅包含department对象，可以在HQL查询语句中使用select关键字
     * <p>
     * <p>
     * 不返回左表不满足条件的记录
     */
    @Test
    public void testHQLInnerJoin1() {
        String hql = "select  distinct d from  Department d inner join fetch d.emps";
        //  String hql = " from  Department d inner join fetch d.emps";
        Query query = session.createQuery(hql);

        List list = query.list();
        System.out.println(list.size());

    }


    @Test
    public void testHQLJoin() {
        String hql = "select e from  Employee e inner join  e.dept order by e.dept.id";
        Query query = session.createQuery(hql);

        List<Employee> list = query.list();
        System.out.println(list.size());

        for (Employee employee : list) {
            System.out.println(employee.getName() + ", " + employee.getDept().getName());
        }

    }


}
