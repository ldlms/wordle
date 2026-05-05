package com.wordle.interfaces;

import com.wordle.dto.InputList;
import com.wordle.dto.ResponseList;

import java.util.List;

public interface GameUI {

    String askInput();
    boolean askReplay();
    void showResponse(ResponseList responseList);
    void showWin();
    void showLose(List<String> word);
    void showRemainingTries(int remaining);
    void showError(String message);
    void showInputList(InputList inputList);
}
