package com.gov.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gov.student.core.Marks;
import com.gov.student.core.Student;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long>{

	

}
