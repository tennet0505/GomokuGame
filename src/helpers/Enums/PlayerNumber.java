package helpers.Enums;

public enum PlayerNumber {
    One(1),
    Two(2);

    public final int value;

    private PlayerNumber(int value) {
        this.value = value;
    }
}
