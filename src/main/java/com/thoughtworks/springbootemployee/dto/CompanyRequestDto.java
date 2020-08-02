package com.thoughtworks.springbootemployee.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.springbootemployee.entity.Employee;

import javax.persistence.OneToMany;
import java.util.List;

public class CompanyRequestDto {
    private Integer id;
    private String name;

    public CompanyRequestDto(String name) {
        this.name = name;
    }

    public CompanyRequestDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
