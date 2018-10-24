package com.web.api.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Employee extends AbstractEntity{

	private String name;
	private String cpf;
	
	@Embedded
	private Address address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
