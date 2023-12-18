import helpers.PlayerNumber;
import helpers.Print;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        Field field = new Field(5,5);
        Player player1 = new Player("", PlayerNumber.One);
        Player player2 = new Player("", PlayerNumber.Two);

//        field.drawMatrixFieldTest();

        GameAsisstant.startGame();
        GameAsisstant.setupPlayerName(player1);
        GameAsisstant.setupPlayerName(player2);

        field.mainMatrix();
        Print.ln(player1.playerName);
        GameAsisstant.step(player1, field,3,2);

        Print.ln(player2.playerName);
        GameAsisstant.step(player2, field,4,2);

    }
}