package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    void addEmployee(Employee employee);

    List<Employee> getEmployees();

    Boolean deleteEmployee(Integer employeeId);

    Boolean updateEmployee(Employee employee);

    Employee getEmployee(Integer id);
}
