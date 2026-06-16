package com.nt.service;

import java.util.List;

import com.nt.entity.Employee;

public interface IEmployeeService {

	Employee empReg(Employee employee);

    List<Employee> getEmployees();
    
    public List<Employee> searchByName(String name);

    public List<Employee> searchByDepartment(String department);

    Employee updateEmployee(Employee employee);

    void deleteEmp(Long id);

	Employee getById(Long id);

}
