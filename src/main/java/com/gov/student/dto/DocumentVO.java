package com.gov.student.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class DocumentVO {

	private Long id;
	
	private String documentName;
	
	private String title;
	
	private MultipartFile document;
}
