package com.bodoque007.RESTAPI.controller;

import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.service.EmployeeService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    public static final String EMPLOYEE_ENDPOINT = "/employees";
    public static final String EMPLOYEE_ENDPOINT_ID = EMPLOYEE_ENDPOINT + "/{employee_id}";
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            description = "Gets all employees."
    )
    @GetMapping(EMPLOYEE_ENDPOINT)
    public Page<Employee> findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size ) {
        return employeeService.findAll(page, size);
    }

    @GetMapping(EMPLOYEE_ENDPOINT_ID)
    public ResponseEntity<Employee> getByID(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok(employee);
    }
    @PostMapping(EMPLOYEE_ENDPOINT)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        Employee savedEmployee = employeeService.save(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", EMPLOYEE_ENDPOINT + "/" + (savedEmployee.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(EMPLOYEE_ENDPOINT_ID)
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee, @PathVariable int employee_id) {
        if (employeeService.updateById(employee_id, employee) == null) {
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build(); // Success
    }
    @DeleteMapping(EMPLOYEE_ENDPOINT_ID)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            throw new NotFoundException();
        }
        employeeService.deleteById(employee_id);
        return ResponseEntity.noContent().build();
    }
}

