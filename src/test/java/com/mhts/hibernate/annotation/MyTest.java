package com.mhts.hibernate.annotation;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class MyTest {

//	@Test
	public void test1() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Book book = new Book();
		book.setName("book1");
		book.setPrice(12.34);
		book.setPublicationDate(new Date(System.currentTimeMillis()));
		
		try {
			session.save(book);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

//	@Test
	public void test2() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Person person = new Person();
		person.setName("sam");
		person.setMsg("this is not going to be saved");
		
		try {
			session.save(person);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void test3() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Customer c = new Customer();
        c.setName("jack");

        Order o1 = new Order();
        o1.setPrice(1000d);
        o1.setAddress("adress1");

        Order o2 = new Order();
        o2.setPrice(2000d);
        o2.setAddress("adress2");

        o1.setC(c);
        o2.setC(c);
        c.getOrders().add(o1);
        c.getOrders().add(o2);
        
//        session.save(c);
        session.save(o1);
        session.save(o2);

        try {
            tx.commit();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
	}
	
}
