package helpers;

public enum Directions {
    TopLeft(-1, 1),
    TopCenter(0, 1),
    TopRight(1, 1),
    MidRight(1, 0),
    MidLeft(-1, 0),
    BottomRight(1, -1),
    BottomCenter(0, -1),
    BottomLeft(-1, 0);

    Directions(int x, int y) {
    }
}
