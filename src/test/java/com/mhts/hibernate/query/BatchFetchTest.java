package com.mhts.hibernate.query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class BatchFetchTest {

	/**
	 * ʹ������ץȡ���Լ��ٲ�ѯ�������ڶ��һ����set��ʹ��@BatchSize����
	 */
	@SuppressWarnings("unchecked")
//	@Test
	public void test1() {
		Session session = MySessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<Customer> list = session.createQuery("from Customer").list();
        for (Customer customer : list) {
            System.out.println(customer.getOrders().size());
        }

        tx.commit();
	}

	/**
	 * ʹ������ץȡ���Լ��ٲ�ѯ�������ڶ��һ����set��ʹ��@BatchSize����
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test2() {
		Session session = MySessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<Order> list = session.createQuery("from Order").list();
        for (Order order : list) {
            System.out.println(order.getC().getName());
        }

        tx.commit();
	}

}
