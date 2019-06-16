package com.example.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.example.beans.Employee;
import com.example.dao.EmployeeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDAO employeeDAO;

	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public Employee save(Employee employee) {
		if (employee.getId() != null) {
			throw new EntityExistsException("Already exist!");
		}
		return employeeDAO.save(employee);
	}

	public Employee update(Employee employee) {
		if (employee.getId() != null) {
			throw new EntityNotFoundException("No entity exist!");
		}
		return employeeDAO.save(employee);
	}

	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	public Employee findOne(Integer id) {
		return employeeDAO.findOne(id);
	}

	public void delete(Integer id) {
		employeeDAO.delete(id);
	}
}
