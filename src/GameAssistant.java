import helpers.Constants;
import helpers.Enums.Directions;
import helpers.Enums.PlayerNumber;
import helpers.ErrorMessage;

import java.util.Scanner;

public class GameAssistant {
    static Scanner scanner = new Scanner(System.in);
    Board board;
    Boolean isFirstPlayerTurn = true;
    Boolean isGameFinished = false;

    public GameAssistant(Board boardMain) {
        board = boardMain;
    }
    public void gameTitle() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                     Start game!");
        System.out.println("-------------------------------------------------------");
    }

    public void setupName(Player player) {
        String playerNumber = player.playerNumber == PlayerNumber.One ? "Player 1" : "Player 2";
        System.out.print(playerNumber + " enter your name: ");
        String name = scanner.nextLine();
        player.playerName = name;
        System.out.printf("Nice to meet you, %s!", name);
        System.out.println();
    }

    public void gameStart(Player player) {
        System.out.println();
        System.out.printf("Let's start, %s. You are the first!", player.playerName);
    }

    public void checkSteps(Player player1, Player player2) {
        if (isFirstPlayerTurn) {
            isFirstPlayerTurn = false;
            makeStep(player1);
        } else {
            isFirstPlayerTurn = true;
            makeStep(player2);
        }
        if (gameIsFinished()) {
            checkSteps(player1, player2);
        }
    }

    //Private functions:
    private void makeStep(Player player) {
        System.out.println();
        System.out.print("\uD83E\uDD14 -=[ " + player.playerName +  " ]=-" + " choose X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Choose Y coordinate: ");
        int y = scanner.nextInt();
        if (isValidLocation(x,y)) {
            board.setupStepOnBoard(player, x, y);
            isGameFinished = board.checkIfIsWinStepFor(player, x, y);
        } else {
            System.out.print(ErrorMessage.errorOutOfBoardBoundsLocation);
            makeStep(player);
        }
    }

    private Boolean gameIsFinished() {
        if (!isGameFinished) {
            return true;
        } else {
            System.out.println("=====GAME OVER=====");
            return false;
        }
    }

    private Boolean isValidLocation(int x, int y) {
        return (x >= 0 && x < board.xSize) && (y >= 0 && y < board.ySize)
//                && !isOutOfBounds(x,y)
                && !isCellOccupied(x,y);
    }

    private Boolean isCellOccupied(int x, int y) {
        return board.gameBoard[x][y] != 0;
    }
//    private Boolean isOutOfBounds(int x, int y) {
//        try {
//            return board.gameBoard[x][y] != 0;
//        } catch(Error e) {
//            return true;
//        }
//    }
}
