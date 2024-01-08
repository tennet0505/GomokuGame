package helpers.Enums;

public enum Directions {
    VERTICAL(0, 1),         //from top to down
    HORIZONTAL(1, 0),       //horizontal from left to right
    DiagonalTLtoBR(1, 1),   //from top-left to bottom-right
    DiagonalTRtoBL(-1, -1);   //from top-right to bottom-left
//    MidLeft(-1, 0),
//    BottomRight(1, -1),
//    BottomCenter(0, -1),
//    BottomLeft(-1, 0);

    private int x;
    private int y;
    //Constructor to initialize the instance variable
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
