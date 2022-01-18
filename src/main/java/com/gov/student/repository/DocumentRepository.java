package com.gov.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gov.student.core.Document;

@Repository
public interface DocumentRepository  extends JpaRepository<Document,Long>{

	Optional<Document> findOneById(Long id);
	

}
