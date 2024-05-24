package com.example.test.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BalanceConstraintValidator.class)
public @interface BalanceConstraint {
    /**
     * Ad's Time Zone error message.
     *
     * @return {@link String} error message
     */
    String message() default "Validation Error: incorrect balance for users '{input}'";

    /**
     * Groups.
     *
     * @return Class array
     */
    Class<?>[] groups() default {};

    /**
     * Payload.
     *
     * @return Class array
     */
    Class<? extends Payload>[] payload() default {};
}
