package com.wordle.UI;

import com.wordle.dto.ResponseList;
import com.wordle.interfaces.GameUI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TestGameUI implements GameUI {

    private final Queue<String> inputs;
    public final List<String> displayedMessages = new ArrayList<>();

    public TestGameUI(String... inputs) {
        this.inputs = new LinkedList<>(List.of(inputs));
    }

    @Override
    public String askInput() {
        return inputs.poll();
    }

    @Override
    public boolean askReplay() {
        String answer = inputs.poll();
        return "y".equals(answer);
    }

    @Override public void showResponse(ResponseList r) { displayedMessages.add(r.getResponses().toString()); }
    @Override public void showWin() { displayedMessages.add("Bravo !"); }
    @Override public void showLose(List<String> word) { displayedMessages.add("Perdu ! La réponse était : " + word); }
    @Override public void showRemainingTries(int r) { displayedMessages.add("essais restants:" + r); }
    @Override public void showError(String msg) { displayedMessages.add("ERREUR:" + msg); }
}
