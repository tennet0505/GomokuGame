import helpers.Enums.PlayerType;

public class Player {
    public String playerName;
    public PlayerType playerType;

    public Player(String name, PlayerType playertype) {
        playerType = playertype;
        playerName = name;
    }
}
