package com.pratap;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.pratap.dao.EventDAO;
import com.pratap.dao.EventDAOImpl;
import com.pratap.model.Event;

public class App {

	public static void main(String[] args) {
		EventDAO dao = new EventDAOImpl();
		dao.save(new Event(1L, "some title", new Date()));
	}

	
}
