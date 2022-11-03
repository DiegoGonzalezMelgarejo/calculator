package com.calculator.strategy.operations;

import com.calculator.util.enums.Operator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class StrategySub implements StrategyOperation {
    @Override
    public BigDecimal operation(BigDecimal num1, BigDecimal num2) {
        return num1.subtract(num2);
    }

    @Override
    public Operator getOperator() {
        return Operator.SUB;
    }
}
