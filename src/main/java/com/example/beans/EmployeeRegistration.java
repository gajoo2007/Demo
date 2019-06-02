package com.example.beans;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRegistration {
	    private List<Employee> employeeRecords;
	    private static EmployeeRegistration empReg = null;
	    private EmployeeRegistration(){
	    	employeeRecords = new ArrayList<Employee>();
	    }
	    public static EmployeeRegistration getInstance() {
	        if(empReg == null) {
	        	return new EmployeeRegistration();
	        } else {
	        	return empReg;
	        }
	    }
	    
	    public void add(Employee emp) {
	    	employeeRecords.add(emp);
	    }
	
	    public String updateEmployee(Employee emp) {
	    	for(int i = 0; i < employeeRecords.size(); i++) {
	            Employee empN = employeeRecords.get(i);
	            if(empN.getEmployeeID() == emp.getEmployeeID()) {
	              employeeRecords.set(i, emp);
	              return "Update successful";
	            }
	        }
	    	return "Update un-successful";
	    }
	
	    public String deleteEmployee(Integer empID) {
	    	for(int i = 0; i < employeeRecords.size(); i++) {
	            Employee empN = employeeRecords.get(i);
	            if(empN.getEmployeeID() == empID){
	              employeeRecords.remove(i);
	              return "Delete successful";
	            }
	        }
	    	return "Delete un-successful";
	    }
	    
	    public List<Employee> getEmployeeRecords() {
	    	return employeeRecords;
	    }
}
