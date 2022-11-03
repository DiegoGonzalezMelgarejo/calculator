package com.calculator.service;

import com.calculator.util.enums.Operator;

import java.math.BigDecimal;

public interface ICalculatorService {
    BigDecimal getOperationResult(BigDecimal num1, BigDecimal num2, Operator operator);
}
