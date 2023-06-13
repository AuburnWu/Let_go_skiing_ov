package course.core.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import course.core.util.HibernateUtil;

public interface CoreService {
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	default Transaction beginTransaction() {
		return getSession().beginTransaction();
	};
	
	default Transaction getTransaction() {
		return getSession().getTransaction();
	}
	
	default void commit() {
		getTransaction().commit();
	};
	
	default void rollback() {
		getTransaction().rollback();
	};
}

