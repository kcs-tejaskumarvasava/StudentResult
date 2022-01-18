package com.gov.student.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gov.student.dto.ResponseVO;
import com.gov.student.dto.StudentVO;
import com.gov.student.logging.Traceable;
import com.gov.student.service.StudentService;

@StudentSecuredController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("student/addStudent")
	@Traceable
	public ResponseVO<?> addEditStudentDetails( StudentVO studentvo) {
		return studentService.addStudentDetails(studentvo);
	}

	@GetMapping("student/getStudent")
	public ResponseVO<?> getStudentById(@RequestParam("id") Long id) {
		return studentService.getStudentById(id);
	}
	
	/*
	 * @GetMapping("/getStudent1") public ResponseEntity<Student>
	 * getStudentById1(@RequestParam("id") Long id) { return
	 * studentService.getStudentById(id); return }
	 */

	@DeleteMapping("student/deleteStudent")
	public ResponseVO<?> deleteStudent(@RequestParam("id") Long id) {
		return studentService.deleteStudentById(id);
	}
	
	@GetMapping("student/getStudentByFirstNameLastName")
	public ResponseVO getStudentByFirstNameAndLastName(@RequestParam("firstName")String fname,@RequestParam("lastName")String lname)
	{
		return studentService.getStudentByFirstNameAndLastName(fname,lname);
	}
	
	@GetMapping("student/getStudentByFirstNameOrLastName")
	public  ResponseVO getStudentByFirstNameOrLastName(@RequestParam("firstName")String fname,@RequestParam("lastName")String lname)
	{
		return studentService.getStudentByFirstNameOrLastName(fname,lname);
	}
}
