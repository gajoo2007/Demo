package com.example.dao;

import java.util.List;

import com.example.beans.Employee;

public interface EmployeeDAO {
	List<Employee> getAllEmployees();

	Employee getEmployeeByID(Integer employeeID);

	void addEmployee(Employee emp);

	void updateEmployee(Employee emp);

	void deleteEmployee(Integer employeeID);

}
