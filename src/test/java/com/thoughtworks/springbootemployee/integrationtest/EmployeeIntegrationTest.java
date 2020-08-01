package com.thoughtworks.springbootemployee.integrationtest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_status_200_when_add_employee_given_employee_dto() throws Exception {
        String employeeDto = "{\n" +
                "\t\"name\":\"ri\",\n" +
                "\t\"age\": 11,\n" +
                "\t\"gender\":\"male\",\n" +
                "\t\"companyId\":1\n" +
                "}";
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeDto))
                .andExpect(status().is2xxSuccessful());
    }
}
