package com.thoughtworks.springbootemployee.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;

    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @PostMapping()
    public void addEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto,employee);
        employee.setCompany(companyService.queryCompanyById(employeeRequestDto.getCompanyId()));
        employeeService.addEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee) {
        employee.setId(employeeId);
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    @GetMapping
    public List<Employee> findEmployeesByPage(@PageableDefault(size = 2, page = 1) Pageable pageable, @RequestParam Boolean unpaged) {
        if (unpaged) {
            return employeeService.findAll();
        }
        return employeeService.queryEmployeeByPage(pageable).getContent();
    }

    @GetMapping(params = "gender")
    public List<Employee> findEmployeesByGender(@RequestParam String gender) {
        return employeeService.findEmployeesByGender(gender);
    }

    @GetMapping("/{employeeId}")
    public Employee queryEmployeesById(@PathVariable Integer employeeId) {
        return employeeService.queryEmployeeById(employeeId);
    }
}