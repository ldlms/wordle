package com.wordle.UI;

import com.wordle.dto.ResponseList;
import com.wordle.interfaces.GameUI;

import java.util.List;
import java.util.Scanner;

public class ConsoleGameUI implements GameUI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askInput() {
        System.out.println("Entrez votre proposition : ");
        return scanner.nextLine();
    }

    @Override
    public boolean askReplay() {
        System.out.println("Encore une partie ? y/n");
        return scanner.nextLine().equals("y");
    }

    @Override
    public void showResponse(ResponseList responseList) {
        System.out.println(responseList.getResponses());
    }

    @Override public void showWin() { System.out.println("Bravo !"); }
    @Override public void showLose(List<String> word) { System.out.println("Perdu ! La réponse était : " + word); }
    @Override public void showRemainingTries(int r) { System.out.println("Encore " + r + " essais"); }
    @Override public void showError(String msg) { System.out.println(msg); }
}
