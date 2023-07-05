package com.alten.shop.alterShop.payload;

import org.springframework.http.HttpStatus;

public class ReturnMessageDto {
	private int statusCode;
	private String message;
	HttpStatus statusHttp;

	public ReturnMessageDto(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public ReturnMessageDto(int statusCode, String message, HttpStatus statusHttp) {
		
		this.statusCode=statusCode;
		this.message=message;
		this.statusHttp=statusHttp;
		
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
