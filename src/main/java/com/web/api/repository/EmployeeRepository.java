package com.web.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByNameIgnoreCaseContaining(String name);
}
