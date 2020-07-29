package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.entity.Employee;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employeeList = new ArrayList<>();
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
    @Override
    public void deleteEmployee(int employeeID) {
        employeeList.remove(employeeList.stream()
                .filter(e -> e.getId() == employeeID)
                .findFirst()
                .orElse(null));
    }
    @Override
    public void updateEmployee(Employee employeeDTO) {
        Employee employee = employeeList.stream()
                .filter(e -> e.getId() == employeeDTO.getId())
                .findFirst()
                .orElse(null);
        if (employee == null) {
            return;
        }
        employee.setAge(employeeDTO.getAge());
        employee.setGender(employeeDTO.getGender());
        employee.setName(employeeDTO.getName());
    }
    @Override
    public Employee queryEmployee(int id) {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .filter(e -> gender.equals(e.getGender()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Employee> getEmployeeInPage(Integer page, Integer pageSize) {
        List<Employee> employeeList = employeeRepository.findAll();
//        return PageHelper.PageHelper(page, pageSize, employeeList);
        return null;
    }
}




