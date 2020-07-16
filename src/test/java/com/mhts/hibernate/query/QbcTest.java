package com.mhts.hibernate.query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class QbcTest {

	/**
	 * 基本查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test1() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> list = criteria.list();
		
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
	public void test2() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Order.class);
		criteria.addOrder(org.hibernate.criterion.Order.asc("address"));
		List<Order> list = criteria.list();
		
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
	public void test3() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Customer.class);
		Criterion like = Restrictions.like("name", "_a%");
		criteria.add(like);
		List<Customer> customerList = criteria.list();
		for (Customer c : customerList) {
			System.out.println(c);
		}
		
		Customer c = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("id", 2)).uniqueResult();
		System.out.println(c);
		
//		criteria = session.createCriteria(Order.class);
//		SimpleExpression gt = Restrictions.gt("price", 1000d);
//		SimpleExpression eq = Restrictions.eq("c", c);
//		LogicalExpression and = Restrictions.and(gt, eq);
//		criteria.add(and);
//		List<Order> list = criteria.list();
		List<Order> list = session.createCriteria(Order.class)
				.add(Restrictions.and(Restrictions.gt("price", 1000d), Restrictions.eq("c", c)))
				.list();
		
		for (Order o : list) {
			System.out.println(o);
		}
		
		tx.commit();
		session.close();
	}
	
	/**
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test4() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Order.class);
		criteria.setFirstResult(10);
		criteria.setMaxResults(10);
		List<Order> list = criteria.list();
		
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
//	@Test
	public void test5() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Order.class);
//		Object obj = criteria.setProjection(Projections.rowCount()).uniqueResult();
//		System.out.println(obj);
		criteria.setProjection(Projections.projectionList().add(Projections.sum("price")).add(Projections.groupProperty("c")));
		List<Object[]> list = criteria.list();
		
		for (Object[] objs : list) {
			for (Object obj : objs) {
				System.out.print(obj + ", ");
			}
			System.out.println();
		}
		
		tx.commit();
		session.close();
	}
	
	/**
	 * 离线查询
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test6() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		dc.add(Restrictions.like("name", "_a_"));
		Criteria criteria = dc.getExecutableCriteria(session);
		List<Customer> list = criteria.list();
		
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
