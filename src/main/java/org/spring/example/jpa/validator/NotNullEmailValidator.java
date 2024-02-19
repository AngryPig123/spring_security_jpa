package org.spring.example.jpa.validator;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.AbstractEmailValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

@Slf4j
public class NotNullEmailValidator extends AbstractEmailValidator<NotNullEmail> {

    private java.util.regex.Pattern pattern;

    @Override
    public void initialize(NotNullEmail emailAnnotation) {
        super.initialize(emailAnnotation);

        Pattern.Flag[] flags = emailAnnotation.flags();
        int intFlag = 0;
        for (Pattern.Flag flag : flags) {
            intFlag = intFlag | flag.getValue();
        }

        if (!".*".equals(emailAnnotation.regexp()) || emailAnnotation.flags().length > 0) {
            try {
                pattern = java.util.regex.Pattern.compile(emailAnnotation.regexp(), intFlag);
            } catch (PatternSyntaxException e) {
                log.error("patternSyntaxException = ", e);
            }
        }
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value == "") {
            return false;
        }

        boolean isValid = super.isValid(value, context);
        if (pattern == null || !isValid) {
            return isValid;
        }

        Matcher m = pattern.matcher(value);
        return m.matches();
    }

}