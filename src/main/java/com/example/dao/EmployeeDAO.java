package com.example.dao;

import java.util.List;

import com.example.beans.Employee;

public interface EmployeeDAO {

	Employee save(Employee employee);
	
	Employee update(Employee employee);

	List<Employee> findAll();

	Employee findOne(Integer id);

	void delete(Integer id);

}
