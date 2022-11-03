package com.calculator.util.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Operator {
    ADD,
    SUB;

    private String representation;
    private String description;
}
