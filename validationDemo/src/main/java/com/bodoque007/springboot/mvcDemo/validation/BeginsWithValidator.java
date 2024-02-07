package com.bodoque007.springboot.mvcDemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BeginsWithValidator implements ConstraintValidator<BeginsWith, String> {

    private String prefix;
    @Override
    public void initialize(BeginsWith constraintAnnotation) {
        this.prefix = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.startsWith(this.prefix);
    }
}
