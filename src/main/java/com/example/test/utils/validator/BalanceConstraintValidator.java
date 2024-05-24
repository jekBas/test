package com.example.test.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceConstraintValidator implements ConstraintValidator<BalanceConstraint, Map<Integer, Integer>> {
    @Override
    public boolean isValid(Map<Integer, Integer> integerIntegerMap,
                           ConstraintValidatorContext constraintValidatorContext) {
        List<Integer> userIds = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : integerIntegerMap.entrySet()) {
            if (entry.getValue() < 0 ) {
                userIds.add(entry.getKey());
            }
        }

        if (!CollectionUtils.isEmpty(userIds)) {
            ((ConstraintValidatorContextImpl) constraintValidatorContext).addMessageParameter("input", userIds);
            return false;
        }

        return true;
    }
}
