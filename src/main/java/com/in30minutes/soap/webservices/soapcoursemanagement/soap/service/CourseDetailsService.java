package com.in30minutes.soap.webservices.soapcoursemanagement.soap.service;
import  com.in30minutes.soap.webservices.soapcoursemanagement.soap.bean.Course;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CourseDetailsService {

	public enum Status{
		success,fails;
	}
	
	private static List<Course> courses =new ArrayList<>();
	static {
	Course course1=new Course(1, "java", "programmation avec java");
	courses.add(course1);
	Course course2=new Course(2, "managment", "management des entreprise");
	courses.add(course2);
	Course course3=new Course(3, "securité ", "introduction cyberSécurity");
	courses.add(course3);
	Course course4=new Course(4, "system", "dolibarrrrr");
	courses.add(course4);
	}
	public Course findById(int id) {
		for(Course course:courses) {
			if(course.getId()==id) {return course;}
		}
		return null;
	}
	public List findAll() {return courses;}
	
	public Status deleteById(int id) {
		Iterator<Course> iterator=courses.iterator();
		while(iterator.hasNext()) {
			Course course=iterator.next();
			if(course.getId()==id) {
				iterator.remove();
				return Status.success;
			}
		}return Status.fails;
	}
	
}
