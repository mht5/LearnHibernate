package com.mhts.hibernate.query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class OneFetchTest {

	@Test
	public void test1() {
		Session session = MySessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Order order = session.get(Order.class, 20);
        Customer c = order.getC();
        System.out.println(c.getName());

        tx.commit();
	}

}
