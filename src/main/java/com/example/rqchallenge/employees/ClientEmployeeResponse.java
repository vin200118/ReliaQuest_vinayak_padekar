package com.example.rqchallenge.employees;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientEmployeeResponse {
	private String status;
	private Employee data;
	private String message;
	
	
	public ClientEmployeeResponse() {
		super();
	}
	public ClientEmployeeResponse(String status, Employee data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Employee getData() {
		return data;
	}

	public void setData(Employee data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
