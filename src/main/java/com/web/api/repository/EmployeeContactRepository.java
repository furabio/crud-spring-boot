package com.web.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.api.model.Employee;
import com.web.api.model.EmployeeContact;

public interface EmployeeContactRepository extends JpaRepository<EmployeeContact, Long>{

	List<EmployeeContact> findByEmployeeId(Employee employee);
}
