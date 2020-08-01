package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {


   private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping()
    @ResponseBody
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @PutMapping("/{companyId}")
    public void updateCompany(@PathVariable Integer companyId, @RequestBody Company company) {
        company.setId(companyId);
        companyService.updateCompany(company);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId) {
        companyService.deleteCompany(companyId);
    }

    @GetMapping
    public List<Company> findCompanyByPage(@PageableDefault(size = 2, page = 1) Pageable pageable, @RequestParam Boolean unpaged) {
        if (unpaged) {
            return companyService.findAll();
        }
        return companyService.queryCompanyByPage(pageable);
    }

    @GetMapping("/{companyId}")
    public Company getEmployees(@PathVariable Integer companyId) {
        return companyService.queryCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable("companyId") Integer companyId) {
        return companyService.queryCompanyById(companyId).getEmployees();
    }




}