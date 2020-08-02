package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.util.EmployeeUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.thoughtworks.springbootemployee.util.CompanyUtil.*;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {


    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping()
    @ResponseBody
    public CompanyResponseDto addCompany(@RequestBody CompanyRequestDto company) {
        return companyToResponseDto(companyService.addCompany(dtoToCompany(company)));
    }

    @PutMapping("/{companyId}")
    public void updateCompany(@PathVariable Integer companyId, @RequestBody CompanyRequestDto company) {
        company.setId(companyId);
        companyService.updateCompany(dtoToCompany(company));
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId) {
        companyService.deleteCompany(companyId);
    }

    @GetMapping
    public List<CompanyResponseDto> findCompanyByPage(@PageableDefault(size = 2, page = 1) Pageable pageable, @RequestParam Boolean unpaged) {
        List<Company> companys;
        if (unpaged) {
            companys = companyService.findAll();
        } else {
            companys = companyService.queryCompanyByPage(pageable);
        }
        return companyToResponseDtos(companys);
    }

    @GetMapping("/{companyId}")
    public CompanyResponseDto getEmployees(@PathVariable Integer companyId) {
        return companyToResponseDto(companyService.queryCompanyById(companyId));
    }

    @GetMapping("/{companyId}/employees")
    public List<EmployeeResponseDto> getEmployeesByCompanyId(@PathVariable("companyId") Integer companyId) {
        return EmployeeUtil.castToEmployeeResponseDtos(companyService.queryCompanyById(companyId).getEmployees());
    }


}