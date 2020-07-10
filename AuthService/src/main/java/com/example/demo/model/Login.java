package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Login {
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="emp_id", updatable=false, nullable=false)
	public int empId;
	
	@Column(name="company_email", updatable=false, nullable=false)
	public String companyEmail;
	
	@Column(nullable=false)
	public String password;
	
	//@Column(nullable=false)
	//private String role;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}*/
	
}
