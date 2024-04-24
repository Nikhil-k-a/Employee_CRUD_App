package net.employee.springboot.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import lombok.AllArgsConstructor;
import net.employee.springboot.dto.EmployeeDTO;
import net.employee.springboot.exception.ResourceNotFoundException;
import net.employee.springboot.mapper.EmployeeMapper;
import net.employee.springboot.model.Employee;
import net.employee.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	PagedResourcesAssembler<Employee> assembler;
	
	public PagedModel<EmployeeDTO> fetchCustomers(int pageNo, int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Employee> pagedResult= employeeRepository.findAll(pageable);
		PagedModel<EntityModel<Employee>> pagedModel = assembler.toModel(pagedResult);
		Iterator<EntityModel<Employee>> it = pagedModel.getContent().iterator();
		List<EmployeeDTO> EmployeeDTOs = new ArrayList<>();
		while(it.hasNext())
		{
			EntityModel<Employee> ent = it.next();
			Employee emp = ent.getContent();
			if(emp.getFirstName()!=null)
				EmployeeDTOs.add(EmployeeMapper.mapToEmployeeDTO(emp));
		}
		List<EmployeeDTO> EmployeeDTOs2= EmployeeDTOs.stream().filter(e->e.getFirstName().startsWith("J")).collect(Collectors.toList());
		System.out.println("----------------------"+EmployeeDTOs2);
		int EmployeeDTOs3= (int)EmployeeDTOs.stream().filter(e->e.getFirstName().startsWith("J")).count();
		System.out.println("----------------------"+EmployeeDTOs3);
		Stream<String> EmployeeDTOs4= EmployeeDTOs.stream().map(e->e.getFirstName().toUpperCase());
		System.out.println("----------------------"+EmployeeDTOs4.toArray().toString());
		return	PagedModel.of(EmployeeDTOs, pagedModel.getMetadata(), pagedModel.getLinks());	
	}

	/*public PagedModel<EmployeeDTO> fetchCustomers(int pageNo, int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Employee> pagedResult= employeeRepository.findAll(pageable);
		PagedModel<EntityModel<Employee>> pagedModel = assembler.toModel(pagedResult);
		Iterator<EntityModel<Employee>> it = pagedModel.getContent().iterator();
		List<EmployeeDTO> EmployeeDTOs = new ArrayList<>();
		while(it.hasNext())
		{
			EntityModel<Employee> ent = it.next();
			Employee emp = ent.getContent();

			EmployeeDTOs.add(EmployeeMapper.mapToEmployeeDTO(emp));
		}
		List<EmployeeDTO> EmployeeDTOs2= EmployeeDTOs.stream().filter(e->e.getFirstName().startsWith("j")).collect(Collectors.toList());
		System.out.println(EmployeeDTOs2);
		return	PagedModel.of(EmployeeDTOs, pagedModel.getMetadata(), pagedModel.getLinks());
	}*/


	
	public EmployeeDTO createEmployee(EmployeeDTO empDto) {
		System.out.println(empDto.toString());
		Employee emp=EmployeeMapper.mapToEmployee(empDto);
		System.out.println(emp.toString());
		Employee savedEmp=employeeRepository.save(emp);
		System.out.println(savedEmp.toString());
		//return modelMapper.map(savedEmp, EmployeeDTO.class);
		return EmployeeMapper.mapToEmployeeDTO(savedEmp);
	}
	
	public EmployeeDTO getEmployeeById(Long id) {
		Employee res=employeeRepository.getById(id);
		if(res==null)
			throw new ResourceNotFoundException("Employee not exist with id :" + id);

		return EmployeeMapper.mapToEmployeeDTO(res);
	}
	
	public EmployeeDTO updateEmployee(Long id,  Employee employeeDetails) {
		Employee employee = employeeRepository.getById(id);
		if(employee != null) {
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		}
		else {
			 throw new ResourceNotFoundException("Employee not exist with id :" + id);
		}
		Employee updatedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
	}
	
	public String deleteEmployee(Long id) {
		Employee employee = employeeRepository.getById(id);
		if(employee != null) {
		employeeRepository.delete(employee);
		}
		else {
			 throw new ResourceNotFoundException("Employee not exist with id :" + id);
		}
		return "Employee deleted successfully";
	}
}
