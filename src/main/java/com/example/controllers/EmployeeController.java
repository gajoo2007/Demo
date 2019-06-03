package com.example.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.beans.Employee;
import com.example.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
		System.out.println("EmployeeService - Id: " +id);
		Employee employee = employeeService.getEmployeeByID(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	@PostMapping("employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		//HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getEmployeeID()).toUri());
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@PutMapping("employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@DeleteMapping("employee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}