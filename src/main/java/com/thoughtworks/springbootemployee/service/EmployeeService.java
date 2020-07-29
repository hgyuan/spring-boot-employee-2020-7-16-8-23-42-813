package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    void deleteEmployee(int employeeID);
    void updateEmployee(Employee employeeDTO);
    Employee queryEmployee(int id);
    List<Employee> getAllEmployee();
    List<Employee> getEmployeeByGender(String gender);
    List<Employee> getEmployeeInPage(Integer page, Integer pageSize);
}