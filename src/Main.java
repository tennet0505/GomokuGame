import helpers.Constants;
import helpers.Enums.PlayerNumber;
public class Main {
    static Board board = new Board(Constants.boardX,Constants.boardY);
    static GameAssistant gameAssistant = new GameAssistant(board);
    static Player player1 = new Player("", PlayerNumber.One);
    static Player player2 = new Player("", PlayerNumber.Two);

    public static void main(String[] args) {

        gameAssistant.setGameTitle();
        gameAssistant.setExampleBoard();               // show default board
        gameAssistant.setupNameFor(player1);
        gameAssistant.setupNameFor(player2);
        gameAssistant.startGame(player1);
        gameAssistant.checkStepsTurnPlayers(player1, player2);





//        board.mainMatrix();                    // show default board
//        Print.ln(player1.playerName);          // show player name
//        assistant.step(player1);     // make a new step for player with x and y
//        ArrayList<String> storedArray = assistant.fetchSteps(player1); // show all player's steps
//        for (String i : storedArray) {
//            System.out.println(i);
//        }
    }
}
