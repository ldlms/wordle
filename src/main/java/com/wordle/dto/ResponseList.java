package com.wordle.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseList {

    List<Response> responses = new ArrayList<>();

    public List<String> getResponses() {
        return responses.stream().map(Response::display).toList();
    }

    public List<String> addResponse(Response response) {
        responses.add(response);
        return getResponses();
    }
}
