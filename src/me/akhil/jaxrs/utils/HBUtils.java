package me.akhil.jaxrs.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HBUtils {

	private static SessionFactory factory;

	private HBUtils() {

	}

	private static SessionFactory getSession() {

		if (factory == null) {

			try {
				factory = new Configuration().configure("hibernate.cfg.xml")
						.buildSessionFactory();
				System.out.println("session factory created.");
			} catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object."
						+ ex);
				throw new ExceptionInInitializerError(ex);
			}

		}
		return factory;

	}

	public static Integer save(Object data) {
		Session session = getSession().openSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(data);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public static <T> List<T> getList(Class<T> entity) {
		Session session = getSession().openSession();		
		try {							
			return session.createCriteria(entity).list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	public static <T> List<T> getList(String hql) {
		Session session = getSession().openSession();		
		try {							
			return session.createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	public static Object get(Class entity,int id) {
		Session session = getSession().openSession();		
		try {							
			return session.get(entity, id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}
