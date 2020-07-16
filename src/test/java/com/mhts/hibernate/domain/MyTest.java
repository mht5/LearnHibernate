package com.mhts.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class MyTest {

	/**
	 * ≤‚ ‘get()∫Õload()
	 */
//	@Test
	 public void test1() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
//			Customer c = session.get(Customer.class, 1);
			Customer c = session.load(Customer.class, 10);
			System.out.println(c);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
    }
	 
	 /**
	  * ≤‚ ‘“ªº∂ª∫¥Ê
	  */
	 @Test
	 public void test2() {
			Session session = MySessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			try {
				Customer c = session.get(Customer.class, 1);
				System.out.println(c);
				Customer c1 = session.get(Customer.class, 1);
				System.out.println(c1);
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
			} finally {
				session.close();
			}
	 }
	
}
