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
    void ShouldHaveFiveTryLeftGivenGoodTryOnFirstGuess() {
        TestGameUI ui = new TestGameUI("livre", "n");
        Game game = new Game(new ApiClientTest("LIVRE"), ui, new GameService(), new WordService());

        game.startGame();

        assertThat(ui.displayedMessages).contains("Bravo !");
        assertThat(game.remainingTries).isEqualTo(5);
    }

    @Test
    void ShouldHaveThreeTryLeftGivenWonOnThirdTry() {
        TestGameUI ui = new TestGameUI("crabe", "arbre", "crane", "n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        assertThat(ui.displayedMessages).contains("Bravo !");
        assertThat(game.remainingTries).isEqualTo(3);
    }

    @Test
    void ShouldLooseGivenAllTriesAreUsed() {
        TestGameUI ui = new TestGameUI("crabe","arbre","barbe","grand","gland","gorge", "n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        assertThat(ui.displayedMessages).contains("Perdu ! La réponse était : " + List.of("C", "R", "A", "N", "E"));
        assertThat(game.remainingTries).isZero();
    }

    @Test
    void ShouldThrowAnErrorGivenInputTooLongThenWin() {

        TestGameUI ui = new TestGameUI("troplongtroplong", "CRANE", "n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        assertThat(ui.displayedMessages).anyMatch(m -> m.startsWith("ERREUR:"))
                                        .contains("Bravo !");
    }

    @Test
    void ShouldWinThenReplayThenQuit() {
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

    @Test
    void ResponseListShouldContainThreeResponsesAfterThreeTries(){
        TestGameUI ui = new TestGameUI("crabe", "arbre", "crane","n");
        Game game = new Game(new ApiClientTest("CRANE"), ui, new GameService(), new WordService());

        game.startGame();

        int responseCount = game.responseList.getResponses().size();
        assertThat(responseCount).isEqualTo(3);
    }

}