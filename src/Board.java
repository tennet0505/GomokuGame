import helpers.Enums.Directions;
import helpers.Enums.PlayerNumber;
import helpers.ErrorMessage;
import helpers.Print;
import models.BoardCell;
import java.util.ArrayList;
import java.util.Arrays;

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
        for (int x = 0; x < xSize; x++) {
            System.out.print("            ");
            for (int y = 0; y < ySize; y++) {
                System.out.print(exampleBoard[x][y] + " ");
            }
            System.out.println();
        }
        Print.ln("-------------------------------------------------------");
    }

    public void gameBoard() {
        for (int x = 0; x < xSize; x++) {
            System.out.print("            ");
            for (int y = 0; y < ySize; y++) {
                System.out.print(gameBoard[x][y] + "  ");
            }
            System.out.println();
        }
    }

    public void setupStepOnBoard(Player player, int x, int y) {
        System.out.println();
        insertStepToBoard(x,y,player);
        gameBoard();

    }
    public boolean checkIfIsWinStepFor(Player player, int x, int y) {
        boolean isWin = (checkDirectionForWin(player, x, y, Directions.HORIZONTAL) ||
                checkDirectionForWin(player, x, y, Directions.VERTICAL) ||
                checkDirectionForWin(player, x, y, Directions.DiagonalTRtoBL) ||
                checkDirectionForWin(player, x, y, Directions.DiagonalTLtoBR));
        if (isWin) {
            System.out.println("Congrats! " + player.playerName + ", you are a WINNER!!!");
            return true;
        }
        return false;
    }

    public boolean checkDirectionForWin(Player player, int x, int y, Directions direction) {
        int countWinCell = 0;
        for (int i = -4; i <= 4; i++) {
            int new_row = x + i * direction.getDirectionX();
            int new_column = y + i * direction.getDirectionY();

            if (isValidCell(new_row, new_column) && gameBoard[new_row][new_column] == player.playerNumber.value) {
                countWinCell++;
                if (countWinCell == 5) {
                    return true;
                }
            } else {
                countWinCell = 0;
            }
        }
        return false;
    }

    public boolean isValidCell(int x, int y) {
        return (x >= 0 && x < xSize) && (y >= 0 && y < ySize);
    }



    public void checkDiagonals() {
        int[] arr = new int[xSize];
        for (int i = 0; i < gameBoard[0].length; i++) {
            arr[i] = gameBoard[i][i];
        }
    }

    public boolean checkingPossibleStepForRow(int row) {
        if (gameBoard[row].length > 5 & isAcceptableCountOfEmptyCellsForRow(row)) {

        }

        return true;
    }

    //checking five cell in a row for are there 0 and 1 or 2. will check row if horizontal and column if vertical.
    //checking possible steps.
    private boolean isAreThereFiveCellsOfOneAndEmpty() {
       return false;
    }

    private boolean isAcceptableCountOfEmptyCellsForRow(int row) {
        return Arrays.stream(gameBoard[row])
                .filter(x-> x == 0).toArray().length > 4;
    }
    private boolean isAcceptableCountOfEmptyCellsForRowInARow(int row, PlayerNumber playerNumber) {
        return Arrays.stream(gameBoard[row])
                .filter(x-> x == 0).toArray().length > 4;
    }

    private void checkingAreThereFiveValidCellsInARow(int y) {
        for (int i = y - 4; i < 4 - 1; i++) {

        }
    }
    private boolean checkingWinPosition(PlayerNumber playerNumber, int row) {
        boolean isWin = false;
        int countInARow = 0;
        switch (countInARow) {
            case 4:
//                checkingStartAndTail();
            case 3:

            case 2:

            default:

        }
        return isWin;
    }



    // Private functions:
    private void insertStepToBoard(int x, int y, Player player) {
        if (gameBoard[x][y] == 0) {
            gameBoard[x][y] = player.playerNumber.value;
            BoardCell cell = new BoardCell(x,y);
            if (player.playerNumber == PlayerNumber.One) {
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




// check boards's edges
// if x > 0 and x < sizeX return true
// if y > 0 and x < sizeY return true

// check dead locks
// if neighbourCell isEmpty return false

// check win pattern
// if there are 4 ident cells return true

// check guard pattern
// if there are 3 ident cells and there are empty cells from tail and nose return true

// check attack pattern
// if there are 3 opponent's cells and there are empty cells from tail and nose return true
