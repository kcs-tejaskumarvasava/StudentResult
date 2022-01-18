package com.gov.student.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MailVO {

	private String mailFrom;
	 
    private String mailTo;
 
    private String mailCc;
 
    private String mailBcc;
 
    private String mailSubject;
 
    private String mailContent;
 
    private String contentType;
    
    private MultipartFile attachments;
}
