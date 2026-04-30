package com.wordle.game;

import com.wordle.api.ApiClient;
import com.wordle.dto.Response;
import com.wordle.dto.ResponseList;
import com.wordle.exceptions.InputTooLongException;
import com.wordle.interfaces.ApiService;
import com.wordle.services.GameService;
import com.wordle.services.WordService;
import java.util.List;
import java.util.Scanner;


public class Game {

    private final List<String> word;
    public Boolean isGameComplete = false;
    public Integer remainingTries = 6;
    public ResponseList responseList = new ResponseList();
    private final Scanner scanner;
    private final GameService gameService;
    private final WordService wordService;
    private final ApiService apiService;

    public Game(ApiService apiService) {
        this(apiService, new Scanner(System.in), new GameService(), new WordService());
    }

    public Game(ApiService apiService, Scanner scanner, GameService gameService, WordService wordService) {
        this.word = apiService.callApi();
        this.scanner = scanner;
        this.gameService = gameService;
        this.wordService = wordService;
        this.apiService = apiService;
    }

    public void startGame(){
        System.out.println("=== WORDLE ===");
        System.out.println("Entrez votre proposition : ");
        System.out.print("le not recherché est :" + this.word);
        try{
            while(scanner.hasNextLine() && !isGameComplete){
                String input = scanner.nextLine();
                wordService.verifyInput(input);
                Response result = gameService.processInput(input, this.word);
                responseList.addResponse(result);
                System.out.println(responseList.getResponses());
                remainingTries--;
                if (result.isGameWon()) {
                    System.out.println("Bravo, c'est gagné !");
                    isGameComplete = true;
                    break;
                }

                if (remainingTries == 0) {
                    System.out.println("Partie terminée, la bonne réponse était : " + this.word);
                    break;
                }
                handleReplay();
            }
        } catch (InputTooLongException e) {
            System.out.println(e.getMessage());
            startGame();
        }

    }

    void handleReplay() {
        System.out.println("Encore une partie ? y/n");
        if (scanner.hasNextLine()) {
            String choice = scanner.nextLine();
            if (choice.equals("n")) {
                System.exit(0);
            } else {
                Game newGame = new Game(apiService, scanner, gameService, wordService);
                newGame.startGame();
            }
        }
    }
}
