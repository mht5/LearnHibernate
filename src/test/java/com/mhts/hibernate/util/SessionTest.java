package com.mhts.hibernate.util;

import org.hibernate.Session;
import org.junit.Test;

public class SessionTest {
	
	@Test
	public void test1() {
		
//		openSession()每次打开一个新Sesison
		Session s1 = MySessionFactory.openSession();
		Session s2 = MySessionFactory.openSession();
		System.out.println(s1 == s2);
		
//		getCurrentSession()获取的session与本地线程绑定
		Session s3 = MySessionFactory.getCurrentSession();
		Session s4 = MySessionFactory.getCurrentSession();
		System.out.println(s3 == s4);
	}
	
}
