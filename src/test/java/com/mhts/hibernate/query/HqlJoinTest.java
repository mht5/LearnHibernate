package com.mhts.hibernate.query;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class HqlJoinTest {

	/**
	 * 内连接
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test1() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer c inner join c.orders with c.name like '_a_'";
		Query query = session.createQuery(hql);
		
		List<Object[]> list = query.list();
		for (Object[] objs : list) {
			for (Object obj : objs) {
				System.out.print(obj + "\t");
			}
			System.out.println();
		}
		
		tx.commit();
		session.close();
	}

	/**
	 * 隐式内连接
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test2() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Order o where o.c.id = 3";
		Query query = session.createQuery(hql);
		
		List<Object> list = query.list();
		for (Object obj : list) {
			System.out.println(obj);
		}
		
		tx.commit();
		session.close();
	}

	/**
	 * 迫切内连接，返回结果中装的是from后面的对象
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test3() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "select distinct c from Customer c inner join fetch c.orders";
		Query query = session.createQuery(hql);
		
		List<Object> list = query.list();
		for (Object obj : list) {
			System.out.println(obj);
		}
		
		tx.commit();
		session.close();
	}

	/**
	 * 左外连接
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test4() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer c left outer join c.orders";
		Query query = session.createQuery(hql);
		
		List<Object[]> list = query.list();
		for (Object[] objs : list) {
			for (Object obj : objs) {
				System.out.print(obj + "\t");
			}
			System.out.println();
		}
		
		tx.commit();
		session.close();
	}

	/**
	 * 迫切左外连接
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test5() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "select distinct c from Customer c left outer join fetch c.orders where c.id = 5";
		Query query = session.createQuery(hql);
		
		List<Object> list = query.list();
		for (Object obj : list) {
			System.out.println(obj);
		}
		
		tx.commit();
		session.close();
	}

	/**
	 * 右外连接
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test6() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Customer c right outer join c.orders";
		Query query = session.createQuery(hql);
		
		List<Object[]> list = query.list();
		for (Object[] objs : list) {
			for (Object obj : objs) {
				System.out.print(obj + "\t");
			}
			System.out.println();
		}
		
		tx.commit();
		session.close();
	}
	
}
