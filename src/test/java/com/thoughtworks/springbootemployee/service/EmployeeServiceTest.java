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
        assertEquals(employee.getName(),returnEmployee.getName());

    }

    @Test
    void should_return_size_0_when_getEmployees_given_0_employee() {

        //given
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>(0));
        //when
        List<Employee> employees = employeeServic.findAll();

        //then
        assertEquals(0,employees.size());
    }
}
