package net.employee.springboot.mapper;

import net.employee.springboot.dto.EmployeeDTO;
import net.employee.springboot.model.Employee;

public class EmployeeMapper {

	
	public static EmployeeDTO mapToEmployeeDTO(Employee emp) {
		EmployeeDTO empdto=new EmployeeDTO();		
		empdto.setId(emp.getId());
		empdto.setEmailId(emp.getEmailId());
		empdto.setFirstName(emp.getFirstName());
		empdto.setLastName(emp.getLastName());
		return empdto;
	}
	
	public static Employee mapToEmployee(EmployeeDTO empdto) {
		Employee emp=new Employee();		
		emp.setId(empdto.getId());
		emp.setEmailId(empdto.getEmailId());
		emp.setFirstName(empdto.getFirstName());
		emp.setLastName((String) empdto.getLastName());
		return emp;
	}
}
