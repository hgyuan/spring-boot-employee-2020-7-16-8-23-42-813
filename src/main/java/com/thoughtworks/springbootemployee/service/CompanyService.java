package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    Company addCompany(Company company);

    Company queryCompanyById(int id);

    void updateCompany(Company company);

    void deleteCompany(Integer companyId);

    List<Company> findAll();

    List<Company> queryCompanyByPage(Pageable pageable);



    CompanyResponseDto addCompanyByDto(CompanyRequestDto companyRequestDto);
}
