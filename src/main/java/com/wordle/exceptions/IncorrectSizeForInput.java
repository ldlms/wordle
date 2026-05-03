package com.wordle.exceptions;

public class IncorrectSizeForInput extends RuntimeException {
    public IncorrectSizeForInput() {
        super("la proposition doit faire 5 lettres");
    }
}
