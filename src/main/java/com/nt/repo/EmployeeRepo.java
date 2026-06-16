package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	List<Employee> findByNameContainingIgnoreCase(String name);

	List<Employee> findByDepartmentContainingIgnoreCase(String department);
    
}
