package com.gov.student.service.impl;

import java.net.URI;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gov.student.service.OtpService;

@Service
public class otpServiceImpl implements OtpService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(otpServiceImpl.class);

	private static final Integer EXPIRES_MIN = 10;
	
	@Value("${sms.enable}")
	private Boolean enableSms;	
	
	@Value("${sms.url}")
	private String smsUrl;
	
	@Value("${sms.gsm}")
	private String smsGsm;
	

	private LoadingCache<String, String> otpCache;
	
	public otpServiceImpl() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRES_MIN, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() {

					@Override
					public String load(String key) throws Exception {
						return StringUtils.EMPTY;
					}
				});
	}

	@Override
	public Boolean generateOtp(String key, String smsText) {

		if (enableSms) {
			String randomOtp = RandomStringUtils.randomNumeric(4);
			
			otpCache.put(key, randomOtp);			
			
			String smsTxt = StringUtils.replace(smsText, "{code}", randomOtp);		
			System.out.println("smsTxtsmsTxt"+smsTxt);
			
			return sendSms(key, smsTxt);
		} else {
			String randomOtp = "1234";
			otpCache.put(key, randomOtp);
			
			return true;
		}
	
	}

	private Boolean sendSms(String phoneNo, String smsTxt) {

		// {code} is OTP for Student Verification. Your OTP is valid for 2 minutes.
		try {
			RestTemplate restTemplate = new RestTemplate();

			URI uri = UriComponentsBuilder
					.fromHttpUrl(smsUrl)
					.queryParam("PhoneNumber", phoneNo)
					.queryParam("Text", URLEncoder.encode(smsTxt, "UTF-8"))
					.queryParam("GSM", smsGsm)
					.build(true).toUri();

			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
			
			if (response.getStatusCode() == HttpStatus.OK && StringUtils.contains(response.getBody(), "Ok")) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			
			return false;
		}
	
	}

}
