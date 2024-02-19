package com.bodoque007.RESTAPI.controller;

import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = {"/employees", "/employees/"})
    public Page<Employee> findAll(@RequestParam(required = false) Integer pageNumber,
                                  @RequestParam(required = false) Integer pageSize) {
        return employeeService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/employees/{employee_id}")
    public ResponseEntity<Employee> getByID(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }
    @PostMapping(value = {"/employees", "/employees/"})
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        Employee savedEmployee = employeeService.save(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/employee/" + Integer.toString(savedEmployee.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{employee_id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee, @PathVariable int employee_id) {
        if (employeeService.updateById(employee_id, employee) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build(); // Success
    }
    @DeleteMapping("/employees/{employee_id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteById(employee_id);
        return ResponseEntity.noContent().build();
    }
}

