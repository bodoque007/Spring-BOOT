package com.bodoque007.springboot.mvcDemo.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BeginsWithValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BeginsWith {
    public String value() default "bodoque";

    public String message() default "Must begin with bodoque!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
