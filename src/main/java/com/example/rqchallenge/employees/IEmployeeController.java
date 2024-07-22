package com.example.rqchallenge.employees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rqchallenge.exception.ClientException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public interface IEmployeeController {

    @GetMapping()
    ResponseEntity<List<Employee>> getAllEmployees() throws IOException, ClientException;

    @GetMapping("/search/{searchString}")
    ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) throws ClientException;

    @GetMapping("/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws ClientException;

    @GetMapping("/highestSalary")
    ResponseEntity<Integer> getHighestSalaryOfEmployees() throws ClientException;

    @GetMapping("/topTenHighestEarningEmployeeNames")
    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws ClientException;

    @PostMapping()
    ResponseEntity<?> createEmployee(@RequestBody Map<String, Object> employeeInput);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable String id);

}
