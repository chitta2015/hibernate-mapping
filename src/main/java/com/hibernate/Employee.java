package com.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries(	
		{
			@NamedQuery(				
				name="findAllEmployees",
				query="from Employee"			
				),
		@NamedQuery(				
				name="findAllEmployeeNames",
				query="select e.employeeName from Employee e"			
				)
			}
		)
@Entity
public class Employee {
	
	@Id
	@Column(name="empId")
	private long employeeId;
	@Column(name="empName",updatable = false)
	private String employeeName;
	@Column(name="empCity", length = 40)
	private String city;
	@Column(name="desg", nullable = false)
	private String designation;
	@Column(name="empSalary")
	private double salary;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(long employeeId, String employeeName, String city, String designation, double salary) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.city = city;
		this.designation = designation;
		this.salary = salary;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", city=" + city
				+ ", designation=" + designation + ", salary=" + salary + "]";
	}
	
	

}
