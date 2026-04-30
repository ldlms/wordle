package com.wordle.unit;

import com.wordle.api.ApiClientTest;
import com.wordle.game.Game;
import com.wordle.services.GameService;
import com.wordle.services.WordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GameTest {


    private ByteArrayOutputStream consoleOutput;
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }


    private Game createTestGame(String word, String input){
        ApiClientTest apiClient = new ApiClientTest();
        apiClient.callTestApi(word);
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        Game game = new Game(apiClient,scanner, new GameService(),new WordService());
        return game;
    }


}
