package com.example.rqchallenge.employees;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.rqchallenge.exception.ClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeClientImpl {
	private RestTemplate restTemplate;
	private ObjectMapper mapper;
	public EmployeeClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.mapper = new ObjectMapper();
	}
	
	public Optional<List<Employee>> getAllEmployees() throws ClientException{
		try {
		 ResponseEntity<String> map = restTemplate.getForEntity("https://dummy.restapiexample.com/api/v1/employees",String.class);
		    try {
				ClientResponse client = mapper.readValue(map.getBody(), ClientResponse.class);
				return Optional.ofNullable(client.getData());
			} catch (JsonProcessingException e) {
				throw new ClientException(e.getMessage());
			}
		}catch(Exception e) {
			if(e instanceof HttpClientErrorException.TooManyRequests){
				throw new ClientException("too may request error");
			}
			throw e;
		}
	}

	public Optional<Employee> getEmployeeById(String id) throws ClientException {
		try {
			
			 ResponseEntity<String> map = restTemplate.getForEntity("https://dummy.restapiexample.com/api/v1/employees/{id}",
	        		String.class, id);
			    try {
			    	ClientEmployeeResponse client = mapper.readValue(map.getBody(), ClientEmployeeResponse.class);
					return Optional.ofNullable(client.getData());
				} catch (JsonProcessingException e) {
					throw new ClientException(e.getMessage());
				}
			
			}catch(Exception e) {
				if(e instanceof HttpClientErrorException.TooManyRequests){
					throw new ClientException("too may request error");
				}
				throw e;
			}
		
	}
	
	public Employee createEmployee(Map<String, Object> employeeInput) {
		 Employee emp = mapper.convertValue(employeeInput, Employee.class);
		ResponseEntity<Employee> map = restTemplate.postForEntity(
	              "https://dummy.restapiexample.com/api/v1/create", emp,
	              Employee.class, "");
		return map.getBody();
	 
	}

	public String deleteEmployeeById(String id) {
	  HttpHeaders httpHeaders = new HttpHeaders();
	  httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	  HttpEntity<Employee> entity = new HttpEntity<>(httpHeaders);
	  ResponseEntity<Employee> emp =  restTemplate.exchange("https://dummy.restapiexample.com/api/v1/delete/" + id,
		                                 HttpMethod.DELETE, entity,
		                                 Employee.class);
	  return emp.getBody().getEmployeeName();
	}

}
