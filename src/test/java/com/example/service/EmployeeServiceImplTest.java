package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.beans.Department;
import com.example.beans.Employee;
import com.example.dao.EmployeeDAO;
import com.example.service.EmployeeServiceImpl;

public class EmployeeServiceImplTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@InjectMocks
	EmployeeServiceImpl impl;

	@Mock
	EmployeeDAO dao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllTest() {
		List<Employee> list = new ArrayList<Employee>();
		Employee empOne = new Employee("Gajendra", "Yadav", new Department("IT"));
		Employee empTwo = new Employee("Brian", "Mortenson", new Department("HR"));
		Employee empThree = new Employee("John", "Perry", new Department("Claim"));

		list.add(empOne);
		list.add(empTwo);
		list.add(empThree);

		when(dao.findAll()).thenReturn(list);
		List<Employee> empList = impl.findAll();
		assertEquals(3, empList.size());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void getFindOneTest() {
		Employee emp = createEmployeeAndDepartment(1, "Gajendra", "Kumar", "HR");

		when(dao.findOne(1)).thenReturn(emp);

		Employee empResult = impl.findOne(1);
		assertEquals("Gajendra", empResult.getFirstName());
		assertEquals("Kumar", empResult.getLastName());
	}

	@Test
	public void deleteTest() {
		impl.delete(1);
		verify(dao, times(1)).delete(1);
	}
	
	@Test
	public void saveWithNullIdTest() {
		Employee emp = createEmployeeAndDepartment(null, "Ram", "Sharma", "IT");
		impl.save(emp);
		verify(dao, times(1)).save(emp);
	}
	
	@Test
	public void saveWithNotNullIdTest() {
		Employee emp = createEmployeeAndDepartment(1, "Ram", "Sharma", "IT");
		thrown.expect(EntityExistsException.class);
        thrown.expectMessage(is("Already exist!"));
		impl.save(emp);
		verify(dao, times(1)).save(emp);
	}
	
	private Employee createEmployeeAndDepartment(Integer id, String firstName, String lastName, String deptName) {
		return new Employee(id, firstName, lastName, createDepartment(deptName));
	}

	private Department createDepartment(String deptName) {
		return new Department(deptName);
	}
}
