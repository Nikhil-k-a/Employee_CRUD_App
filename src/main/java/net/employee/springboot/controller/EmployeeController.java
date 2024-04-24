package net.employee.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.employee.springboot.dto.EmployeeDTO;
import net.employee.springboot.exception.ResourceNotFoundException;
import net.employee.springboot.mapper.EmployeeMapper;
import net.employee.springboot.model.Employee;
import net.employee.springboot.repository.EmployeeRepository;
import net.employee.springboot.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees in pagination fashion, have to provide page and size as parameters while requesting
	@GetMapping("/employees")
	public PagedModel<EmployeeDTO> getAllEmployees(@RequestParam("page") int page,@RequestParam("size") int size){
		return employeeService.fetchCustomers(page, size);
	}
	
	// create employee rest api
	@PostMapping("/employees")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDto) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.createEmployee(employeeDto));
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
		return  ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(id));
	}
	
	// update employee rest api	
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(id,employeeDetails));
	}
	
	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		Map<String, Boolean> response = new HashMap<>();
		return ResponseEntity.ok(employeeService.deleteEmployee(id));
	}
	
	
}
