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

import com.web.api.model.Contact;
import com.web.api.repository.ContactRepository;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@GetMapping
	public ResponseEntity<?> listAll() {
		return ResponseEntity.ok(contactRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Contact contact = contactRepository.findById(id).get();
		
		return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Contact contact) {
		return ResponseEntity.created(null).body(contactRepository.save(contact));
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Contact contact = contactRepository.findById(id).get();
		
		if ( contact == null ) {
			return ResponseEntity.notFound().build();
		}
			
		contactRepository.deleteById(id);
		return ResponseEntity.noContent().build(); 
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Contact contact) {
		Contact contactSaved = contactRepository.findById(id).get();
		
		if (contactSaved == null) {
			return ResponseEntity.notFound().build();	
		}
		
		BeanUtils.copyProperties(contact, contactSaved);
		contactRepository.save(contactSaved);
		
		return ResponseEntity.ok().build();
	}
	
}
