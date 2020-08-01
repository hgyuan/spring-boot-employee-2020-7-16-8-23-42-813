package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    @ResponseBody
    public EmployeeResponseDto addEmployee(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        return employeeService.addEmployeeByDto(employeeRequestDto);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public EmployeeResponseDto updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeRequestDto employee) {
        employee.setId(employeeId);
        return employeeService.updateEmployeeByDto(employee);
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