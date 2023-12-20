import helpers.PlayerNumber;

import java.util.ArrayList;
import java.util.Scanner;

public class GameAssistant {
    static Scanner scanner = new Scanner(System.in);
    Board board;
    Boolean isFirstPlayerTurn = true;
    Boolean isGameFinished = false;

    public GameAssistant(Board boardMain) {
        board = boardMain;
    }
    public void startGame() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                     Start game!");
        System.out.println("-------------------------------------------------------");
    }
    public void gameRule() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                     Game rule description");
        System.out.println("-------------------------------------------------------");
    }

    public void setupPlayerName(Player player) {
        String playerNumber = player.playerNumber == PlayerNumber.One ? "Player 1" : "Player 2";
        System.out.print(playerNumber + " enter your name: ");
        String name = scanner.nextLine();
        player.playerName = name;
        System.out.printf("Nice to meet you, %s!", name);
        System.out.println();
    }

    public void firstStep(Player player) {
        System.out.println();
        System.out.printf("Let's start, %s. You are the first!", player.playerName);
    }

    public void checkSteps(Player player1, Player player2) {
        if (isFirstPlayerTurn) {
            isFirstPlayerTurn = false;
            step(player1);
        } else {
            isFirstPlayerTurn = true;
            step(player2);
        }
        checkSteps(player1, player2);
    }
    private void step(Player player) {
        System.out.println();
        System.out.print(player.playerName + " choose X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Choose Y coordinate: ");
        int y = scanner.nextInt();
        if (isValidCoordiante(x,y)) {
            board.setupStepInMatrix(player, x, y);
        } else {
            System.out.print("This coordinate is choose or out of bounds. Please choose another coordinate.");
            step(player);
        }
    }

    public Boolean isGameFinished() {
        if (!isGameFinished) {
            return true;
        } else {
            System.out.println("=====GAME OVER=====");
            return false;
        }
    }
    public ArrayList<String> fetchSteps(Player player) {
        return board.fetchSteps(player);
    }

    private Boolean isValidCoordiante(int x, int y) {
        return (!board.passedStepsPlayerOne.contains("(" + x + "," + y + ")")) &&
                (!board.passedStepsPlayerTwo.contains("(" + x + "," + y + ")")) &&
                (x >= 0 && x < board.xSize) && (y >= 0 && y < board.ySize);
    }
}
