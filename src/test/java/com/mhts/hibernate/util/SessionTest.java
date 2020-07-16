package com.mhts.hibernate.util;

import org.hibernate.Session;
import org.junit.Test;

public class SessionTest {
	
	@Test
	public void test1() {
		
//		openSession()ÿ�δ�һ����Sesison
		Session s1 = MySessionFactory.openSession();
		Session s2 = MySessionFactory.openSession();
		System.out.println(s1 == s2);
		
//		getCurrentSession()��ȡ��session�뱾���̰߳�
		Session s3 = MySessionFactory.getCurrentSession();
		Session s4 = MySessionFactory.getCurrentSession();
		System.out.println(s3 == s4);
	}
	
}
