import java.util.ArrayList;

public class Field {
    public int xSize;
    public int ySize;
    ArrayList<String[][]> fieldMatrix;

    public Field(int fieldXSize, int fieldYSize) {
        xSize = fieldXSize;
        ySize = fieldYSize;
        fieldMatrix = new ArrayList<>();
    }

    private String[][] filledDefaultMatrix(int fieldXSize, int fieldYSize) {
        String[][] arrayUpdated = new String[fieldXSize][fieldYSize];
        for (int x = 0; x < fieldXSize; x++) {
            for (int y = 0; y < fieldYSize; y++) {
                arrayUpdated[x][y] = String.format("[%s, %s]", x, y);
            }
        }
        return arrayUpdated;
    }

    public void drawMatrixField() {
        String[][] matrix = filledDefaultMatrix(xSize, ySize);
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println();
        }
    }
}
