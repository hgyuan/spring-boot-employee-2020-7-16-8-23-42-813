package com.thoughtworks.springbootemployee.util;

import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeUtil {
    public static List<EmployeeResponseDto> castToEmployeeResponseDtos(List<Employee> employees){
        return employees.stream().map(EmployeeUtil::castToEmployeeResponseDto).collect(Collectors.toList());
    }

    public static EmployeeResponseDto castToEmployeeResponseDto(Employee employee){
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(employee.getId());
        employeeResponseDto.setName(employee.getName());
        employeeResponseDto.setAge(employee.getAge());
        employeeResponseDto.setGender(employee.getGender());
        employeeResponseDto.setCompanyId(employee.getCompany().getId());
        return employeeResponseDto;
    }
}
