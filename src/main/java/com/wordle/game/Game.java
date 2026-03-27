package com.wordle.game;

import com.wordle.api.ApiClient;
import com.wordle.dto.Response;
import com.wordle.dto.Status;
import com.wordle.services.GameService;
import java.util.List;
import java.util.Scanner;


public class Game {

    private final List<String> word;
    public Boolean isGameComplete = false;
    public Integer remaningTries = 6;
    Scanner scanner = new Scanner(System.in);
    private final GameService service = new GameService();

    public Game(ApiClient apiClient) {
        this.word = apiClient.callApi();
    }

    public void startGame(){
        System.out.println("Entrez votre proposition : ");
        System.out.print("le not recherché est :" + this.word);
        while(scanner.hasNextLine() && !isGameComplete){
            String input = scanner.nextLine();
            service.processInput(input, this.word);
            Response response = this.service.processInput(input, this.word);
            System.out.println(response.display());
            remaningTries--;
            if(!(remaningTries == 0) && !isGameComplete){
                System.out.println("encore " + remaningTries + " essais");
                startGame();
            }

            System.out.println("partie terminée, la bonne réponse etait" + this.word);
        }
    }
}
