package com.bodoque007.RESTAPI.controller;

import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.service.EmployeeService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class EmployeeRestController {
    private final EmployeeService employeeService;
    private static final String EMPLOYEE_ENDPOINT = "/employees";
    private static final String EMPLOYEE_ENDPOINT_ID = EMPLOYEE_ENDPOINT + "/{employee_id}";
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = {EMPLOYEE_ENDPOINT, EMPLOYEE_ENDPOINT + "/"})
    public Page<Employee> findAll(@RequestParam(required = false) Integer pageNumber,
                                  @RequestParam(required = false) Integer pageSize) {
        return employeeService.findAll(pageNumber, pageSize);
    }

    @GetMapping(EMPLOYEE_ENDPOINT_ID)
    public ResponseEntity<Employee> getByID(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }
    @PostMapping(value = {EMPLOYEE_ENDPOINT, EMPLOYEE_ENDPOINT + "/"})
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build(); // Success
    }
    @DeleteMapping(EMPLOYEE_ENDPOINT_ID)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int employee_id) {
        Employee employee = employeeService.findById(employee_id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteById(employee_id);
        return ResponseEntity.noContent().build();
    }
}

