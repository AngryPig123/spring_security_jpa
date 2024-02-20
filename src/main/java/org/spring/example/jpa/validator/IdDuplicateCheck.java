package org.spring.example.jpa.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.spring.example.jpa.validator.IdDuplicateCheck.List;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
@Documented
@Constraint(validatedBy = {IdDuplicateCheckValidator.class})
public @interface IdDuplicateCheck {

    String message() default "{validation.duplicated.id}";
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};
    String tableName();
    String columnName();
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        IdDuplicateCheck[] value();
    }

}
