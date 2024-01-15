import helpers.*;
import helpers.Enums.BorderCellMark;
import helpers.Enums.PlayerType;
import models.BoardCell;

import java.util.*;

public class Board {
    public int xSize;
    public int ySize;
    public final int[][] gameBoard;
    private final String[][] exampleBoard;
    ArrayList<BoardCell> playerOneSteps = new ArrayList<>();
    ArrayList<BoardCell> playerTwoSteps = new ArrayList<>();
    public Board(int boardXSize, int boardYSize) {
        xSize = boardXSize;
        ySize = boardYSize;
        exampleBoard = filledExampleBoard(boardXSize, boardYSize);
        gameBoard = filledGameBoard(boardXSize, boardYSize);
    }

    public void setExampleBoard() {
        for (int x = 0; x < 10; x++) {
            System.out.print("            ");
            for (int y = 0; y < 6; y++) {
                System.out.print(exampleBoard[x][y] + " ");
            }
            System.out.println();
        }
        Print.ln(ConstantStrings.dividerLine);
    }

    public void gameBoard() {
        System.out.print("                     ");
        for (int y = 0; y < xSize; y++) {
            System.out.print(" " + StringHelper.extraSpace(xSize) + "Y" + " ");
        }
        System.out.println();
        System.out.print("                     ");
        for (int y = 0; y < xSize; y++) {
            System.out.print(" " + StringHelper.extraSpaceHorizontal(xSize, y) + y + " ");
        }
        System.out.println();
        System.out.print("                     ");
        for (int y = 0; y < xSize; y++) {
            System.out.print(" " + StringHelper.extraSpace(xSize) + "_" + " " );
        }
        System.out.println();
        for (int x = 0; x < xSize; x++) {
            System.out.print("            X - " + StringHelper.extraSpaceVertical(x) + x + " | ");
            for (int y = 0; y < ySize; y++) {
                System.out.print(StringHelper.extraSpace(xSize) + replaceMark(gameBoard[x][y]));
            }
            System.out.println();
        }
    }

    private String replaceMark(int gameBoardCell) {
        return switch (gameBoardCell) {
            case 0 -> BorderCellMark.Empty.mark;
            case 1 -> BorderCellMark.PlayerOne.mark;
            case 2, 3 -> BorderCellMark.PlayerTwo.mark;
            default -> "";
        };
    }

    public void setupStepOnBoard(Player player, BoardCell cell) {
        System.out.println();
        insertStepToBoard(cell, player);
        gameBoard();
    }

    public int getValueForCell(BoardCell cell) {
        return gameBoard[cell.X][cell.Y];
    }

    // Private functions:
    private void insertStepToBoard(BoardCell cell, Player player) {
        if (gameBoard[cell.X][cell.Y] == 0) {
            gameBoard[cell.X][cell.Y] = player.playerType.value;
            if (player.playerType == PlayerType.HumanOne) {
                playerOneSteps.add(cell);
            } else {
                playerTwoSteps.add(cell);
            }
        } else {
            Print.ln(ErrorMessage.errorWrongLocation);
        }
    }

    private String[][] filledExampleBoard(int fieldXSize, int fieldYSize) {
        String[][] arrayUpdated = new String[fieldXSize][fieldYSize];
        for (int x = 0; x < fieldXSize; x++) {
            for (int y = 0; y < fieldYSize; y++) {
                arrayUpdated[x][y] = String.format("[%s,%s]", x, y);
            }
        }
        return arrayUpdated;
    }
    private int[][] filledGameBoard(int fieldXSize, int fieldYSize) {
        int[][] arrayUpdated = new int[fieldXSize][fieldYSize];
        for (int x = 0; x < fieldXSize; x++) {
            for (int y = 0; y < fieldYSize; y++) {
                arrayUpdated[x][y] = 0;
            }
        }
        return arrayUpdated;
    }
}
