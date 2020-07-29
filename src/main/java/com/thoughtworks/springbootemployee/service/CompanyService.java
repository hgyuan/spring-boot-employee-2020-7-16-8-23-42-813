package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface CompanyService {

    List<Employee> findEmployeesByCompanyId(Integer companyId);

    List<Company> findAll();
}
