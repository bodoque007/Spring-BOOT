package com.bodoque007.RESTAPI.controller;

import com.bodoque007.RESTAPI.config.SecurityConfig;
import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.service.EmployeeService;
import com.bodoque007.RESTAPI.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest({EmployeeController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EmployeeService employeeService;


    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void findById() throws Exception {
        Employee employee = Employee.builder().firstName("Eren").id(1).email("yageya@gmail.com").build();

        given(employeeService.findById(employee.getId())).willReturn(employee);

        MvcResult result = this.mockMvc.perform(post("/token")
                        .with(httpBasic("eren", "password")))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        mockMvc.perform(get(EmployeeController.EMPLOYEE_ENDPOINT_ID, employee.getId())
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(employee.getId())))
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())));
    }

    @Test
    void findByIdNotFound() throws Exception {
        given(employeeService.findById(any(Integer.class))).willReturn(null);

        MvcResult result = this.mockMvc.perform(post("/token")
                        .with(httpBasic("eren", "password")))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        mockMvc.perform(get(EmployeeController.EMPLOYEE_ENDPOINT_ID, 1)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());
    }

    @Test
    void addEmployee() throws Exception {
        Employee employee = Employee.builder().firstName("Eren").email("yageya@gmail.com").build();
        given(employeeService.save(any(Employee.class))).willReturn(employee);

        MvcResult result = this.mockMvc.perform(post("/token")
                        .with(httpBasic("eren", "password")))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        mockMvc.perform(post(EmployeeController.EMPLOYEE_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}
