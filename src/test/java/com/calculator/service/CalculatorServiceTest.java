package com.calculator.service;

import com.calculator.configuration.log.Logger;
import com.calculator.configuration.log.LoggerImpl;
import com.calculator.factory.OperationFactory;
import com.calculator.service.serviceImp.CalculatorServiceImp;
import com.calculator.strategy.operations.StrategyAdd;
import com.calculator.strategy.operations.StrategySub;
import com.calculator.util.enums.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculatorServiceTest {

    @Autowired
    @InjectMocks
    private static ICalculatorService calculatorService;
    @Mock
    private static final Logger logger = mock(LoggerImpl.class);
    private static final OperationFactory operationFactory= mock(OperationFactory.class);

    @BeforeAll
    public static void setup() {
        calculatorService = new CalculatorServiceImp(operationFactory,logger);
    }
    @Test
    public void getOperationResultAddition(){
        when(operationFactory.findStrategy(Operator.ADD)).thenReturn(new StrategyAdd());
        BigDecimal bigDecimal= calculatorService.getOperationResult(new BigDecimal(1),new BigDecimal(2), Operator.ADD);
        Assertions.assertEquals(new BigDecimal(3),bigDecimal);
    }
    @Test
    public void getOperationResultsubtraction(){
        when(operationFactory.findStrategy(Operator.SUB)).thenReturn(new StrategySub());
        BigDecimal bigDecimal= calculatorService.getOperationResult(new BigDecimal(5),new BigDecimal(2), Operator.SUB);
        Assertions.assertEquals(new BigDecimal(3),bigDecimal);
    }
}
