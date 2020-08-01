package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company queryCompanyById(int id) {
        return companyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public CompanyResponseDto addCompanyByDto(CompanyRequestDto companyRequestDto) {
        Company company=new Company();
        company.setName(companyRequestDto.getName());
        Company newCompany = companyRepository.save(company);
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        BeanUtils.copyProperties(newCompany,companyResponseDto);
        return companyResponseDto;

    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Integer companyId) {
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public List<Company> queryCompanyByPage(Pageable pageable) {
        return companyRepository.findAll(pageable).getContent();
    }
}
