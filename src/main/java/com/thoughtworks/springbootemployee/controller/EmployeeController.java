package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping()
    public void addEmployee(@RequestBody Employee employee){
        employeeRepository.addEmployee(employee);
    }

    @PutMapping()

    public void updateEmployee(@RequestBody Employee employee){
        employeeRepository.updateEmployee(employee);
    }

    @DeleteMapping()
    public void deleteEmployee(Integer employeeId){
        employeeRepository.deleteEmployee(employeeId);
    }

    @GetMapping()
    public List<Employee> getEmployees(){
        return employeeRepository.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@RequestParam("employeeId") Integer employeeId){
        return employeeRepository.getEmployee(employeeId);
    }

}
