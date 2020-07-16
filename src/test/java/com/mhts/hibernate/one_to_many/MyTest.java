package com.mhts.hibernate.one_to_many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class MyTest {

	@Test
	public void test1() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Customer c = new Customer();
        c.setName("jack");
        c.setAge(22);

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

        try {
//            session.save(c);
            session.save(o1);
            session.save(o2);
            tx.commit();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
	}
	
	@Test
    public void test2() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        try {
            Customer c = session.get(Customer.class, 1);
            session.delete(c);
            tx.commit();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
    }
	
}
