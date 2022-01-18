package com.gov.student.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gov.student.core.Student;
import com.gov.student.dto.ResponseVO;
import com.gov.student.dto.StudentVO;

public interface StudentService {

	ResponseVO addStudentDetails(StudentVO studentvo);

	Optional<Student> findByRollno(Long rollno);

	ResponseVO getStudentById(Long id);

	ResponseVO deleteStudentById(Long id);

	ResponseVO getStudentByFirstNameAndLastName(String fname, String lname);

	ResponseVO getStudentByFirstNameOrLastName(String fname, String lname);
	
	



}
