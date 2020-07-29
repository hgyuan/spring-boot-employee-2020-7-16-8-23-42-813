package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    CompanyServiceImpl companyService;

    @Mock
    CompanyRepository companyRepository;

    @Test
    void should_return_company_when_addCompany_given_1_company() {
        //given
        Company company = new Company(1, "OOCL");

        when(companyRepository.save(Mockito.any())).thenReturn(company);
        //when
        Company returnCompany = companyService.addCompany(company);

        //then
        assertEquals(company.getName(), returnCompany.getName());

    }

    @Test
    void should_throw_not_found_exception_when_query_company_by_id_given_not_exist_id() {
        when(companyRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        //when
        Throwable throwable = assertThrows(NotFoundException.class, () -> companyService.queryCompanyById(0));
        //then
        assertEquals("not found", throwable.getMessage());
    }
}
