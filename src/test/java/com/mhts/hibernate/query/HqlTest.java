package com.mhts.hibernate.query;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.query.Customer;
import com.mhts.hibernate.query.Order;
import com.mhts.hibernate.util.MySessionFactory;

public class HqlTest {

//	@Test
	public void test1() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Customer c = new Customer();
        c.setName("sam");
        
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setPrice(1000d);
            order.setAddress("adress" + i);
            order.setC(c);
            session.save(order);
        }
        try {
        	tx.commit();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
	}
	
	/**
	 * 基本查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test2() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        String hql = "from Customer";
        Query query = session.createQuery(hql);
		List<Customer> list = query.list();
        
        for (Customer c : list) {
        	System.out.println(c);
        }
        
        tx.commit();
        session.close();
	}
	
	/**
	 * 排序查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test3() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();
		
        String hql = "from Order order by address";
        List<Order> list = session.createQuery(hql).list();
        
        for (Order o : list) {
        	System.out.println(o);
        }
        
        tx.commit();
        session.close();
	}
	
	/**
	 * 条件查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test4() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
//        String hql = "from Customer where name = ?";
        String hql = "from Customer where name like :name";
        Query query = session.createQuery(hql);
//        query.setParameter(0, "jack");
        query.setParameter("name", "_a_");
		List<Customer> list = query.list();
        
        for (Customer c : list) {
        	System.out.println(c);
        }
        
        tx.commit();
        session.close();
	}
	
	/**
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test5() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        String hql = "from Order";
        Query query = session.createQuery(hql);
        query.setFirstResult(10);
        query.setMaxResults(10);
		List<Order> list = query.list();
        
        for (Order o : list) {
        	System.out.println(o);
        }
        
        tx.commit();
        session.close();
	}
	
	/**
	 * 分组统计查询
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test6() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        String hql = "select sum(price) from Order group by c";
        Query query = session.createQuery(hql);
		List<Object> list = query.list();
        
        for (Object obj : list) {
        	System.out.println(obj);
        }
        
        tx.commit();
        session.close();
	}
	
	/**
	 * 投影查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test7() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "select new Customer(id, name) from Customer";
        Query query = session.createQuery(hql);
        List<Customer> list = query.list();
        
        for (Customer c : list) {
        	System.out.println(c);
        }
        
        tx.commit();
        session.close();
	}
	
	/**
	 * 命名查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test8() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.getNamedQuery("listCustomer");
        List<Customer> list = query.list();
        
        for (Customer c : list) {
        	System.out.println(c);
        }
        
        tx.commit();
        session.close();
	}
	
	/**
	 * 命名查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test9() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        Customer c = session.get(Customer.class, 7);

        Query query = session.getNamedQuery("findOrderByCustomer");
        query.setEntity("c", c);
        List<Order> list = query.list();
        
        for (Order o : list) {
        	System.out.println(o);
        }
        
        tx.commit();
        session.close();
	}
	
}
