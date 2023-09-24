package com.javacodegeeks.example;
import java.util.function.DoubleBinaryOperator;

// Enum definieren für alle Operations die es gibt
public enum Operation {
    // Lambda Expressions benutzen
    ADDITION((x, y) -> x+y),
    SUBTRACTION((x, y) -> x-y),
    DIVISION((x, y) -> x/y),
    MULTIPLICATION((x, y) -> x*y),
    PERCENTAGE((x, y) -> x%y);
    private DoubleBinaryOperator operator;
    // constructor
    Operation(DoubleBinaryOperator operator) {
       this.operator = operator;
    }

    // funktion die den Operator zurückgibt
    public DoubleBinaryOperator getOperator() {
        return operator;
    }
}
