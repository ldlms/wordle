package com.wordle.unit;

import com.wordle.UI.TestGameUI;
import com.wordle.api.ApiClientTest;
import com.wordle.game.Game;
import com.wordle.services.GameService;
import com.wordle.services.WordService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {


    @Test
    void ShouldWinOnFirstTryAndHaveFiveTryLeft() {
        TestGameUI ui = new TestGameUI("livre", "n");
        Game game = new Game(new ApiClientTest("LIVRE"), ui, new GameService(), new WordService());

        game.startGame();

        assertThat(ui.displayedMessages).contains("Bravo !");
        assertThat(game.remainingTries).isEqualTo(5);
    }

    @Test
    void ShouldWinOnThirdTryAndHaveThreeTryLeft() {
        TestGameUI ui = new TestGameUI("crabe", "arbre", "crane", "n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        assertThat(ui.displayedMessages).contains("Bravo !");
        assertThat(game.remainingTries).isEqualTo(3);
    }

    @Test
    void ShouldLooseAfterAllTriesAreUsed() {
        TestGameUI ui = new TestGameUI("crabe","arbre","barbe","grand","gland","gorge", "n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        assertThat(ui.displayedMessages).contains("Perdu ! La réponse était : " + List.of("C", "R", "A", "N", "E"));
        assertThat(game.remainingTries).isEqualTo(0);
    }

    @Test
    void ShouldWinAfterAnErrorIsCAughtBecauseOfWordLength() {

        TestGameUI ui = new TestGameUI("troplongtroplong", "CRANE", "n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        assertThat(ui.displayedMessages).anyMatch(m -> m.startsWith("ERREUR:"));
        assertThat(ui.displayedMessages).contains("Bravo !");
    }

    @Test
    void ShouldWinThenAskForAReplayThenQuitHavingWonTwoTimes() {
        TestGameUI ui = new TestGameUI("crane", "y", "crane", "n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        long winCount = ui.displayedMessages.stream().filter("Bravo !"::equals).count();
        assertThat(winCount).isEqualTo(2);
    }

    @Test
    void ShouldWinThenQuit() {
        TestGameUI ui = new TestGameUI("crane", "n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        long winCount = ui.displayedMessages.stream().filter("Bravo !"::equals).count();
        assertThat(winCount).isEqualTo(1);
    }

}