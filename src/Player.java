import helpers.Enums.PlayerNumber;

public class Player {
    public String playerName;
    public PlayerNumber playerNumber;

    public Player(String name, PlayerNumber playernumber) {
        playerNumber = playernumber;
        playerName = name;
    }
}
