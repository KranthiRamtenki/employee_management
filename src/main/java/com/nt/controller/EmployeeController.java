package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.entity.Employee;
import com.nt.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService empservice;
    

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/save")
    public String saveEmployee(Employee employee, Model model) {

        try {
            empservice.empReg(employee);

            model.addAttribute("message",
                    "Employee Registered Successfully!");

        } catch (Exception e) {

            model.addAttribute("message",
                    "Email already exists! Please use another email.");
        }

        return "register";
    }
    
    
    
    @GetMapping("/all")
    public String getAllEmployees(Model model) {

        List<Employee> employees = empservice.getEmployees();

        model.addAttribute("employees", employees);

        return "viewEmployees";
    }
    
    
    
    @GetMapping("/search")
    public String searchEmployee(
            @RequestParam String searchType,
            @RequestParam String keyword,
            Model model) {

        List<Employee> employee;

        if ("name".equalsIgnoreCase(searchType)) {
            employee = empservice.searchByName(keyword);

        } else if ("department".equalsIgnoreCase(searchType)) {
            employee = empservice.searchByDepartment(keyword);

        } else {
            employee = List.of(); // empty result for invalid input
        }

        model.addAttribute("employee", employee);
        return "findEmployee";
    }
    
    
    
    @GetMapping("/findEmployee")
    public String openFindEmployeePage() {
        return "findEmployee";
    }
    
    
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {

        Employee emp = empservice.getById(id);
        model.addAttribute("employee", emp);

        return "edit-employee";  
    }
    
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee) {

    	empservice.updateEmployee(employee);
        return "redirect:/employee/all";
    }
    
    
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        empservice.deleteEmp(id);
        return "redirect:/employee/all";
    }
    
   
}