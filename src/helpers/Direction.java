package helpers;

import models.BoardCell;

public class Direction {
    public static String[] possibleDirection = {"(-1,0)", "(0,1)","(1,0)","(0,-1)","(-1,1)","(1,1)","(1,-1)","(-1,-1)"};
    public static BoardCell vertical = new BoardCell(0,1);
}
