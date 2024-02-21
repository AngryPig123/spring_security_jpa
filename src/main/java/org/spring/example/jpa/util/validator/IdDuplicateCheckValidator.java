package org.spring.example.jpa.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.util.validator.annotation.IdDuplicateCheck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdDuplicateCheckValidator implements ConstraintValidator<IdDuplicateCheck, CharSequence> {

    private final JdbcTemplate jdbcTemplate;
    private String tableName;
    private String columnName;

    @Override
    public void initialize(IdDuplicateCheck parameters) {
        tableName = parameters.tableName();
        columnName = parameters.columnName();
        validateParameters();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext context) {
        String sql = String.format("select count(*) from %s where %s = ?", tableName, columnName);
        Integer value = jdbcTemplate.queryForObject(sql, Integer.class, charSequence == null ? "" : charSequence.toString());
        if (value == null)
            throw new NullPointerException("jdbcTemplate.queryForObject(sql, Integer.class) is null");
        return value == 0;
    }

    private void validateParameters() {
        if (validate(tableName) || validate(columnName)) {
            throw new RuntimeException("ToDO");
        }
    }

    private boolean validate(String value) {
        return value == null || value.isEmpty();
    }

}
