package com.wordle.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Response(List<Status> letters) {

    public String display() {
        return letters.stream()
                .map(Status::getSymbol)
                .collect(Collectors.joining());
    }

    public static Response builder(){
        return new Response(new ArrayList<>(List.of(Status.INCORRECT, Status.INCORRECT, Status.INCORRECT, Status.INCORRECT, Status.INCORRECT)));
    }

    public boolean isGameWon(){
        return !this.letters.contains(Status.INCORRECT) && !this.letters.contains(Status.MISPLACED);
    }


    public void setLetter(int letter, Status status) {
        letters.set(letter, status);
    }

}
