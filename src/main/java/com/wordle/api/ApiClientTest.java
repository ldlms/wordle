package com.wordle.api;

import com.wordle.interfaces.ApiService;

import java.util.ArrayList;
import java.util.List;

public class ApiClientTest implements ApiService {

    private final List<String> word;

    public ApiClientTest(String word) {
        List<String> letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            letters.add(String.valueOf(c));
        }
        this.word = letters;
    }

    @Override
    public List<String> callApi() {
        return word;
    }
}