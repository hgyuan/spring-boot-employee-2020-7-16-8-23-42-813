package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.util.EmployeeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> queryEmployeeByPage(Pageable pageRequest) {
        return employeeRepository.findAll(pageRequest);
    }

    @Override
    public Employee queryEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    @Override
    public EmployeeResponseDto addEmployeeByDto(EmployeeRequestDto employeeRequestDto) {
        Company company = companyRepository.findById(employeeRequestDto.getCompanyId()).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        employee.setCompany(company);
        BeanUtils.copyProperties(employeeRequestDto, employee);
        return EmployeeUtil.castToEmployeeResponseDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponseDto updateEmployeeByDto(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepository.findById(employeeRequestDto.getId()).orElse(null);
        if (Objects.isNull(employee)) {
            throw new NotFoundException();
        }
        if (!employee.getCompany().getId().equals(employeeRequestDto.getCompanyId())) {
            Company company = companyRepository.findById(employeeRequestDto.getCompanyId()).get();
            employee.setCompany(company);
        }
        BeanUtils.copyProperties(employeeRequestDto, employee);
        return EmployeeUtil.castToEmployeeResponseDto(employeeRepository.save(employee));
    }
}
