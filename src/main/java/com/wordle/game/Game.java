package com.wordle.game;

import com.wordle.UI.ConsoleGameUI;

import com.wordle.dto.Response;
import com.wordle.dto.ResponseList;
import com.wordle.exceptions.IncorrectSizeForInput;
import com.wordle.interfaces.ApiService;
import com.wordle.interfaces.GameUI;
import com.wordle.services.GameService;
import com.wordle.services.WordService;
import java.util.List;


public class Game {

    private final List<String> word;
    public Boolean isGameComplete = false;
    public Integer remainingTries = 6;
    public ResponseList responseList = new ResponseList();

    private final GameUI ui;
    private final GameService gameService;
    private final WordService wordService;
    private final ApiService apiService;


    //default constructor
    public Game(ApiService apiService) {
        this(apiService, new ConsoleGameUI(), new GameService(), new WordService());
    }

    //constructor used in handle replay
    public Game(ApiService apiService, GameUI ui, GameService gameService, WordService wordService) {
        this.word = apiService.callApi();
        this.ui = ui;
        this.gameService = gameService;
        this.wordService = wordService;
        this.apiService = apiService;
    }

    public void startGame() {
        try {
            while (!isGameComplete && remainingTries > 0) {
                String input = ui.askInput();
                wordService.verifyInput(input);

                Response result = gameService.processInput(input, this.word);
                responseList.addResponse(result);
                ui.showResponse(responseList);
                remainingTries--;

                if (result.isGameWon()) {
                    ui.showWin();
                    isGameComplete = true;
                    break;
                }

                if (remainingTries == 0) {
                    ui.showLose(this.word);
                    break;
                }

                ui.showRemainingTries(remainingTries);
            }

            handleReplay();

        } catch (IncorrectSizeForInput e) {
            ui.showError(e.getMessage());
            startGame();
        }
    }

    void handleReplay() {
        if (ui.askReplay()) {
            Game newGame = new Game(apiService, ui, gameService, wordService);
            newGame.startGame();
        }
    }
}