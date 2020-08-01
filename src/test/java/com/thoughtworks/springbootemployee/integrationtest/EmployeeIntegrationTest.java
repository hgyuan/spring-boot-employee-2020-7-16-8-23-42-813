package com.thoughtworks.springbootemployee.integrationtest;


import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @BeforeEach
    void initEntity() {
        Company company = new Company();
        company.setName("ri");
        companyRepository.save(company);
        Employee employee = new Employee("ri", 12, "male", company);
        employeeRepository.save(employee);
    }

    @Test
    void should_return_status_200_when_add_employee_given_employee_dto() throws Exception {
        Company company = companyRepository.findAll().stream().findFirst().get();
        String employeeDto = "{\n" +
                "\t\"name\":\"ri\",\n" +
                "\t\"age\": 11,\n" +
                "\t\"gender\":\"male\",\n" +
                "\t\"companyId\":" + company.getId() + "\n" +
                "}";
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeDto))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_return_status_accepted_when_update_employee_given_employee_dto() throws Exception {
        Company company = companyRepository.findAll().stream().findFirst().get();
        Employee employee = employeeRepository.findAll().stream().findFirst().get();
        String employeeDto = "{\n" +
                "        \"id\": " +
                employee.getId() +
                "         ,\n" +
                "        \"name\": \"chengcheng111\",\n" +
                "        \"age\": 54,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"companyId\":" +
                company.getId() +
                "\n" +
                " }";
        mockMvc.perform(put("/employees/" + employee.getId()).contentType(MediaType.APPLICATION_JSON).content(employeeDto))
                .andExpect(status().isAccepted());
    }

    @Test
    void should_return_status_200_when_delete_employee_given_employee_employee_id() throws Exception {
        Employee employee = employeeRepository.findAll().stream().findFirst().get();
        mockMvc.perform(delete("/employees/" + employee.getId()))
                .andExpect(status().isOk());
        assertEquals(Optional.empty(), employeeRepository.findById(employee.getId()));
    }

    @Test
    void should_return_status_200_when_employees_given_4_employee_unpage_false() throws Exception {
        tearDown();
        add4EmployeeToDb();
        mockMvc.perform(get("/employees?unpaged=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value(2));
    }

    @Test
    void should_return_4_employee_when_find_employees_by_page_given_4_employee_unpaged_true() throws Exception {
        tearDown();
        add4EmployeeToDb();
        mockMvc.perform(get("/employees?unpaged=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value(0))
                .andExpect(jsonPath("[3].name").value(3));
    }

    @Test
    void should_not_contain_female_when_find_employee_by_gender_given_male() throws Exception {
        String result = mockMvc.perform(get("/employees?gender=male"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        boolean isContain = result.contains("female");
        assertFalse(isContain);
        isContain = result.contains("male");
        assertTrue(isContain);
    }

    @Test
    void should_return_employee_when_query_employee_by_id_given_employee_id() throws Exception {
        Employee employee = employeeRepository.findAll().stream().findFirst().get();
        mockMvc.perform(get("/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(employee.getName()))
                .andExpect(jsonPath("id").value(employee.getId()));
    }

    private void add4EmployeeToDb(){
        for (int i = 0; i < 4; i++) {
            Company company = new Company();
            company.setName("ri");
            companyRepository.save(company);
            Employee employee = new Employee(String.valueOf(i), 12, "male", company);
            employeeRepository.save(employee);
        }
    }
}
