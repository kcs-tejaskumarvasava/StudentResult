package com.gov.student.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gov.student.core.Marks;
import com.gov.student.core.Student;
import com.gov.student.dto.MarksVO;
import com.gov.student.dto.ResponseVO;
import com.gov.student.enums.ResponseStatus;
import com.gov.student.repository.MarksRepository;
import com.gov.student.repository.StudentRepository;
import com.gov.student.service.MarksService;
import com.gov.student.service.StudentService;
import com.gov.student.utils.Messages;

@Service
public class MarksServiceImpl implements MarksService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MarksRepository marksRepository;
	
	@Override
	public ResponseVO addStudentMarks(MarksVO marksvo) {

		try {

			@SuppressWarnings("unused")
			Marks marksStatusId = null;
			
			ResponseVO vo = validateRequest(marksvo);
			
			if (vo == null) {
				
				Marks marks=new Marks();
				Optional<Student>  student=studentRepository.findByRollno(marksvo.getRollno());
				
				if (Objects.nonNull(marksvo.getId())) {
					marks = marksRepository.findById(marksvo.getId()).get();
					
					System.out.println("id is-->"+marksvo.getId() +"-->"+marks.toString());
				}
				    // Student studentRollno= studentService.findByRollno(marksvo.getRollno());
				Optional<Student> studentRollno= studentService.findByRollno(marksvo.getRollno());
				
				marks.setRollno(studentRollno.get());
				marks.setSub1(marksvo.getSub1());
				marks.setSub2(marksvo.getSub2());
				marks.setSub3(marksvo.getSub3());
				marks.setSub4(marksvo.getSub4());
				marks.setSub5(marksvo.getSub5());
				marksStatusId =marksRepository.save(marks);
				
				return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(),
						marksvo.getId() == null ? Messages.MARKS_SUBMIT_SUCSSFULLY : Messages.MARKS_UPDATE_SUCSSFULLY,marks);
			}
			 

			return vo;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isExistStudent(Long rollno)
	{
	
		Optional<Student>  student=studentRepository.findByRollno(rollno);
		if(student.isPresent())
		{
			return true;
		}
		return false;
	}
	private ResponseVO validateRequest(MarksVO marksvo) {

		if (  !isExistStudent(marksvo.getRollno())  ) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.ROLLNO_NOTEXIST);

		}
		if( marksvo.getRollno() == null)
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.ROLLNO_VALIDATION);
		}

		if (marksvo.getSub1() == null || marksvo.getSub1() > 100 ) {

			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.MARKS_INVALID);

		}
		if (marksvo.getSub2() == null || marksvo.getSub2() > 100) {

			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.MARKS_INVALID);
		}
		if (marksvo.getSub3() == null || marksvo.getSub3() > 100) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.MARKS_INVALID);
		}
		if (marksvo.getSub4() == null || marksvo.getSub4() > 100) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.MARKS_INVALID);
		}
		if (marksvo.getSub5() == null || marksvo.getSub5() > 100) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.MARKS_INVALID);
		}
		return null;
	}

}
