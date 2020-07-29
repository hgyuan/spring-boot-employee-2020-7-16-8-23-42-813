package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping
//    public List<Employee> findAllEmployees(){
//        return employeeService.findAll();
//    }

    @GetMapping(params = {"name"})
    public List<Employee> findAllByName(@RequestParam String name){
        return employeeService.findAllByName(name);
    }

    @GetMapping(params = {"size","page","sort"})
    public List<Employee> findEmployeesByPage(@PageableDefault(size = 2,page = 1)Pageable pageable){
        return employeeService.findEmployeesByPage(pageable);
    }
}
