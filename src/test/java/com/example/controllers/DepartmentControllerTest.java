package com.example.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.beans.Department;
import com.example.service.DepartmentService;

public class DepartmentControllerTest {

	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@InjectMocks
	DepartmentController controller;

	@Mock
	DepartmentService departmentService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllDepartmentsTest() {
		List<Department> list = new ArrayList<Department>();
		Department deptOne = new Department("IT");
		Department deptTwo = new Department("HR");

		list.add(deptOne);
		list.add(deptTwo);

		when(departmentService.findAll()).thenReturn(list);
		
		List<Department> deptList = controller.getAllDepartments();
		
		assertEquals(2, deptList.size());
		verify(departmentService, times(1)).findAll();
	}
	
	@Test
	public void deleteDepartmentTest() {
		controller.deleteDepartment(1);
		verify(departmentService, times(1)).delete(1);
	}

	/*
	 * @Test public void createDepartmentTest() throws URISyntaxException {
	 * Department dept = createDepartment(1, "IT");
	 * controller.createDepartment(dept); verify(departmentService,
	 * times(1)).save(dept); }
	 */
	
	private Department createDepartment(Integer id, String deptName) {
		return new Department(id, deptName);
	}

}
