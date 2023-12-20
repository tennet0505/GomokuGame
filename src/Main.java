import helpers.PlayerNumber;
import helpers.Print;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Board board = new Board(5,5);
    static GameAssistant assistant = new GameAssistant(board);
    static Player player1 = new Player("", PlayerNumber.One);
    static Player player2 = new Player("", PlayerNumber.Two);

    public static void main(String[] args) {

        assistant.startGame();
//        board.mainMatrix();                                // show default board
        Print.ln("-------------------------------------------------------");
        assistant.setupPlayerName(player1);
        assistant.setupPlayerName(player2);

        assistant.firstStep(player1);
        assistant.checkSteps(player1, player2);




//        board.mainMatrix();                    // show default board
//        Print.ln(player1.playerName);          // show player name
//        assistant.step(player1);     // make a new step for player with x and y
//        ArrayList<String> storedArray = assistant.fetchSteps(player1); // show all player's steps
//        for (String i : storedArray) {
//            System.out.println(i);
//        }
    }


}