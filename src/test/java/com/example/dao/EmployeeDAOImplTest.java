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

public class EmployeeDAOImplTest {

	@InjectMocks
	EmployeeDAOImpl impl;

	@Mock
	EntityManager entityManager;;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteTest() {
		Employee emp = createEmployeeAndDepartment(null, "Ram", "Sharma", "IT");
		when(impl.findOne(1)).thenReturn(emp);
		impl.delete(1);
		verify(entityManager, times(1)).remove(emp);
	}

	@Test
	public void saveTest() {
		Employee emp = createEmployeeAndDepartment(null, "Ram", "Sharma", "IT");
		impl.save(emp);
		verify(entityManager, times(1)).persist(emp);
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
		
		Query mockedQuery = mock(Query.class);
		when(entityManager.createQuery(Mockito.<String>any())).thenReturn(mockedQuery);
	    when(mockedQuery.getResultList()).thenReturn(list);
		
		List<Employee> empList = impl.findAll();
		assertEquals(3, empList.size());
	}

	@Test
	public void getFindOneTest() {
		Employee emp = createEmployeeAndDepartment(1, "Gajendra", "Kumar", "HR");

		when(entityManager.find(Employee.class, 1)).thenReturn(emp);

		Employee empResult = impl.findOne(1);
		assertEquals("Gajendra", empResult.getFirstName());
		assertEquals("Kumar", empResult.getLastName());
	}

	private Employee createEmployeeAndDepartment(Integer id, String firstName, String lastName, String deptName) {
		return new Employee(id, firstName, lastName, createDepartment(deptName));
	}

	private Department createDepartment(String deptName) {
		return new Department(deptName);
	}
}
