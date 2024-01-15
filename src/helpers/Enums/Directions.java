package helpers.Enums;

public enum Directions {
    VERTICAL(1, 0),         //vertical
    HORIZONTAL(0, 1),       //horizontal
    DiagonalTLtoBR(1, 1),   //diagonal \
    DiagonalTRtoBL(-1, 1); //diagonal /

    private final int x;
    private final int y;
    Directions(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getDirectionX() {
        return this.x;
    }
    public int getDirectionY() {
        return this.y;
    }
}
