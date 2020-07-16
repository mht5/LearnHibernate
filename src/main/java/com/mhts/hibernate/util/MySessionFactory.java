package com.mhts.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {

	private static Configuration config;
	private static SessionFactory sessionFactory;
	
	static {
		config = new Configuration().configure();
		sessionFactory = config.buildSessionFactory();
	}
	
	public static Session openSession() {
		return sessionFactory.openSession();
	}
	
	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
