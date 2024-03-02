package com.bodoque007.RESTAPI.controller;

import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeeRestController.class)
public class EmployeeController {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EmployeeService employeeService;

    @Captor
    ArgumentCaptor<Integer> integerArgumentCaptor;

    @Captor
    ArgumentCaptor<Employee> employeeArgumentCaptor;

    @Test
    void findById() throws Exception {
        Employee employee = Employee.builder().firstName("Eren").id(1).email("yageya@gmail.com").build();

        given(employeeService.findById(employee.getId())).willReturn(employee);

        mockMvc.perform(get(EmployeeRestController.EMPLOYEE_ENDPOINT_ID, employee.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(employee.getId())))
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())));
    }

    @Test
    void findByIdNotFound() throws Exception {
        given(employeeService.findById(any(Integer.class))).willReturn(null);

        mockMvc.perform(get(EmployeeRestController.EMPLOYEE_ENDPOINT_ID, 1))
                .andExpect(status().isNotFound());
    }

    @Test
    void addEmployee() throws Exception {
        Employee employee = Employee.builder().firstName("Eren").email("yageya@gmail.com").build();
        given(employeeService.save(any(Employee.class))).willReturn(employee);

        mockMvc.perform(post(EmployeeRestController.EMPLOYEE_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}
