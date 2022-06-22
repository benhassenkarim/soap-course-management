package com.in30minutes.soap.webservices.soapcoursemanagement.soap.bean;

public class Course {

	private int id;
	private String name;
	private String discription;
	public Course(int id, String name, String discription) {
		super();
		this.id = id;
		this.name = name;
		this.discription = discription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	@Override
	public String toString() {
		return String.format("Course [id=%s, name=%s, discription=%s]", id, name, discription);
	}
	
	
}
