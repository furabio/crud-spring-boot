package com.web.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.api.model.Employee;
import com.web.api.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok(employeeRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).get();
		
		return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("findByName/{name}")
	public ResponseEntity<?> getEmployeeFindByName(@PathVariable String name) {
		return ResponseEntity.ok(employeeRepository.findByNameIgnoreCaseContaining(name));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Employee employee) {
		return ResponseEntity.created(null).body(employeeRepository.save(employee));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).get();
		
		if ( employee == null ) {
			return ResponseEntity.notFound().build();
		}
		
		employeeRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee employee) {
		Employee employeeSaved = employeeRepository.findById(id).get();
		
		if ( employeeSaved == null ) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(employee, employeeSaved);
		employeeRepository.save(employeeSaved);
		return ResponseEntity.ok().build();
	}
	
}
