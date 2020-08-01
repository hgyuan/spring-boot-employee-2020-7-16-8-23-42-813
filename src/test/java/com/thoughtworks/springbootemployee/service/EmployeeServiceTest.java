package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    EmployeeServiceImpl employeeService;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    CompanyRepository companyRepository;

    @Test
    void should_return_employee_when_addEmployee_given_1_Employee_1_company() {
        //given
        Company company = new Company(1, "OOCL");
        Employee employee = new Employee("Richard", 18, "male", company);

        when(employeeRepository.save(any())).thenReturn(employee);
        //when
        Employee returnEmployee = employeeService.addEmployee(employee);

        //then
        assertEquals(employee.getName(), returnEmployee.getName());

    }

    @Test
    void should_return_size_0_when_getEmployees_given_0_employee() {

        //given
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>(0));
        //when
        List<Employee> employees = employeeService.findAll();

        //then
        assertEquals(0, employees.size());
    }

    @Test
    void should_return_size_2_when_query_employee_by_page_given_page_1_size_2_4employee() {
        //given
        Pageable pageRequest = PageRequest.of(1, 2);
        List<Employee> employees = new ArrayList<>(2);
        employees.add(new Employee());
        employees.add(new Employee());
        Page<Employee> pages = new PageImpl<>(employees);
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(pages);
        //when
        Page<Employee> pageOfEmployees = employeeService.queryEmployeeByPage(pageRequest);

        //then
        assertEquals(2, pageOfEmployees.getContent().size());
    }

    @Test
    void should_throw_not_found_exception_when_query_employee_by_id_given_not_exist_id() {
        when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        //when
        Throwable throwable = assertThrows(NotFoundException.class, () -> employeeService.queryEmployeeById(0));
        //then
    }

    @Test
    void should_return_employee_when_query_employee_by_id_given_exist_id() {
        //given
        final Integer id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        Optional<Employee> optionalEmployee = Optional.of(employee);
        when(employeeRepository.findById(Mockito.anyInt())).thenReturn(optionalEmployee);
        //when
        Employee returnEmployee = employeeService.queryEmployeeById(id);
        //then
        assertEquals(id, returnEmployee.getId());
    }

    @Test
    void should_return_employee_when_add_employee_by_employee_dto_given_employee_request_dto_company() {
        //given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto("olivia", 10, "female", 1);
        Company company = new Company();
        company.setId(employeeRequestDto.getCompanyId());
        Employee employee = new Employee("olivia", 10, "female", company);
        Optional<Company> optionalCompany = Optional.of(company);

        //when
        when(companyRepository.findById(any())).thenReturn(optionalCompany);
        when(employeeRepository.save(any())).thenReturn(employee);
        Employee returnEmployee = employeeService.addEmployeeByDto(employeeRequestDto);

        //then
        assertEquals(employeeRequestDto.getName(), returnEmployee.getName());
    }

    @Test
    void should_return_employee_when_update_employee_by_employee_dto_given_employee_request_dto_company() {
        //given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto("olivia", 10, "female", 1);
        employeeRequestDto.setId(1);
        Company company = new Company();
        company.setId(employeeRequestDto.getCompanyId());
        Employee employeeBeforeSave = new Employee("olivia1111", 10, "female", company);
        employeeBeforeSave.setId(1);
        Employee employeeAfterSaved = new Employee("olivia", 10, "female", company);
        employeeAfterSaved.setId(1);
        Optional<Employee> optionalEmployee = Optional.of(employeeBeforeSave);

        //when
        when(employeeRepository.findById(any())).thenReturn(optionalEmployee);
        when(employeeRepository.save(any())).thenReturn(employeeAfterSaved);
        Employee returnEmployee = employeeService.updateEmployeeByDto(employeeRequestDto);

        //then
        assertEquals(employeeRequestDto.getName(), returnEmployee.getName());
    }

    @Test
    void should_throw_employee_not_found_when_update_employee_by_employee_dto_given_employee_request_dto() {
        //given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto("olivia", 10, "female", 1);
        Optional<Employee> optionalEmployee = Optional.empty();
        //when
        when(employeeRepository.findById(any())).thenReturn(optionalEmployee);
        Throwable throwable = assertThrows(NotFoundException.class, ()->employeeService.updateEmployeeByDto(employeeRequestDto));

        //then
        assertEquals("not found", throwable.getMessage());

    }
}
