import helpers.PlayerNumber;

import java.util.ArrayList;

public class Field {
    public int xSize;
    public int ySize;
    private final String[][] fieldMatrix;
    final ArrayList<String> passedStepsPlayerOne = new ArrayList<>();
    final ArrayList<String> passedStepsPlayerTwo = new ArrayList<>();

    public Field(int fieldXSize, int fieldYSize) {
        xSize = fieldXSize;
        ySize = fieldYSize;
        fieldMatrix = filledDefaultMatrix(fieldXSize, fieldYSize);
    }

    public void mainMatrix() {
        System.out.println();
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                System.out.print(fieldMatrix[x][y] + " ");
            }
            System.out.println();
        }
    }

    public void setupStepInMatrix(Player player, int x, int y) {
        switch (player.playerNumber) {
            case PlayerNumber.One ->  {
                fieldMatrix[x][y] = "  \uD83D\uDD34 ";
                passedStepsPlayerOne.add("["+x+","+y+"]");
            }

            case PlayerNumber.Two ->  {
                fieldMatrix[x][y] = "  \uD83D\uDD35 ";
                passedStepsPlayerTwo.add("["+x+","+y+"]");
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
