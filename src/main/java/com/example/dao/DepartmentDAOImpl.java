package com.example.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.beans.Department;

@Transactional
@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Department findOne(Integer id) {
		System.out.println("%%%%%%%%%%%%% Gaju ************** ID in DAOImpl: " +id);
		return entityManager.find(Department.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findAll() {
		String hql = "FROM Department as dept ORDER BY dept.id";
		return (List<Department>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Department save(Department dept) {
		entityManager.persist(dept);
		return dept;
	}

	@Override
	public Department update(Department dept) {
		Department department = findOne(dept.getId());
		department.setName(dept.getName());
		entityManager.flush();
		return department;
	}

	@Override
	public void delete(Integer id) {
		entityManager.remove(findOne(id));
	}
}