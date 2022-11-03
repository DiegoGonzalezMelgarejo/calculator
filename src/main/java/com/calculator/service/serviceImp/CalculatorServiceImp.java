package com.calculator.service.serviceImp;

import com.calculator.factory.OperationFactory;
import com.calculator.service.ICalculatorService;
import com.calculator.strategy.operations.StrategyOperation;
import com.calculator.util.enums.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@RequiredArgsConstructor
@Service
public class CalculatorServiceImp implements ICalculatorService {

}
