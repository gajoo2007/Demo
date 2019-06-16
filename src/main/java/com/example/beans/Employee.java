package com.example.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "department_id", "name" }) })
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String firstName;
	private String lastName;
 
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;
 
 
	public Employee() {
	}
	
	public Employee(Integer id, String firstName, String lastName, Department department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}
 
	public Employee(String firstName, String lastName, Department department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}
 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
