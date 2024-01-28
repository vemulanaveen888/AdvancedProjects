package com.pratap.dao;

import java.util.List;

import com.pratap.model.Event;

public interface EventDAO {
	public void save(Event  e);
	public List<Event> readAllEvents();
}
