package com.pratap.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pratap.model.Event;
import com.pratap.util.HibernateUtils;

public class EventDAOImpl implements EventDAO {

	private SessionFactory  factory= HibernateUtils.getFactory();
	
	
	public void save(Event e) {
		System.out.println("factory comparision : "+ (HibernateUtils.getFactory() == HibernateUtils.getFactory()));
		System.out.println(factory);
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();

	}

}
