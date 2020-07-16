package com.mhts.hibernate.query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class LoadTest {

	@Test
	public void test1() {
		Session session = MySessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Customer c = session.load(Customer.class, 5);
//        String name = c.getName();
//        System.out.println(name);
        
//        对延迟加载的对象进行初始化
        Hibernate.initialize(c);

        tx.commit();
	}
	
}
