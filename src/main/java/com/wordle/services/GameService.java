package com.wordle.services;
import com.wordle.dto.Response;
import com.wordle.dto.Status;
import java.util.*;

public class GameService {

    public Response processInput(String input, List<String> word){
        Response response = Response.builder();

        Map<Character, List<Integer>> userGuess = new HashMap<>();
        Map<Character, List<Integer>> wordToGuess = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            userGuess.computeIfAbsent(Character.toUpperCase(c), k -> new ArrayList<>()).add(i);
        }

        for (int i = 0; i < word.size(); i++) {
            char c = word.get(i).charAt(0);
            wordToGuess.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }

        for (Map.Entry<Character, List<Integer>> entry : wordToGuess.entrySet()) {
            entry.getValue().forEach(i -> {
                if (userGuess.containsKey(entry.getKey())) {
                    entry.getValue().forEach(j -> {
                        if (userGuess.get(entry.getKey()).contains(j)) {
                            response.setLetter(i,Status.CORRECT);
                        }else{
                            response.setLetter(i,Status.MISPLACED);
                        }
                    });
                }
            });
        }
        return response;

    }
}
