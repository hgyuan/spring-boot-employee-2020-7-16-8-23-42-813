package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {


    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping()
    public void addCompany(@RequestBody Company company) {
        companyRepository.addCompany(company);
    }

    @PutMapping("/{companyId}")
    public void updateCompany(@PathVariable Integer companyId, @RequestBody Company company) {
        company.setId(companyId);
        companyRepository.updateCompany(company);
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployeesOfCompany(@PathVariable Integer companyId) {
        companyRepository.deleteEmployeesOfCompany(companyId);
    }

    @GetMapping()
    public List<Company> getCompanies(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyRepository.queryCompanyByPage(page,pageSize);
        }
        return companyRepository.getCompanies();
    }

    @GetMapping("/{companyId}")
    public Company getEmployees(@RequestParam("companyId") Integer companyId) {
        return companyRepository.getCompany(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable("companyId") Integer companyId) {
        return companyRepository.getCompany(companyId).getEmployees();
    }


}
