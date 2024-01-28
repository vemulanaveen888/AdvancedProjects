package com.pratap.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
	private static SessionFactory  sessionFactory;
	
	static {
		System.out.println("static block");
		final StandardServiceRegistry registry = 
					new StandardServiceRegistryBuilder().configure()
														.build();
		try {
			
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			System.out.println("sessionFactory : "+sessionFactory);
		} catch (Exception e) {
			System.out.println("in catch" + e);
			StandardServiceRegistryBuilder.destroy(registry);
		}
	
	}
	
	public  static SessionFactory   getFactory() {
		return sessionFactory;
	}
}
