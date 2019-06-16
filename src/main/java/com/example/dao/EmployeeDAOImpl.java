package com.example.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.beans.Employee;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Employee findOne(Integer id) {
		System.out.println("%%%%%%%%%%%%% Gaju ************** ID in DAOImpl: " +id);
		return entityManager.find(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll() {
		String hql = "FROM Employee as emp ORDER BY emp.id";
		Query query = entityManager.createQuery(hql);
		List<Employee> empList = query.getResultList();
		return empList;
	}

	@Override
	public Employee save(Employee emp) {
		entityManager.persist(emp);
		return emp;
	}

	@Override
	public Employee update(Employee emp) {
		Employee employee = findOne(emp.getId());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		entityManager.flush();
		return employee;
	}

	@Override
	public void delete(Integer id) {
		entityManager.remove(findOne(id));
	}
}