package com.calculator.factory;

import com.calculator.configuration.log.Logger;
import com.calculator.strategy.operations.StrategyOperation;
import com.calculator.util.enums.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OperationFactory {
    private Map<Operator, StrategyOperation> strategies;
    @Autowired
    private final Logger logger;
    @Autowired
    public OperationFactory(Set<StrategyOperation> strategySet, Logger logger) {
        this.logger = logger;
        createStrategy(strategySet);

    }
    public StrategyOperation findStrategy(Operator strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<StrategyOperation> strategySet) {
        strategies = new HashMap<Operator, StrategyOperation>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getOperator(), strategy));
        logger.log("created the following strategies " + strategies);
    }
}
