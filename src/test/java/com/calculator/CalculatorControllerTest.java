package com.calculator;

import com.calculator.configuration.log.Logger;
import com.calculator.configuration.log.LoggerImpl;
import com.calculator.controller.CalculatorController;
import com.calculator.exception.CalculatorBasicException;
import com.calculator.service.ICalculatorService;
import com.calculator.service.serviceImp.CalculatorServiceImp;
import com.calculator.util.constants.Constants;
import com.calculator.util.enums.Operator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {
    @Autowired
    @InjectMocks
    private static CalculatorController calculatorController;
    @Mock
    private  static ICalculatorService calculatorService= mock(CalculatorServiceImp.class);
    @Mock
    private final Logger logger = mock(LoggerImpl.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getOperationResultForAddition(){
        when(calculatorService.getOperationResult(any(),any(),any())).thenReturn(new BigDecimal(3));
        Assertions.assertEquals(
                calculatorController.getOperationResult(new BigDecimal(1),new BigDecimal(2), Operator.ADD).getStatusCode(),
                HttpStatus.OK);

    }
    @Test
    public void getOperationResultForsubtraction(){
        when(calculatorService.getOperationResult(any(),any(),any())).thenReturn(new BigDecimal(3));
        Assertions.assertEquals(
                calculatorController.getOperationResult(new BigDecimal(5),new BigDecimal(2), Operator.SUB).getStatusCode(),
                HttpStatus.OK);

    }
    @Test
    public void getOperationResultForExceptionNum1(){
        try {
            calculatorController.getOperationResult(null,new BigDecimal(2), Operator.ADD).getStatusCode();

        }catch (CalculatorBasicException calculatorBasicException){
            Assertions.assertEquals(calculatorBasicException.getMessage(),String.format(Constants.ERROR_NULL_PARAM,"num1"));
        }
    }
    @Test
    public void getOperationResultForExceptionNum2(){
        try {
            calculatorController.getOperationResult(new BigDecimal(2),null, Operator.ADD).getStatusCode();

        }catch (CalculatorBasicException calculatorBasicException){
            Assertions.assertEquals(calculatorBasicException.getMessage(),String.format(Constants.ERROR_NULL_PARAM,"num1"));
        }
    }
    @Test
    public void getCalculatorExceptionInRestControllerAdvice() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/calculator")

              .param("num2","3")
              .param("operator",Operator.ADD.toString())
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));
    }
    @Test
    public void getExceptionInRestControllerAdvice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculator")
                .param("num1","a")
                .param("num2","3")
                .param("operator",Operator.ADD.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}
