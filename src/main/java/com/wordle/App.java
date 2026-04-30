package com.wordle;

import com.wordle.api.ApiClient;
import com.wordle.game.Game;
import com.wordle.interfaces.ApiService;


public class App {
    public static void main(String[] args) {
        ApiService apiClient = new ApiClient();
        Game game = new Game(apiClient);
        game.startGame();

    }
}
