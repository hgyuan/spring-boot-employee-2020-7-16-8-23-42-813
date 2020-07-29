package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Employee> findEmployeesByCompanyId(Integer companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(NotFoundException::new)
                .getEmployees();
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
