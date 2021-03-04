package com.example.revision.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;

public class GenericSpecificationsBuilder<U> {

    public Specification<U> build(Deque<?> postFixedExprStack, Function<SpecSearchCriteria, Specification<U>> converter) {

        Deque<Specification<U>> specStack = new LinkedList<>();

        while (!postFixedExprStack.isEmpty()) {
            Object mayBeOperand = postFixedExprStack.pollLast();

            if (!(mayBeOperand instanceof String)) {
                specStack.push(converter.apply((SpecSearchCriteria) mayBeOperand));
            } else {
                Specification<U> operand1 = specStack.pop();
                Specification<U> operand2 = specStack.pop();
                if (mayBeOperand.equals(SearchOperation.AND_OPERATOR)) {
                    specStack.push(Specification.where(operand1)
                            .and(operand2));
                } else if (mayBeOperand.equals(SearchOperation.OR_OPERATOR)) {
                    specStack.push(Specification.where(operand1)
                            .or(operand2));
                }
            }
        }
        return specStack.pop();
    }
}
