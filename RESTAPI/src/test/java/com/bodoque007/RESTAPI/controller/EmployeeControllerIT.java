package com.bodoque007.RESTAPI.controller;

import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EmployeeControllerIT {
    @Autowired
    EmployeeController employeeController;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testFindAllPageSize8() throws Exception {
        mockMvc.perform(get(EmployeeController.EMPLOYEE_ENDPOINT)
                .queryParam("size", "8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(8)));
    }
    @Rollback
    @Transactional
    @Test
    void deleteByIdFound() {
        Employee employee = employeeRepository.findAll().get(0);

        ResponseEntity responseEntity = employeeController.deleteEmployee(employee.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        // Employee was indeed deleted
        assertThat(employeeRepository.findById(employee.getId()).isEmpty());
    }

    @Rollback
    @Transactional
    @Test
    void updateEmployee() {
        Employee employee = employeeRepository.findAll().get(0);
        final String updatedName = "UPDATED NAME";
        employee.setFirstName(updatedName);

        ResponseEntity<Void> responseEntity =  employeeController.updateEmployee(employee, employee.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        // Check if the employee's name actually changed in the database.
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        assertThat(updatedEmployee.getFirstName()).isEqualTo(updatedName);
    }

    @Rollback
    @Transactional
    @Test
    void saveNewEmployee() {
        Employee employee = Employee.builder().firstName("Ada").email("lovelace@gmail.com").build();

        ResponseEntity responseEntity = employeeController.addEmployee(employee);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationID = responseEntity.getHeaders().getLocation().getPath().split("/");
        int savedId = Integer.parseInt(locationID[2]);
        Employee savedEmployee = employeeRepository.findById(savedId).get();
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void findEmployee() {
        Employee employee = employeeRepository.findAll().get(0);
        assertThat(employee).isNotNull();
    }
}
