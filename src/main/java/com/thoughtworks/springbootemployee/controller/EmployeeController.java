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
    private EmployeeRepository employeeRepository;

    @PostMapping()
    public void addEmployee(@RequestBody Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable Integer employeeId,@RequestBody Employee employee) {
        employee.setId(employeeId);
        employeeRepository.updateEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }

    @GetMapping()
    public List<Employee> getEmployee(@RequestParam(value = "gender", required = false) String gender,
                                      @RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (gender != null) {
            return employeeRepository.getEmployeesByGender(gender);
        }
        if (page != null && pageSize != null) {
            return employeeRepository.queryEmployeesByPage(page, pageSize);
        }
        return employeeRepository.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Integer employeeId) {
        return employeeRepository.getEmployee(employeeId);
    }


}
