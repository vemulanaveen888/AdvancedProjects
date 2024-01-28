package com.pratap.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pratap.model.Event;

@Repository("dao")
public class EventDAOHibernateImpl implements EventDAO {

	@Autowired
	private  SessionFactory factory ;
	
	
	public void save(Event e) {
		System.out.println(factory);
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
	}

	

}
