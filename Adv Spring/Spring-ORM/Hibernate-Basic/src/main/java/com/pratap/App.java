package com.pratap;

import java.util.Date;

import com.pratap.dao.EventDAO;
import com.pratap.dao.EventDAOImpl;
import com.pratap.model.Event;

public class App {

	public static void main(String[] args) {
		EventDAO dao = new EventDAOImpl();
		dao.save(new Event(1L, "some title", new Date()));
	}

	
}
