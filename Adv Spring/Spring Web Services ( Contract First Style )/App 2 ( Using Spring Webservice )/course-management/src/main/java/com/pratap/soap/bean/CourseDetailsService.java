package com.pratap.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pratap.soap.bean.Course;

@Service
public class CourseDetailsService {

	private static List<Course> courses = new ArrayList<>();
	static {
		Course course1 = new Course(1, "Core Spring", "The foundation for Spring");
		courses.add(course1);

		Course course2 = new Course(2, "Adv Spring", "The Advanced Spring");
		courses.add(course2);

		Course course3 = new Course(3, "DevOps", "Development & Operations");
		courses.add(course3);
	}

	public Course findById(int id) {
		for (Course course : courses) {
			if (course.getId() == id) {
				return course;
			}
		}
		return null;
	}

	public List<Course> findAll() {
		return courses;
	}

	public int deleteById(int id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return 1;
			}
		}
		return 0;
	}
}
