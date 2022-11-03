package com.calculator.strategy.operations;

import com.calculator.util.enums.Operator;

import java.math.BigDecimal;

public interface StrategyOperation {
    BigDecimal operation(BigDecimal num1,BigDecimal num2);
    Operator getOperator();
}
