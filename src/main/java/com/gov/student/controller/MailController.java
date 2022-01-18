package com.gov.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gov.student.dto.MailVO;
import com.gov.student.service.EmailService;

@RestController
@StudentSecuredController
public class MailController {

	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendEmail")
	public void sendEmail( MailVO emailvo) //@RequestBody
	{
		//System.out.println("name of docu");
		emailService.sendEmail(emailvo);
	}
}
