package com.wordle;

import com.wordle.api.ApiClient;
import com.wordle.game.Game;


public class App {
    public static void main(String[] args) {
        ApiClient  apiClient = new ApiClient();
        Game game = new Game(apiClient);
        System.out.println("=== WORDLE ===");
        game.startGame();

    }
}
