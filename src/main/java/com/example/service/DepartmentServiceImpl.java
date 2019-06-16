package com.example.service;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import com.example.beans.Department;
import com.example.dao.DepartmentDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDAO departmentDAO;

	@Autowired
	public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public Department save(Department department) {
		if (department.getId() != null) {
			throw new EntityExistsException("Already exist!");
		}
		return departmentDAO.save(department);
	}

	public Department update(Department department) {
		if (department.getId() != null) {
			throw new EntityNotFoundException("No entity exist");
		}
		return departmentDAO.save(department);
	}

	public List<Department> findAll() {
		return departmentDAO.findAll();
	}

	public Department findOne(Integer id) {
		return departmentDAO.findOne(id);
	}

	public void delete(Integer id) {
		departmentDAO.delete(id);
	}
}