package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.beans.Department;
import com.example.dao.DepartmentDAO;

public class DepartmentServiceImplTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@InjectMocks
	DepartmentServiceImpl impl;

	@Mock
	DepartmentDAO dao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllTest() {
		List<Department> list = new ArrayList<Department>();
		Department deptOne = new Department("IT");
		Department deptTwo = new Department("HR");
		Department deptThree = new Department("Claim");

		list.add(deptOne);
		list.add(deptTwo);
		list.add(deptThree);

		when(dao.findAll()).thenReturn(list);
		List<Department> deptList = impl.findAll();
		assertEquals(3, deptList.size());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void getFindOneTest() {
		Department dept = createDepartment(1, "HR");
		when(dao.findOne(1)).thenReturn(dept);
		Department deptResult = impl.findOne(1);
		assertEquals("HR", deptResult.getName());
	}

	@Test
	public void deleteTest() {
		impl.delete(1);
		verify(dao, times(1)).delete(1);
	}
	
	@Test
	public void saveWithNullIdTest() {
		Department dept = createDepartment(null, "IT");
		impl.save(dept);
		verify(dao, times(1)).save(dept);
	}
	
	@Test
	public void saveWithNotNullIdTest() {
		Department dept = createDepartment(1, "IT");
		thrown.expect(EntityExistsException.class);
        thrown.expectMessage(is("Already exist!"));
		impl.save(dept);
		verify(dao, times(1)).save(dept);
	}
	
	private Department createDepartment(Integer id, String deptName) {
		return new Department(id, deptName);
	}
}
