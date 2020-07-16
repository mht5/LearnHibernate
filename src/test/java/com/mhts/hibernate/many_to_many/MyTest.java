package com.mhts.hibernate.many_to_many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.mhts.hibernate.util.MySessionFactory;

public class MyTest {
	
//	@Test
	public void test1() {
        Session session = MySessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Teacher t1 = new Teacher();
        t1.setName("jack");
        Teacher t2 = new Teacher();
        t2.setName("sam");
        
        Student s1 = new Student();
        s1.setName("abe");
        Student s2 = new Student();
        s2.setName("baron");
        
        s1.getTeachers().add(t1);
        s1.getTeachers().add(t2);
        s2.getTeachers().add(t1);
        s2.getTeachers().add(t2);

        try {
            session.save(s1);
            session.save(s2);
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
            Student student = session.get(Student.class, 1);
            session.delete(student);
            tx.commit();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
	}
	
}
