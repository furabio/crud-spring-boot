package com.web.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Contact extends AbstractEntity{
	
	@Column(unique=true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
