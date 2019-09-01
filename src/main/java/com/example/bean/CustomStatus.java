package com.example.bean;

import org.springframework.http.HttpStatus;

public class CustomStatus {
	
	private HttpStatus statusCode;
	private String statusMesaage;
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMesaage() {
		return statusMesaage;
	}
	public void setStatusMesaage(String statusMesaage) {
		this.statusMesaage = statusMesaage;
	}
	
	
	

}
