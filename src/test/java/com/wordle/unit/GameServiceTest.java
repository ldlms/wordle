package com.wordle.unit;

import com.wordle.dto.Response;
import com.wordle.dto.Status;
import com.wordle.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() { gameService = new GameService(); }

    private List<String> word(String word) {
        List<String> letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            letters.add(String.valueOf(c));
        }
        return letters;
    }

    @Test
    void ShouldOnlyContainCorrectLetters() {
        Response result = gameService.processInput("arbre", word("ARBRE"));
        assertThat(result.letters()).containsOnly(Status.CORRECT);
        assertThat(result.isGameWon()).isTrue();
    }

    @Test
    void ShouldOnlyContainIncorrectLetters() {
        Response r = gameService.processInput("manger", word("FXHIJ"));
        assertThat(r.letters()).containsOnly(Status.INCORRECT);
        assertThat(r.isGameWon()).isFalse();
    }

    @Test
    void ShouldOnlyContainMisplacedLetters() {
        Response r = gameService.processInput("abcde", word("EABCD"));
        assertThat(r.letters()).containsOnly(Status.MISPLACED);
    }

    @Test
    void ShouldHaveFourthLetterIncorrect() {
        Response r = gameService.processInput("crave", word("CRANE"));
        assertThat(r.letters()).containsExactly(
                Status.CORRECT,
                Status.CORRECT,
                Status.CORRECT,
                Status.INCORRECT,
                Status.CORRECT
        );
    }


    @Test
    void ShouldHandleCorrectNumberOfIncorrectLetters() {
        Response r = gameService.processInput("ramer", word("LIVRE"));
        assertThat(r.letters()).containsExactly(
                Status.MISPLACED,
                Status.INCORRECT,
                Status.INCORRECT,
                Status.MISPLACED,
                Status.INCORRECT
        );
    }

    @Test
    void ShouldDisplayCorrectSymbolsWhenGivenInput(){

        Response r = gameService.processInput("carte", word("CRANE"));

        assertThat(r.letters().get(0).getSymbol()).isEqualTo("✅"); //symbol for correct letter
        assertThat(r.letters().get(1).getSymbol()).isEqualTo("\uD83D\uDFE1"); // symbol for misplaced letter
        assertThat(r.letters().get(3).getSymbol()).isEqualTo("❌"); //symbol for incorrect letter
    }




}