package com.gov.student.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gov.student.core.Student;
import com.gov.student.dto.ResponseVO;
import com.gov.student.dto.StudentVO;
import com.gov.student.enums.ResponseStatus;
import com.gov.student.logging.Traceable;
import com.gov.student.repository.StudentRepository;
import com.gov.student.service.StudentService;
import com.gov.student.utils.Messages;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseVO addStudentDetails(StudentVO studentvo) {

		try {

			@SuppressWarnings("unused")
			Student studentStatusId = null;
			ResponseVO vo = validateRequest(studentvo);
			if (vo == null) {
				Student student = new Student();

				if (Objects.nonNull(studentvo.getId())) {
					student = studentRepository.findById(studentvo.getId()).get();
				}

				student.setRollno(studentvo.getRollno());
				student.setFname(studentvo.getFname());
				student.setLname(studentvo.getLname());
				student.setPhoneNo(studentvo.getPhoneNo());
				student.setBloodGroup(studentvo.getBloodGroup());
				student.setStd(studentvo.getStd());
				student.setDob(studentvo.getDob());
				studentStatusId = studentRepository.save(student);
				
				return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(),
						studentvo.getId() == null ? Messages.SUBMIT_SUCSSFULLY : Messages.UPDATE_SUCSSFULLY,student);
			}

			return vo;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private ResponseVO validateRequest(StudentVO studentvo) {

		if (studentvo.getRollno() == null) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.ROLLNO_VALIDATION);

		}
		if (StringUtils.isBlank(studentvo.getFname())) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.FIRSTNAME_VALIDATION);
		}
		if (StringUtils.isBlank(studentvo.getLname())) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.LASTNAME_VALIDATION);

		}
		if (StringUtils.isBlank(studentvo.getPhoneNo())) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.PHONENO_VALIDATION);

		}
		if (StringUtils.isBlank(studentvo.getBloodGroup())) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.BLOODGROUP_VALIDATION);

		}
		if (StringUtils.isBlank(studentvo.getStd())) {
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					Messages.CLASS_VALIDATION);

		}

		return null;
	}

	@Override
	public Optional<Student> findByRollno(Long rollno) {
		Optional<Student> student=studentRepository.findByRollno(rollno);
		if (student.isPresent()) {
			return student;
		}
		return null;
	}

	@Override
	@Traceable
	public ResponseVO getStudentById(Long id) {

		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			StudentVO studentVO = new StudentVO();
			BeanUtils.copyProperties(student.get(), studentVO);
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "FOUND SUCCSSFULLY",
					student);

		}
		return ResponseVO.create(HttpStatus.NOT_FOUND.value(), ResponseStatus.FAIL.name(), Messages.ID_NOT_FOUND);

	}

	@Override
	public ResponseVO deleteStudentById(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			studentRepository.delete(student.get());
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), Messages.DELETED_SUCSSFULLY,
					student);
		}
		return ResponseVO.create(HttpStatus.NOT_FOUND.value(), ResponseStatus.FAIL.name(), Messages.ID_NOT_FOUND);
	}

	@Override
	public ResponseVO getStudentByFirstNameAndLastName(String fname, String lname) {
		List<Student> studentList = studentRepository.findByFnameAndLname(fname, lname);

		if(studentList.size() !=0)
		{
			List<StudentVO> studentVOs = studentList.stream().map(obj->convertToVO(obj)).collect(Collectors.toList());
			
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "LIST OF STUDENTS", studentVOs);
		}
		return ResponseVO.create(HttpStatus.NOT_FOUND.value(), ResponseStatus.FAIL.name(), "NOT FOUND !!", null);
}

	@Override
	public ResponseVO getStudentByFirstNameOrLastName(String fname, String lname) {

		List<Student> studentList = studentRepository.findByFnameOrLname(fname, lname);

		if(studentList.size() !=0)
		{
			List<StudentVO> studentVOs = studentList.stream().map(obj->convertToVO(obj)).collect(Collectors.toList());
			
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), "LIST OF STUDENTS", studentVOs);
		}
		return ResponseVO.create(HttpStatus.NOT_FOUND.value(), ResponseStatus.FAIL.name(), "NOT FOUND !!", null);

	}

	private StudentVO convertToVO(Student obj) {
		// TODO Auto-generated method stub
		StudentVO studentVo=new StudentVO();
		studentVo.setBloodGroup(obj.getBloodGroup());
		studentVo.setDob(obj.getDob());
		studentVo.setFname(obj.getFname());
		studentVo.setId(obj.getId());
		studentVo.setLname(obj.getLname());
		studentVo.setPhoneNo(obj.getPhoneNo());
		studentVo.setRollno(obj.getRollno());
		studentVo.setStd(obj.getStd());
		return studentVo;
	}

	


}
