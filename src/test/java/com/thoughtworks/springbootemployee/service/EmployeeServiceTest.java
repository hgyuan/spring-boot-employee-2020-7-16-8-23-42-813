package com.thoughtworks.springbootemployee.service;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    EmployeeServicImpl employeeServic;
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void should_return_employee_when_addEmployee_given_1_Employee_1_company() {
        //given
        Company company = new Company(1, "OOCL");
        Employee employee = new Employee("Richard", 18, "male", company);

        when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        //when
        Employee returnEmployee = employeeServic.addEmployee(employee);

        //then
        assertEquals(employee.getName(), returnEmployee.getName());

    }

    @Test
    void should_return_size_0_when_getEmployees_given_0_employee() {

        //given
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>(0));
        //when
        List<Employee> employees = employeeServic.findAll();

        //then
        assertEquals(0, employees.size());
    }

    @Test
    void should_return_size_2_when_query_employee_by_page_given_page_1_size_2_4employee() {
        //given
        int page = 1;
        int size = 2;
        Pageable pageRequest = PageRequest.of(1, 2);
        List<Employee> employees = new ArrayList<>(2);
        Page<Employee> pages = new PageImpl<>(employees);
        when(employeeRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pages);
        //when
        Page<Employee> pageOfEmployees = employeeServic.queryEmployeeByPage(pageRequest);

        //then
        assertEquals(2, pageOfEmployees.getContent().size());

    }
}
