package com.gov.student.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StudentVO {

	private Long id;
	
	private Long rollno;
	
	private String fname;
	
	private String lname;
	
	private String phoneNo;
	
	private String bloodGroup;
	
	private String std;
	
	private Date dob;
}
