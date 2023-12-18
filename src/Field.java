import helpers.PlayerNumber;

public class Field {
    public int xSize;
    public int ySize;
    String[][] fieldMatrix;

    public Field(int fieldXSize, int fieldYSize) {
        xSize = fieldXSize;
        ySize = fieldYSize;
        fieldMatrix = filledDefaultMatrix(fieldXSize, fieldYSize);
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
            case PlayerNumber.One ->  fieldMatrix[x][y] = "  \uD83D\uDD34 ";
            case PlayerNumber.Two ->  fieldMatrix[x][y] = "  \uD83D\uDD35 ";
        }
        mainMatrix();
    }

    public void drawMatrixFieldTest() {
        String[][] matrix = filledDefaultMatrix(xSize, ySize);
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println();
        }
    }
}
