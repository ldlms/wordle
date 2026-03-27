package com.wordle.dto;

public enum Status {
    CORRECT("✅"),
    MISPLACED("🟡"),
    INCORRECT("❌");

    private final String symbol;

    Status(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
