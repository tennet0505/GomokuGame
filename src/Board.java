import helpers.PlayerNumber;

import java.util.ArrayList;

public class Board {
    public int xSize;
    public int ySize;
    private final String[][] fieldMatrix;
    final ArrayList<String> passedStepsPlayerOne = new ArrayList<>();
    final ArrayList<String> passedStepsPlayerTwo = new ArrayList<>();

    int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}}; //without diagonals
    public Board(int fieldXSize, int fieldYSize) {
        xSize = fieldXSize;
        ySize = fieldYSize;
        fieldMatrix = filledDefaultMatrix(fieldXSize, fieldYSize);
    }

    public void mainMatrix() {
        for (int x = 0; x < xSize; x++) {
            System.out.print("            ");
            for (int y = 0; y < ySize; y++) {
                System.out.print(fieldMatrix[x][y] + " ");
            }
            System.out.println();
        }
    }

    public void setupStepInMatrix(Player player, int x, int y) {
        System.out.println();
        switch (player.playerNumber) {
            case PlayerNumber.One ->  {
                fieldMatrix[x][y] = "  \uD83D\uDD34 ";
                passedStepsPlayerOne.add("("+x+","+y+")");
            }

            case PlayerNumber.Two ->  {
                fieldMatrix[x][y] = "  \uD83D\uDD35 ";
                passedStepsPlayerTwo.add("("+x+","+y+")");
            }
        }
        mainMatrix();
    }

    public ArrayList<String> fetchSteps(Player player) {
        switch (player.playerNumber) {
            case PlayerNumber.One -> {
                return passedStepsPlayerOne;
            }
            case PlayerNumber.Two -> {
                return passedStepsPlayerTwo;
            }
        }
        return null;
    }

    private String[][] filledDefaultMatrix(int fieldXSize, int fieldYSize) {
        String[][] arrayUpdated = new String[fieldXSize][fieldYSize];
        for (int x = 0; x < fieldXSize; x++) {
            for (int y = 0; y < fieldYSize; y++) {
                arrayUpdated[x][y] = String.format("[%s,%s]", x, y);
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