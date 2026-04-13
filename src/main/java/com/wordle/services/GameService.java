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

        for (Map.Entry<Character, List<Integer>> entry : userGuess.entrySet()) {
            List<Integer> toRemove = new ArrayList<>();
            for (Integer i : entry.getValue()) {
                if (wordToGuess.get(entry.getKey()) != null) {
                    if (wordToGuess.get(entry.getKey()).contains(i)) {
                        toRemove.add(i);
                    }
                }
            }
            for (Integer i : toRemove) {
                wordToGuess.get(entry.getKey()).remove(i);
                userGuess.get(entry.getKey()).remove(i);
                response.setLetter(i, Status.CORRECT);
            }
        }

        for (Map.Entry<Character, List<Integer>> entry : userGuess.entrySet()) {
            entry.getValue().forEach(i -> {
                if(wordToGuess.get(entry.getKey()) != null && !wordToGuess.get(entry.getKey()).isEmpty()){
                    response.setLetter(i, Status.MISPLACED);
                    wordToGuess.get(entry.getKey()).remove(wordToGuess.get(entry.getKey()).remove(wordToGuess.get(entry.getKey()).size()-1));
                }

            });
        }


        return response;

    }
}
