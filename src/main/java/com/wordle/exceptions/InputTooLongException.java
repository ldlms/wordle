package com.wordle.exceptions;

public class InputTooLongException extends RuntimeException {
    public InputTooLongException() {
        super("la proposition doit faire 5 lettres");
    }
}
