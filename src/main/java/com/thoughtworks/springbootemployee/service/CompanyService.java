package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;

import java.util.List;

public interface CompanyService {

    public List<Company> queryCompanyByPage(int page, int pageSize) ;

}
