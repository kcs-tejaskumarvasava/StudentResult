package com.gov.student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gov.student.core.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	Optional<Student> findByRollno(Long id);
	
	List<Student> findByFnameAndLname(String fname,String lname);
	
	List<Student> findByFnameOrLname(String fname,String lname);
}
