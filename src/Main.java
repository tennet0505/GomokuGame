import helpers.Constants;
import helpers.Enums.GameType;
import helpers.Enums.PlayerType;
public class Main {
    static Board board = new Board(Constants.boardX,Constants.boardY);
    static GameAssistant gameAssistant = new GameAssistant(board);
    static Player player1 = new Player("", PlayerType.HumanOne);
    static Player player2 = new Player("", PlayerType.HumanTwo);
    static GameType gameType = GameType.HumanVsDumbAI;

    public static void main(String[] args) {

        gameAssistant.gameType = gameType;
        gameAssistant.setGameTitle();
        gameAssistant.setExampleBoard();
        gameAssistant.setBoardSize();
        gameType = gameAssistant.chooseGameType();
        gameAssistant.setupNameFor(player1);
        switch (gameType) {
            case GameType.HumanVsHuman:
                gameAssistant.setupNameFor(player2);
                break;
            case GameType.HumanVsDumbAI:
                player2 = new Player("My name is AI", PlayerType.AI);
        }
        gameAssistant.startGame(player1);
        gameAssistant.checkStepsTurnPlayers(player1, player2);
    }
}
