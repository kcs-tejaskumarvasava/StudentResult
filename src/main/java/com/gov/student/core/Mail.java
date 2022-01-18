package com.gov.student.core;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Mail {

	private String mailFrom;
	 
    private String mailTo;
 
    private String mailCc;
 
    private String mailBcc;
 
    private String mailSubject;
 
    private String mailContent;
 
    private String contentType;
    
    private MultipartFile attachments;
 
	
	/*
	 * private List < Object > attachments;
	 * 
	 * public Mail() { contentType = "text/plain"; }
	 */
}
