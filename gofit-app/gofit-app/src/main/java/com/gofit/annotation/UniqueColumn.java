package com.gofit.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueColumnValidator.class)
public @interface UniqueColumn {

    String message() default "Field value must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();
}
