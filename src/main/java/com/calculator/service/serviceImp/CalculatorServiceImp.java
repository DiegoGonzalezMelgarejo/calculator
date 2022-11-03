package com.calculator.service.serviceImp;

import com.calculator.configuration.log.Logger;
import com.calculator.exception.CalculatorBasicException;
import com.calculator.factory.OperationFactory;
import com.calculator.service.ICalculatorService;
import com.calculator.strategy.operations.StrategyOperation;
import com.calculator.util.constants.Constants;
import com.calculator.util.enums.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@RequiredArgsConstructor
@Service
public class CalculatorServiceImp implements ICalculatorService {
    @Autowired
    private OperationFactory operationFactory;
    @Autowired
    private final Logger logger;

    @Override
    public BigDecimal getOperationResult(BigDecimal num1, BigDecimal num2, Operator operator) {
        logger.log("You have successfully entered the service");
        if(num1==null){
             throw new CalculatorBasicException(String.format(Constants.ERROR_NULL_PARAM,"num1"));
        }
        if(num2==null){
            throw new CalculatorBasicException(String.format(Constants.ERROR_NULL_PARAM,"num2"));
        }
        StrategyOperation strategyOperation= operationFactory.findStrategy(operator);
        logger.log("used the strategy: " + strategyOperation.getOperator());
        return strategyOperation.operation(num1,num2);
    }
}
