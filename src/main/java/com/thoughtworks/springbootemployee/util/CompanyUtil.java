package com.thoughtworks.springbootemployee.util;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyUtil {

    public static Company dtoToCompany(CompanyRequestDto companyRequestDto) {
        Company company = new Company();
        company.setId(companyRequestDto.getId());
        company.setName(companyRequestDto.getName());
        return company;
    }

    public static CompanyResponseDto companyToResponseDto(Company company) {
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        companyResponseDto.setName(company.getName());
        companyResponseDto.setId(company.getId());
        if(company.getEmployees()!=null){
            companyResponseDto.setEmployees(company.getEmployees().stream()
                    .map(EmployeeUtil::castToEmployeeResponseDto)
                    .collect(Collectors.toList()));
        }
        return companyResponseDto;
    }

    public static List<CompanyResponseDto> companyToResponseDtos(List<Company> companies) {
        return companies.stream().map(CompanyUtil::companyToResponseDto).collect(Collectors.toList());
    }
}
