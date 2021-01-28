package com.cg.onlineeyeclinic.exception;

@SuppressWarnings("serial")
public class DoctorIdNotFoundException extends Exception {
	
public DoctorIdNotFoundException(int id) {
	System.out.println(id + " Not Found");
}
public DoctorIdNotFoundException(String message) {
	super(message);
}
}
