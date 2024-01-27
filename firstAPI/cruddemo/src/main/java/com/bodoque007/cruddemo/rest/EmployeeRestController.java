package com.bodoque007.cruddemo.rest;

import com.bodoque007.cruddemo.entity.Employee;
import com.bodoque007.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("employees/{employee_id}")
    public Employee getByID(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            throw new RuntimeException("Employee not found!");
        }
        return employee;
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{employee_id}")
    public String deleteEmployee(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            throw new RuntimeException("Employee not in database.");
        }
        employeeService.deleteById(employee_id);
        return "Deleted employee with ID " + employee_id;
    }
}
