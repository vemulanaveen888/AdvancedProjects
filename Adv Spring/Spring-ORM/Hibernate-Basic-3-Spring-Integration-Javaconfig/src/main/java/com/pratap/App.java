package com.pratap;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pratap.dao.EventDAO;
import com.pratap.model.Event;

public class App {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);

		
		EventDAO  dao = context.getBean("dao", EventDAO.class);
		dao.save(new Event(3L, "A new Event!", new Date()));

	}

}
