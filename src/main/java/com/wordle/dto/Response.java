package com.wordle.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Response(List<Status> letters) {

    public Response {
        if (letters.size() != 5) {
            throw new IllegalArgumentException("Le mot doit faire 5 lettres");
        }
    }

    public String display() {
        return letters.stream()
                .map(Status::getSymbol)
                .collect(Collectors.joining());
    }

    public static Response builder(){
        return new Response(new ArrayList<>(List.of(Status.INCORRECT, Status.INCORRECT, Status.INCORRECT, Status.INCORRECT, Status.INCORRECT)));
    }

    public void setLetter(int letter, Status status) {
        letters.set(letter, status);
    }

}
