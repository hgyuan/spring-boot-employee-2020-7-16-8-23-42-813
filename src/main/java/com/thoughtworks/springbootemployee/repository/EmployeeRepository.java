package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    void addEmployee(Employee employee);

    List<Employee> findAll();

    Boolean deleteEmployee(Integer employeeId);

    Boolean updateEmployee(Employee employee);

    Employee getEmployee(Integer id);

    List<Employee> getEmployeesByGender(String gender);

    List<Employee> queryEmployeesByPage(Integer page, Integer pageSize);
}
