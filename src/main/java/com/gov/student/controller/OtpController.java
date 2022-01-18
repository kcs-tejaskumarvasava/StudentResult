package com.gov.student.controller;

import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gov.student.dto.ResponseVO;
import com.gov.student.enums.ResponseStatus;
import com.gov.student.service.MailOtpService;
import com.gov.student.service.OtpService;
import com.gov.student.utils.Messages;

@RestController
@StudentSecuredController
public class OtpController {
	
	@Value("${sms.text}")
	private String smsText;

	@Autowired
	private OtpService otpService;
	
	@Autowired
	private MailOtpService mailOtpService;
	
	@PostMapping("/sendotp")
	public ResponseVO<Void> registerMobileAndSendOtp(@RequestParam("mobileNo") String mobileNo)
	{
		Boolean isSmsSent = otpService.generateOtp(mobileNo,smsText);
		System.out.println("smsText"+isSmsSent);
		if (isSmsSent)
			return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(), Messages.SEND_OTP);
		
		return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
				Messages.SEND_OTP_FAILURE);
	}
	
	
	@PostMapping("/sendotpToMail")
	public ResponseVO<Void> sendOtpToEmail(@RequestParam("to") String to,@RequestParam("subject") String subject,@RequestParam("message") String message) throws MessagingException
	{
		
		String username = "tejaskumar.vasava@kcsitglobal.com";
		int otp = mailOtpService.generateOTP(username);
		System.out.println("otp-->"+otp);
		
		//mailOtpService.sendOtpMessage(to, subject, message);
		
		Random rand = new Random();
		int maxNumber = 10000;

		int randomNumber = rand.nextInt(maxNumber) + 1;

		System.out.println("randomNumber-->"+randomNumber);
		
		
		return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
				Messages.SEND_OTP_FAILURE);
	}
}
