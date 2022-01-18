package com.gov.student.service.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gov.student.service.MailOtpService;

@Service
public class MailOtpServiceImpl implements MailOtpService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Integer EXPIRE_MINS = 4;
	
    private LoadingCache<String, Integer> otpCache;
    
	public MailOtpServiceImpl() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}
    
	@Override
	public int generateOTP(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}

	@Override
	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void clearOTP(String key) {
		otpCache.invalidate(key);
		
	}

	@Override
	public void sendOtpMessage(String to, String subject, String message) throws MessagingException {
		 MimeMessage msg = javaMailSender.createMimeMessage();

	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(message, true);
	        javaMailSender.send(msg);
		
	}

}
