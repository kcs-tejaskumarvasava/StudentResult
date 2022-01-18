package com.gov.student.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Marks")
public class Marks {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "rollno_id")
	private Student rollno;
	
	@Column(name="subject1")
	private Long Sub1;
	
	@Column(name="subject2")
	private Long Sub2;
	
	@Column(name="subject3")
	private Long Sub3;
	
	@Column(name="subject4")
	private Long Sub4;
	
	@Column(name="subject5")
	private Long Sub5;
	
}
