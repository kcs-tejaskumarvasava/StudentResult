package com.gov.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gov.student.dto.MarksVO;
import com.gov.student.dto.ResponseVO;
import com.gov.student.service.MarksService;

@RestController
@RequestMapping("/marks")
public class MarksController {

	@Autowired
	private MarksService marksService;
	
	@PostMapping("/addMarks")
	public ResponseVO<?> addEditStudentMarks(MarksVO marksvo)
	{
		return marksService.addStudentMarks(marksvo);
	}
}
