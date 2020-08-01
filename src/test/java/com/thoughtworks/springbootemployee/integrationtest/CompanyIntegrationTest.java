package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @BeforeEach
    void initEntity() {
        Company company = new Company();
        company.setName("ri");
        companyRepository.save(company);
    }

    @Test
    void should_return_404_when_query_company_given_0_company() throws Exception {
        mockMvc.perform(get("/companies/1")).andExpect(status().isNotFound());
    }

    @Test
    void should_return_company_when_get_company_given_company() throws Exception {
        String company = "{\n" +
                "\t\"name\":\"ri\"\n" +
                "}";
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(company))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("name").value("ri"));
        List<Company> companies = companyRepository.findAll();
        assertEquals(1, companies.size());
    }

    @Test
    void should_return_not_found_when_get_company_given_company_id_1() throws Exception {
        mockMvc.perform(get("/companies/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_not_found_when_get_employees_given_company_id_1() throws Exception {
        mockMvc.perform(get("/companies/1/employees"))
                .andExpect(status().isNotFound());
    }
    @Test
    void should_return_status_200_when_add_company_given_company() throws Exception {
        String company = "{\n" +
                "    \"name\": \"oliver\"\n" +
                "}";
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(company))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    void should_return_status_200_when_delete_company_given_company() throws Exception {
        Company company = companyRepository.findAll().stream().findFirst().get();
        mockMvc.perform(delete("/companies/" + company.getId()))
                .andExpect(status().isOk());
        assertEquals(Optional.empty(), companyRepository.findById(company.getId()));
    }
    @Test
    void should_return_status_200_when_update_company_given_newCompany() throws Exception {
        Company company = companyRepository.findAll().stream().findFirst().get();
        String newCompany = "{\n" +
                "    \"name\": \"oli\"\n" +
                "}";
        mockMvc.perform(put("/companies/" + company.getId()).contentType(MediaType.APPLICATION_JSON).content(newCompany))
                .andExpect(status().isOk());
        Company newCompany1 = companyRepository.findAll().stream().findFirst().get();
        assertEquals("oli",newCompany1.getName());
    }
}