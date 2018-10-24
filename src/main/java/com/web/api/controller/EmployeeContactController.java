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

import com.web.api.model.EmployeeContact;
import com.web.api.repository.EmployeeContactRepository;

@RestController
@RequestMapping("/api/e-contacts")
public class EmployeeContactController<S> {
	
	@Autowired
	private EmployeeContactRepository ecRepository;
	
	@GetMapping
	public ResponseEntity<?> listAll() {
		return ResponseEntity.ok(ecRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		EmployeeContact ec = ecRepository.findById(id).get();
		
		return ec != null ? ResponseEntity.ok(ec) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody EmployeeContact employeeContact) {
		return ResponseEntity.created(null).body(ecRepository.save(employeeContact));
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		EmployeeContact ec = ecRepository.findById(id).get();
		
		if ( ec == null ) {
			return ResponseEntity.notFound().build();
		}
			
		ecRepository.deleteById(id);
		return ResponseEntity.noContent().build(); 
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmployeeContact ec) {
		EmployeeContact ecSaved = ecRepository.findById(id).get();
		
		if (ecSaved == null) {
			return ResponseEntity.notFound().build();	
		}
		
		BeanUtils.copyProperties(ec, ecSaved);
		ecRepository.save(ecSaved);
		
		return ResponseEntity.ok().build();
	}
	
	
}
