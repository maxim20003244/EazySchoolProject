package com.eazybyte.springschoolproject.annotation;

import com.eazybyte.springschoolproject.validation.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface PasswordValidator {
    String message() default "Please choose a strong password";
    Class<?> [] groups ()default {};
    Class<? extends Payload> [] payload() default {};

}
