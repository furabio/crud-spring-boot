package com.web.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.api.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
}
