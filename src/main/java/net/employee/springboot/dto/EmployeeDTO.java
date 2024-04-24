package net.employee.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class EmployeeDTO {
	
	private long id;

	private String firstName;
	
	private String lastName;	

	private String emailId;
	
	public EmployeeDTO() {
		
	}
	public Long getId() {
		return this.id;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Object getLastName() {
		return this.lastName;
	}

	
	public void setId(long id2) {
		this.id=id2;
	}
	public void setEmailId(String emailId2) {
		this.emailId = emailId2;
	}
	public void setFirstName(String firstName2) {
		this.firstName = firstName2;
		
	}
	public void setLastName(String lastName2) {
		this.lastName = lastName2;
	}
	
	public EmployeeDTO(String firstName, String lastName, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	
	public EmployeeDTO(Long id2, String emailId2, String firstName2, Object lastName2) {
		this.id=id2;
		this.firstName = firstName2;
		this.lastName = (String) lastName2;
		this.emailId = emailId2;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ "]";
	}
	

}
