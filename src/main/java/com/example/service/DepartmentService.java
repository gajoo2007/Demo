package com.example.service;

import java.util.List;

import com.example.beans.Department;

public interface DepartmentService {

	List<Department> findAll();

	Department save(Department department);

	Department update(Department department);

	void delete(Integer id);

}