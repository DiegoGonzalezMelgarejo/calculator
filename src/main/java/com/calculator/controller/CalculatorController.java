package com.calculator.controller;

import com.calculator.configuration.log.Logger;
import com.calculator.service.ICalculatorService;
import com.calculator.util.enums.Operator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calculator")
@Api(value = "Calculator API", tags = {"Operations"})
public class CalculatorController {

    @Autowired
    private final ICalculatorService calculatorService;
    @Autowired
    private final Logger logger;

    @ApiOperation(value = "Get operation result")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request completed successfully."),
            @ApiResponse(code = 500, message = "Server error.")
    })
    @GetMapping
    public ResponseEntity<BigDecimal> getOperationResult(BigDecimal num1, Operator operator, BigDecimal num2) {
        logger.log("Enter the request with the data -> num1: " + num1 + ", num2: " + num2 + ", operación: " + operator.name());
        BigDecimal result = calculatorService.getOperationResult(num1, num2, operator);
        logger.log("The result was: " + result);
        return ResponseEntity.ok(result);
    }

}
