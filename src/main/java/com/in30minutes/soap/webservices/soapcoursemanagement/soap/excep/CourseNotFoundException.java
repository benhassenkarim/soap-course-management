package com.in30minutes.soap.webservices.soapcoursemanagement.soap.excep;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,customFaultCode = "{http://in30minutes.com/courses}001_course not found")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID=3518170101751491969L;
	public CourseNotFoundException(String message) {
		super(message);
		
	}

}
