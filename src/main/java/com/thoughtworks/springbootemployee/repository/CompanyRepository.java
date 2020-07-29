package com.thoughtworks.springbootemployee.repository;


import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository {
    void addCompany(Company company);

    List<Company> getCompanies();

    Boolean deleteEmployeesOfCompany(Integer companyId);

    void updateCompany(Company company);

    Company getCompany(Integer companyId);

    List<Company> queryCompanyByPage(Integer page, Integer pageSize);
}
