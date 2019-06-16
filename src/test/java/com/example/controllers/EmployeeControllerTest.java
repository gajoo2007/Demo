package com.example.controllers;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.client.RestTemplate;

import com.example.beans.Department;
import com.example.beans.Employee;
import com.example.service.EmployeeService;

public class EmployeeControllerTest {

	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@InjectMocks
	EmployeeController controller;

	@Mock
	EmployeeService employeeService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllEmployeesTest() {
		List<Employee> list = new ArrayList<Employee>();
		Employee empOne = new Employee("Gajendra", "Yadav", new Department("IT"));
		Employee empTwo = new Employee("Brian", "Mortenson", new Department("HR"));

		list.add(empOne);
		list.add(empTwo);

		when(employeeService.findAll()).thenReturn(list);
		
		List<Employee> empList = controller.getAllEmployees();
		
		assertEquals(2, empList.size());
		verify(employeeService, times(1)).findAll();
	}
	
	@Test
	public void deleteEmployeeTest() {
		controller.deleteEmployee(1);
		verify(employeeService, times(1)).delete(1);
	}

	/*
	 * @Test public void createEmployeeTest() throws URISyntaxException { Employee
	 * emp = createEmployeeAndDepartment(null, "Ram", "Sharma", "IT");
	 * controller.createEmployee(emp); verify(employeeService, times(1)).save(emp);
	 * }
	 */
	
	private Employee createEmployeeAndDepartment(Integer id, String firstName, String lastName, String deptName) {
		return new Employee(id, firstName, lastName, createDepartment(deptName));
	}
	
	private Department createDepartment(String deptName) {
		return new Department(deptName);
	}

}
