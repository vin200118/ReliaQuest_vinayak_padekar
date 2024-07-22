package com.example.rqchallenge.employees;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientResponse {
	private String status;
	private List<Employee> data;
	private String message;
	
	
	public ClientResponse() {
		super();
	}
	public ClientResponse(String status, List<Employee> data, String message) {
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
	public List<Employee> getData() {
		return data;
	}

	public void setData(List<Employee> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
