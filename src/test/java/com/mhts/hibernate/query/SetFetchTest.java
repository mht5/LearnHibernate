package com.mhts.hibernate.query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class SetFetchTest {

	@Test
	public void test1() {
		Session session = MySessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Customer customer = session.get(Customer.class, 6);
        int size = customer.getOrders().size();
        System.out.println(size);

        tx.commit();
	}
	
}
