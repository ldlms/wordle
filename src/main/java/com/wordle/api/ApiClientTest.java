package com.wordle.api;

import com.wordle.interfaces.ApiService;

import java.util.List;

public class ApiClientTest implements ApiService {

    @Override
    public List<String> callApi() {
        return List.of();
    }

    @Override
    public List<String> callTestApi(String word) {
        String[] wordsArray = word.split("\\s");
        return List.of(wordsArray);
    }
}
