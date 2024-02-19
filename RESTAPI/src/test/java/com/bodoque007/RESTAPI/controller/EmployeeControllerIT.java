package com.bodoque007.RESTAPI.controller;

import com.bodoque007.RESTAPI.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EmployeeControllerIT {
    @Autowired
    EmployeeRestController employeeRestController;

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
        mockMvc.perform(get("/employees")
                .queryParam("pageSize", "8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(8)));
    }

}
