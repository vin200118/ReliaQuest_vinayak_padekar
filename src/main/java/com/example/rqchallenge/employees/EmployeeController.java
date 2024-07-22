package com.example.rqchallenge.employees;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.rqchallenge.exception.ClientException;
@RestController
public class EmployeeController implements IEmployeeController {
	private EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() throws IOException, ClientException {
		List<Employee> list = employeeService.getAllEmployees();
		if(!list.isEmpty()) {
			return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) throws ClientException {
		List<Employee> list = employeeService.getEmployeesByNameSearch(searchString);
		if(!list.isEmpty()) {
			return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(String id) throws ClientException {
		Employee emp = employeeService.getEmployeeById(id);
		if(emp != null) {
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		
	}

	@Override
	public ResponseEntity<Integer> getHighestSalaryOfEmployees() throws ClientException {
		return new ResponseEntity<Integer>(employeeService.getHighestSalaryOfEmployees(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws ClientException {
		List<String> list = employeeService.getTopTenHighestEarningEmployeeNames();
		if(!list.isEmpty()) {
			return new ResponseEntity<List<String>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> createEmployee(Map<String, Object> employeeInput) {
		Employee employee = employeeService.createEmployee(employeeInput);
		if(employee != null) {
			return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Employee details not found",HttpStatus.NOT_FOUND);
		
	}

	@Override
	public ResponseEntity<String> deleteEmployeeById(String id) {
		String name = employeeService.deleteEmployeeById(id);
		if(name != null) {
			return new ResponseEntity<String>(name,HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Employee details not found",HttpStatus.NOT_FOUND);
		
	}

}
