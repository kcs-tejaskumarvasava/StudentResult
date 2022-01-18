package com.gov.student.service;

import javax.mail.MessagingException;

public interface MailOtpService {

	public int generateOTP(String key);
	
	 public int getOtp(String key);
	 
	 public void clearOTP(String key);
	 
	 public void sendOtpMessage(String to, String subject, String message) throws MessagingException;
	
}
