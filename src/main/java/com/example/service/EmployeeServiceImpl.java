package com.example.service;

import java.util.List;
import com.example.beans.Employee;
import com.example.dao.EmployeeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public Employee getEmployeeByID(Integer employeeID) {
		return employeeDAO.getEmployeeByID(employeeID);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeDAO.addEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDAO.updateEmployee(employee);
	}

	@Override
	public void deleteEmployee(Integer employeeID) {
		employeeDAO.deleteEmployee(employeeID);
	}
}
