package com.example.service;

import java.util.List;

import com.example.beans.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();

	Employee getEmployeeByID(Integer employeeID);

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(Integer employeeID);
}