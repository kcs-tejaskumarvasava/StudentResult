package com.gov.student.dto;

import java.util.List;
import java.util.Map;

public class ResponseVO<T> {

	private String status;

	private Integer statusCode;

	private String message;

	private T result;
	
	

	public ResponseVO(String status, Integer statusCode, String message, T result) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.result = result;
	}

	public ResponseVO(Integer statusCode, String status, T data, String message) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.result = data;
	}

	public ResponseVO(Integer statusCode, String status, String message) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}

	
	

	public String getStatus() {
		return status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

	public T getResult() {
		return result;
	}

	public static ResponseVO<Void> create(Integer statusCode, String status, String message) {
		return new ResponseVO<Void>(statusCode, status, message);
	}
	
	public static <U> ResponseVO<U> create(Integer statusCode, String status, String message, U data) {
		return new ResponseVO<U>(statusCode, status, data, message);
	}

}
