import helpers.Constants;
import helpers.Enums.PlayerNumber;
import helpers.ErrorMessage;
import helpers.Print;

public class Board {
    public int xSize;
    public int ySize;
    public final int[][] gameBoard;
    private final String[][] exampleBoard;

    int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}}; //without diagonals
    public Board(int boardXSize, int boardYSize) {
        xSize = boardXSize;
        ySize = boardYSize;
        exampleBoard = filledExampleBoard(boardXSize, boardYSize);
        gameBoard = filledGameBoard(boardXSize, boardYSize);
    }

    public void exampleBoard() {
        for (int x = 0; x < xSize; x++) {
            System.out.print("            ");
            for (int y = 0; y < ySize; y++) {
                System.out.print(exampleBoard[x][y] + " ");
            }
            System.out.println();
        }
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
        switch (player.playerNumber) {
            case PlayerNumber.One ->  {
                insertStepToBoard(x,y,1);
            }

            case PlayerNumber.Two ->  {
                insertStepToBoard(x,y,2);
            }
        }
        gameBoard();
    }




    // Private functions:
    private void insertStepToBoard(int x, int y, int playerNumber) {
        if (gameBoard[x][y] == 0) {
            gameBoard[x][y] = playerNumber;
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