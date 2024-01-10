package helpers;

import models.BoardCell;
import java.util.Comparator;
public class ArraySort implements Comparator<BoardCell> {
    @Override
    public int compare(BoardCell cell, BoardCell cell2) {
        // First, compare based on x-coordinate
        int xComparison = Integer.compare(cell.X, cell2.X);
        if (xComparison != 0) {
            return xComparison;
        }

        // If x-coordinates are equal, compare based on y-coordinate
        return Integer.compare(cell.Y, cell2.Y);
    }
}
