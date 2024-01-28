package com.pratap;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pratap.dao.EventDAO;
import com.pratap.model.Event;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/pratap/beans.xml");
		

		
		EventDAO  dao = context.getBean("dao", EventDAO.class);
		dao.save(new Event(2L, "A new Event!", new Date()));

	}

}
