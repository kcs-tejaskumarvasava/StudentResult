package com.gov.student.service;


public interface OtpService {

	Boolean generateOtp(String key, String smsText);

}
