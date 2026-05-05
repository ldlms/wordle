package com.wordle.dto;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InputList {

    ArrayList<String> inputs = new ArrayList<>();

    public void add(String input) {
        inputs.add(input);
    }

    public String display(){
        return inputs.stream()
                .map(input -> input + "--------")
                .collect(Collectors.joining());
    }
}
