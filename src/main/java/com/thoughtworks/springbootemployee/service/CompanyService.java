package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;

public interface CompanyService {
    Company addCompany(Company company);

    Company queryCompanyById(int id);
}
