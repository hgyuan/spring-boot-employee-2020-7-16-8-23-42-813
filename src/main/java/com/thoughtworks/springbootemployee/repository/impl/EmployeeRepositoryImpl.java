package com.thoughtworks.springbootemployee.repository.impl;


import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeRepositoryImpl implements EmployeeRepository {

    List<Employee> employeeList = new ArrayList<>();

    @Override
    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeList;
    }

    @Override
    public Boolean deleteEmployee(Integer employeeId) {
        return employeeList.removeIf(item ->
                item.getId().equals(employeeId)
        );
    }

    @Override
    public Boolean updateEmployee(Employee employee) {

        for(Employee employeeItem :employeeList){
            if(employeeItem.getId().equals(employee.getId())){
                employeeList.remove(employeeItem);
                employeeList.add(employee);
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee getEmployee(Integer id) {
        return employeeList.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Employee> getEmployeesByGender(String gender) {
        return employeeList.stream().filter(item -> item.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> queryEmployeesByPage(Integer page, Integer pageSize) {
        if((page-1)*pageSize>=employeeList.size()){
            return new ArrayList<>();
        }
        if((page-1)*pageSize<employeeList.size()&&employeeList.size()<page*pageSize){
            return employeeList.subList((page-1)*pageSize,employeeList.size());
        }
        return employeeList.subList((page-1)*pageSize,page*pageSize);
    }
}
