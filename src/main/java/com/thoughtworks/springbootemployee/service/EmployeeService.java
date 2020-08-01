package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> findAll();

    Page<Employee> queryEmployeeByPage(Pageable pageRequest);

    Employee queryEmployeeById(int id);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Integer employeeId);

    List<Employee> findEmployeesByGender(String gender);


    EmployeeResponseDto addEmployeeByDto(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto updateEmployeeByDto(EmployeeRequestDto employeeRequestDto);
}
