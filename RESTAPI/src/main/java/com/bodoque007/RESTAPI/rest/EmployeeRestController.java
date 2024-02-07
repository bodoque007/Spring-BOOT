package com.bodoque007.RESTAPI.rest;

import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = {"/employee", "/employee/"})
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employee_id}")
    public ResponseEntity<Employee> getByID(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }
    @PostMapping(value = {"/employee", "/employee/"})
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employee/{employee_id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee, @PathVariable int employee_id) {
        Employee employeeToUpdate = employeeService.findById(employee_id);
        if (employeeToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        employeeToUpdate.setFirstName(employee.getFirstName());
        employeeToUpdate.setLastName(employee.getLastName());
        employeeToUpdate.setEmail(employee.getEmail());
        employeeService.save(employeeToUpdate);
        return ResponseEntity.noContent().build(); // Success
    }

    @DeleteMapping("/employee/{employee_id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteById(employee_id);
        return ResponseEntity.noContent().build();
    }
}

