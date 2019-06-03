package com.example.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.beans.Employee;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Employee getEmployeeByID(Integer employeeID) {
		System.out.println("%%%%%%%%%%%%% Gaju ************** ID in DAOImpl: " +employeeID);
		return entityManager.find(Employee.class, employeeID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		String hql = "FROM Employee as emp ORDER BY emp.employeeID";
		return (List<Employee>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void addEmployee(Employee emp) {
		entityManager.persist(emp);
	}

	@Override
	public void updateEmployee(Employee emp) {
		Employee employee = getEmployeeByID(emp.getEmployeeID());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		entityManager.flush();
	}

	@Override
	public void deleteEmployee(Integer employeeID) {
		entityManager.remove(getEmployeeByID(employeeID));
	}
}