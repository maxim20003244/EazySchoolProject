package com.eazybyte.springschoolproject.annotation;

import com.eazybyte.springschoolproject.validation.FieldsValueMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
      Class<?> [] groups ()default {};
      Class<? extends Payload> [] payload() default {};
      String message () default  "Fields values don't match!";
      String field();
      String fieldMatch();

      @Target({ElementType.TYPE})
      @Retention(RetentionPolicy.RUNTIME)
       @interface  List {
          FieldsValueMatch[] value();
      }

}
