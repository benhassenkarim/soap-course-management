package com.in30minutes.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in30minutes.courses.CourseDetails;
import com.in30minutes.courses.DeleteCourseDetailsRequest;
import com.in30minutes.courses.DeleteCourseDetailsResponse;
import com.in30minutes.courses.GestALLCourseDetailsResponse;
import com.in30minutes.courses.GestAllCourseDetailsRequest;
import com.in30minutes.courses.GestCourseDetailsRequest;
import com.in30minutes.courses.GestCourseDetailsResponse;
import com.in30minutes.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.in30minutes.soap.webservices.soapcoursemanagement.soap.excep.CourseNotFoundException;
import com.in30minutes.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.in30minutes.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailEndpoint {
	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace = "http://in30minutes.com/courses",localPart = "GestCourseDetailsRequest")
	@ResponsePayload
	public GestCourseDetailsResponse processCourseDetailsRequest(
		@RequestPayload GestCourseDetailsRequest request	) {
		
		Course course= service.findById(request.getId());
		if(course==null)
			throw new CourseNotFoundException("invalid Course Id"+request.getId());
		return mapCorseDetails(course);
		
	}
	@PayloadRoot(namespace = "http://in30minutes.com/courses",localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(
		@RequestPayload DeleteCourseDetailsRequest request	) {
		
		Status status= service.deleteById(request.getId());
		DeleteCourseDetailsResponse response=new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
		
	}
	private com.in30minutes.courses.Status mapStatus(Status status) {
		if(status==Status.fails)
		return com.in30minutes.courses.Status.FAILS;
		return com.in30minutes.courses.Status.SUCCESS;
	}
	private GestCourseDetailsResponse mapCorseDetails(Course course) {
		GestCourseDetailsResponse response=new GestCourseDetailsResponse();
		
		response.setCourseDetails(mapCorse(course));
		
		return response;
	}
	
	private GestALLCourseDetailsResponse mapAllCorseDetails(List<Course> courses) {
		GestALLCourseDetailsResponse response=new GestALLCourseDetailsResponse();
		for(Course course:courses) {
			CourseDetails mapCourse=mapCorse(course);
			response.getCourseDetails().add(mapCourse);	
		}
		return response;
	}
	private CourseDetails mapCorse(Course course) {
		CourseDetails courseDetails= new CourseDetails();
		
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDiscription());
		return courseDetails;
	}
	@PayloadRoot(namespace = "http://in30minutes.com/courses",localPart = "GestAllCourseDetailsRequest")
	@ResponsePayload
	public GestALLCourseDetailsResponse processAllCourseDetailsRequest(
		@RequestPayload GestAllCourseDetailsRequest request	) {
		List<Course> courses= service.findAll();
		return mapAllCorseDetails(courses);
		}
	
}
