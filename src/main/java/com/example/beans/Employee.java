package com.example.beans;

import javax.persistence.Entity;

@Entity
public class Employee {

	private String firstName;
	private String lastName;
	private Integer employeeID;
	
	public Employee() {
	}
	
	public Employee(String firstName, String lastName, Integer employeeID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeID = employeeID;
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
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
}
