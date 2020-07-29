package com.thoughtworks.springbootemployee.repository.impl;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    List<Company> companyList = new ArrayList<>();

    @Override
    public void addCompany(Company company) {
        companyList.add(company);
    }

    @Override
    public List<Company> getCompanies() {
        return companyList;
    }

    @Override
    public Boolean deleteEmployeesOfCompany(Integer companyId) {
        Company company = getCompanyById(companyId);
        if (company != null) {
            company.setEmployees(new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public void updateCompany(Company company) {
        Company oldCompany = getCompanyById(company.getId());
        for (Employee newEmployee : company.getEmployees()) {
            boolean isEqual = false;
            for (Employee existEmployee : oldCompany.getEmployees()) {
                if(newEmployee.equals(existEmployee)){
                    isEqual = true;
                }
            }
            if(!isEqual){
                oldCompany.setEmployeesNumber(oldCompany.getEmployeesNumber()+1);
                oldCompany.getEmployees().add(newEmployee);
            }
        }
    }

    @Override
    public Company getCompany(Integer companyId) {
        return getCompanyById(companyId);
    }

    @Override
    public List<Company> queryCompanyByPage(Integer page, Integer pageSize) {
        if ((page - 1) * pageSize >= companyList.size()) {
            return new ArrayList<>();
        }
        if ((page - 1) * pageSize < companyList.size() && companyList.size() < page * pageSize) {
            return companyList.subList((page - 1) * pageSize, companyList.size());
        }
        return companyList.subList((page - 1) * pageSize, page * pageSize);
    }

    private Company getCompanyById(Integer companyId) {
        return companyList.stream().filter(item -> item.getId().equals(companyId)).findFirst().orElse(null);
    }
}
