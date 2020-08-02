package com.thoughtworks.springbootemployee.dto;


import com.thoughtworks.springbootemployee.validator.EnumValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class EmployeeRequestDto {
    private Integer id;
    @NotBlank
    private String name;
    @Min(value = 0)
    @Max(value = 150)
    private Integer age;
    @EnumValue(strValues = {"female","male"})
    private String gender;
    private Integer companyId;

    public EmployeeRequestDto(String name, Integer age, String gender, Integer companyId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.companyId = companyId;
    }

    public EmployeeRequestDto() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }


}
