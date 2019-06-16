package com.example.dao;

import java.util.List;

import com.example.beans.Department;

public interface DepartmentDAO {

	Department save(Department department);

	List<Department> findAll();

	Department findOne(Integer id);

	Department update(Department dept);

	void delete(Integer id);

}
