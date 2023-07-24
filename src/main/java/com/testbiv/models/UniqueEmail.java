package com.testbiv.models;

import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
@Documented
public @interface UniqueEmail {
    String message() default "Email already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}