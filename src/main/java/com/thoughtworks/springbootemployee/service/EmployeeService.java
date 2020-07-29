package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    List<Employee> findAllByName(String name);

    List<Employee> findEmployeesByPage(Pageable pageable);
}