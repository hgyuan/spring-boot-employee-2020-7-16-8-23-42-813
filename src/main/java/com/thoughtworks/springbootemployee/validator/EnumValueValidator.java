package com.thoughtworks.springbootemployee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue,Object> {

    private String[] strValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        for(String str:strValues){
            if(value.equals(str)){
                return true;
            }
        }
        return false;
    }
}
