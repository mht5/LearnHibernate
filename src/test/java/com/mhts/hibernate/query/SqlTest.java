package com.mhts.hibernate.query;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class SqlTest {
	
	/**
	 * 本地SQL
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test1() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("select * from t_customer");
		query.addEntity(Customer.class);
		List<Customer> list = query.list();
		
		for (Customer c : list) {
			System.out.println(c);
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
	 * 命名查询
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test2() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.getNamedQuery("listCustomerSql");
		List<Customer> list = query.list();
		
		for (Customer c : list) {
			System.out.println(c);
		}
		
		try {
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
