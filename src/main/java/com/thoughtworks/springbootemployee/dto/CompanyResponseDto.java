package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public class CompanyResponseDto {
    private Integer id;
    private String name;
    private List<EmployeeResponseDto> employees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeResponseDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponseDto> employees) {
        this.employees = employees;
    }
}
