package com.nt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.repo.EmployeeRepo;
import com.nt.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

  @Autowired
  private EmployeeRepo repo;
  
	
	@Override
	public Employee empReg(Employee employee) {
		// TODO Auto-generated method stub
		return repo.save(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee) {

	    Employee existing = repo.findById(employee.getEmpId())
	            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employee.getEmpId()));

	    existing.setName(employee.getName());
	    existing.setEmail(employee.getEmail());
	    existing.setDepartment(employee.getDepartment());
	    existing.setDesignation(employee.getDesignation());
	    existing.setSalary(employee.getSalary());
	   // existing.setJoinDate(employee.getJoinDate());

	    return repo.save(existing);
	}

	@Override
	public void deleteEmp(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public List<Employee> searchByName(String name) {
		// TODO Auto-generated method stub
		return repo.findByNameContainingIgnoreCase(name);
	}

	@Override
	public List<Employee> searchByDepartment(String department) {
		// TODO Auto-generated method stub
		return repo.findByDepartmentContainingIgnoreCase(department);
	}

	@Override
	public Employee getById(Long id) {
		// TODO Auto-generated method stub
		return repo.getById(id);
	}
	

}
