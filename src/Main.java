import helpers.PlayerNumber;
import helpers.Print;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Field field = new Field(5,5);
    static GameAssistant assistant = new GameAssistant(field);
    static Player player1 = new Player("", PlayerNumber.One);
    static Player player2 = new Player("", PlayerNumber.Two);
    public static void main(String[] args) {

        assistant.startGame();
        assistant.setupPlayerName(player1);
        System.out.println();
        assistant.setupPlayerName(player2);

        field.mainMatrix();
        Print.ln(player1.playerName);
        assistant.step(player1,3,2);

        Print.ln(player2.playerName);
        assistant.step(player2,4,2);

        ArrayList<String> storedArray = assistant.fetchSteps(player1);
        for (String i : storedArray) {
            System.out.println(i);
        }
    }
}