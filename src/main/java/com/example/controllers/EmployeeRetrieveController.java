package com.example.controllers;

/*import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Employee;
import com.example.beans.EmployeeRegistration;
import com.example.beans.EmployeeRegistrationReply;

@RestController
public class EmployeeRetrieveController {
	@RequestMapping(method = RequestMethod.GET, value = "/employee/allemployee")
	@ResponseBody
	public List<Employee> getAllEmployees() {
		return EmployeeRegistration.getInstance().getEmployeeRecords();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register/employee")
	@ResponseBody
	public EmployeeRegistrationReply registerEmployee(@RequestBody Employee employee) {
		System.out.println("In Register Employee");
		EmployeeRegistrationReply empRegReply = new EmployeeRegistrationReply();
		EmployeeRegistration.getInstance().add(employee);
		empRegReply.setFirstName(employee.getFirstName());
		empRegReply.setLastName(employee.getLastName());
		empRegReply.setEmployeeID(employee.getEmployeeID());
		empRegReply.setStatus("Successful");
		return empRegReply;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/employee")
	@ResponseBody
	public String updateEmployeeRecord(@RequestBody Employee emp) {
		System.out.println("In Update Employee Record");
		return EmployeeRegistration.getInstance().updateEmployee(emp);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/employee/{regdNum}")
	@ResponseBody
	public String deleteEmployeeRecord(@PathVariable("regdNum") Integer regdNum) {
		System.out.println("In Delete Employee Record");
		return EmployeeRegistration.getInstance().deleteEmployee(regdNum);
	}
}*/

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
@RequestMapping("employee")
public class EmployeeRetrieveController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
		Employee employee = employeeService.getEmployeeByID(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	@PostMapping("employee")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder) {
		employeeService.addEmployee(employee);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employee.getEmployeeID()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
