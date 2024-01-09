package helpers.Enums;

public enum PlayerType {
    HumanOne(1),
    HumanTwo(2),
    AI(3);

    public final int value;

     PlayerType(int value) {
        this.value = value;
    }
}
