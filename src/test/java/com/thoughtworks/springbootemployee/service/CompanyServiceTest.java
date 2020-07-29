package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.entity.Employee;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void should_return_2_when_findAll_given_company_id_2_employee() {
        //given
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("Female", 1, 4, "111"));
        list.add(new Employee("Female", 2, 4, "222"));
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = Mockito.mock(EmployeeServiceImpl.class);
        Mockito.when(employeeRepository.findAll()).thenReturn(list);
        int companyId = 1;
        //when
        List<Employee> result = employeeService.getAllEmployee();
        //then
        assertEquals(2, result.size());
    }

}
