package com.example.rqchallenge.employees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rqchallenge.exception.ClientException;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

@Service
public class EmployeeService {
	private EmployeeClientImpl clientImpl;
	public EmployeeService(EmployeeClientImpl clientImpl) {
		this.clientImpl = clientImpl;
	}

	public List<Employee> getAllEmployees() throws ClientException {
		Optional<List<Employee>> empList = clientImpl.getAllEmployees();
		if(empList.isPresent()) {
			return empList.get();
		}
		return Collections.emptyList();
	}

	public List<Employee> getEmployeesByNameSearch(String searchString) throws ClientException {
		Optional<List<Employee>> empList =  clientImpl.getAllEmployees();
		if(empList.isPresent()) {
			return empList.get().stream().sorted((a,b)->a.getEmployeeName().compareToIgnoreCase(b.getEmployeeName()))
			.filter(a->a.getEmployeeName().toLowerCase().contains(searchString)).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	public Employee getEmployeeById(String id) throws ClientException {
		Optional<Employee> emp = clientImpl.getEmployeeById(id);
		if(emp.isPresent()) {
			return emp.get();
		}
		return null;
		
	}
	public int getHighestSalaryOfEmployees() throws ClientException {
		Optional<List<Employee>> empList =  clientImpl.getAllEmployees();
		if(empList.isPresent()) {
			Optional<Employee> optEmployee = empList.get().stream().max(Comparator.comparing(Employee::getEmployeeSalary));
			if(optEmployee.isPresent()) {
				return optEmployee.get().getEmployeeSalary();
			}
		}
		return 0;
	}
	public List<String> getTopTenHighestEarningEmployeeNames() throws ClientException {
		Optional<List<Employee>> empList = clientImpl.getAllEmployees();
		if(empList.isPresent()) {
				return empList.get().stream().sorted(Comparator.comparing(Employee::getEmployeeSalary).reversed())
						.limit(10).map(a->a.getEmployeeName()).collect(Collectors.toList());		
		}
		return Collections.emptyList();
		
	}
	public Employee createEmployee(Map<String, Object> employeeInput) {
		return clientImpl.createEmployee(employeeInput);
		
	}

	public String deleteEmployeeById(String id) {
		return clientImpl.deleteEmployeeById(id);
		
	}

}
