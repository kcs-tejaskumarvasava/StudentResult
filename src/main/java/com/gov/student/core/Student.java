package com.gov.student.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "rollno")
	private Long rollno;

	@Column(name = "first_name")
	private String fname;

	@Column(name = "last_name")
	private String lname;

	@Column(name = "phone_number")
	private String phoneNo;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "standard")
	private String std;

	@Column(name = "Date_of_birth")
	private Date dob;

}
