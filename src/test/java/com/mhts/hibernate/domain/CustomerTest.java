package com.mhts.hibernate.domain;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class CustomerTest {

//	@Test
	public void saveCustomerTest() {
		Customer c = new Customer();
		c.setName("jack");
		c.setAge(12);
		c.setAddress("nowhere");
		
//		加载指定位置的hibernate配置文件
//		Configuration config = new Configuration().configure("hibernate-test.xml");
		
//		加载指定位置的映射配置文件
//		config.addResource("com/mhts/hibernate/domain/Customer.hbm.xml");
		
//		会去加载类所在的包下面符合规范的映射配置文件
//		config.addClass(Customer.class);
		
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(c);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	@Test
	public void findCustomerTest() {
//		Configuration config = new Configuration().configure();
//		SessionFactory sessionFactory = config.buildSessionFactory();
//		Session session = sessionFactory.openSession();
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Customer c = session.load(Customer.class, 1);
			System.out.println(c.getName() + ", " + c.getAddress());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
//			sessionFactory.close();
		}
	}

//	@Test
	public void listCustomerTest() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Customer");
		try {
			@SuppressWarnings("unchecked")
			List<Customer> list = query.list();
			for (Customer c : list) {
				System.out.println(c.getName() + ", " + c.getAddress());
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

//	@Test
	public void updateCustomerTest() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Customer c = session.get(Customer.class, 1);
			c.setName("sam");
			session.update(c);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

//	@Test
	public void deleteCustomerTest() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Customer c = session.get(Customer.class, 3);
			session.delete(c);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
}
