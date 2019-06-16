package com.example.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.beans.Department;
import com.example.beans.Employee;

public class DepartmentDAOImplTest {

	@InjectMocks
	DepartmentDAOImpl impl;

	@Mock
	EntityManager entityManager;;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteTest() {
		Department dept = createDepartment(1, "Finance");
		when(impl.findOne(1)).thenReturn(dept);
		impl.delete(1);
		verify(entityManager, times(1)).remove(dept);
	}

	@Test
	public void saveTest() {
		Department dept = createDepartment(2, "Claim");
		impl.save(dept);
		verify(entityManager, times(1)).persist(dept);
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
		
		Query mockedQuery = mock(Query.class);
		when(entityManager.createQuery(Mockito.<String>any())).thenReturn(mockedQuery);
	    when(mockedQuery.getResultList()).thenReturn(list);
		
		List<Department> deptList = impl.findAll();
		assertEquals(3, deptList.size());
	}

	@Test
	public void getFindOneTest() {
		Department dept = createDepartment(1, "HR");
		when(entityManager.find(Department.class, 1)).thenReturn(dept);
		Department deptResult = impl.findOne(1);
		assertEquals("HR", deptResult.getName());
	}

	private Department createDepartment(Integer id, String deptName) {
		return new Department(id, deptName);
	}
}
