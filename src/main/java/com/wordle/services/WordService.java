package com.wordle.services;

import com.wordle.exceptions.InputTooLongException;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordService {

    public List<String> processResponse(String body){
        String field = extractField(body,"name");
        String decoded = decodeUnicode(field);
        String normalized = normalizeWord(decoded);
        return wordToLetterList(normalized);

    }

    public static String decodeUnicode(String input) {
        Pattern pattern = Pattern.compile("\\\\u([0-9A-Fa-f]{4})");
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String replacement = String.valueOf(
                    (char) Integer.parseInt(matcher.group(1), 16)
            );
            matcher.appendReplacement(result, replacement);
        }

        matcher.appendTail(result);

        return result.toString();
    }

    public String extractField(String json, String field) {
        String key = "\"" + field + "\":\"";
        int start = json.indexOf(key) + key.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    public static String normalizeWord(String input) {
        String noAccent = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        return noAccent.toUpperCase();
    }

    public List<String> wordToLetterList(String word) {
        List<String> letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            letters.add(String.valueOf(c));
        }
        return letters;
    }

    public void verifyInput(String input){
     if(input.length() != 5){
         throw new InputTooLongException();
     }
    }
}
