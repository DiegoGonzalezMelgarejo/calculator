package com.calculator.factory;

import com.calculator.strategy.operations.StrategyOperation;
import com.calculator.util.enums.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class OperationFactory {
    private Map<Operator, StrategyOperation> strategies;
    @Autowired
    public OperationFactory(Set<StrategyOperation> strategySet) {
        createStrategy(strategySet);
    }
    public StrategyOperation findStrategy(Operator strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<StrategyOperation> strategySet) {
        strategies = new HashMap<Operator, StrategyOperation>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getOperator(), strategy));
    }
}
