package com.mhts.hibernate.one_to_one;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class MyTest {

//	@Test
	public void test1() {
		Session session = MySessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = new User();
		user.setName("jack");
		
		IDCard idCard = new IDCard();
		idCard.setCardNum("123456");
		idCard.setUser(user);
		
		try {
			session.save(idCard);
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

		Husband h = new Husband();
		h.setName("aragon");
		
		Wife w = new Wife();
		w.setName("ave");
		
		h.setWife(w);
		w.setHusband(h);
		
		try {
			session.save(h);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
