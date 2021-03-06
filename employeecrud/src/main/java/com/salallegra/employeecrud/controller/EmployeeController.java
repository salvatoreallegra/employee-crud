package com.salallegra.employeecrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salallegra.employeecrud.exception.ResourceNotFoundException;
import com.salallegra.employeecrud.model.Employee;
import com.salallegra.employeecrud.repository.EmployeeRepository;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repository;
	
	//gets all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		return repository.findAll();
	}
	
	//post one employee to database
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return repository.save(employee);
	}
	
	//Get an employee by Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> GetEmployeeById(@PathVariable Long id) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist"));
		return ResponseEntity.ok(employee);
		
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist"));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = repository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

}
