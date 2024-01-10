import helpers.ArraySort;
import helpers.ConstantStrings;
import helpers.Enums.Directions;
import helpers.Enums.PlayerType;
import models.BoardCell;

public class GameLogic {

    Board board;
    public GameLogic(Board boardMain) {
        board = boardMain;
    }

    public boolean checkIfIsWinStepFor(Player player, BoardCell cell) {
        boolean isWin = (checkDirectionForWin(player, cell, Directions.HORIZONTAL) ||
                checkDirectionForWin(player, cell, Directions.VERTICAL) ||
                checkDirectionForWin(player, cell, Directions.DiagonalTRtoBL) ||
                checkDirectionForWin(player, cell, Directions.DiagonalTLtoBR));
        if (isWin) {
            System.out.println(ConstantStrings.congrats + player.playerName + ConstantStrings.youAreWinner);
            return true;
        }
        return false;
    }

    public boolean checkDirectionForWin(Player player, BoardCell cell, Directions direction) {
        int countWinCell = 0;
        for (int i = -4; i <= 4; i++) {
            int new_row = cell.X + i * direction.getDirectionX();
            int new_column = cell.Y + i * direction.getDirectionY();

            BoardCell new_cell = new BoardCell(new_row, new_column);
            if (isValidCell(new_cell) && board.gameBoard[new_row][new_column] == player.playerType.value) {
                countWinCell++;
                if (countWinCell == 5) {
                    return true;
                }
            } else {
                countWinCell = 0;
            }
        }
        return false;
    }

    public BoardCell checkAndGetCellForPreWinPosition() {
        if (checkDirectionForPreWinPosition(Directions.HORIZONTAL) != null) {
            return checkDirectionForPreWinPosition(Directions.HORIZONTAL);
        }
        if (checkDirectionForPreWinPosition(Directions.VERTICAL) != null) {
            return checkDirectionForPreWinPosition(Directions.VERTICAL);
        }
        if (checkDirectionForPreWinPosition(Directions.DiagonalTLtoBR) != null) {
            return checkDirectionForPreWinPosition(Directions.DiagonalTLtoBR);
        }
        if (checkDirectionForPreWinPosition(Directions.DiagonalTRtoBL) != null) {
            return checkDirectionForPreWinPosition(Directions.DiagonalTRtoBL);
        }
        return null;
    }

    //check for preWin position when there are three "1"  in a row
    public BoardCell checkDirectionForPreWinPosition(Directions direction) {
        int countWinCell = 0;
        board.playerOneSteps.sort(new ArraySort());
        for (BoardCell playerOneStep : board.playerOneSteps) {
            System.out.println("playerOneStep: " + playerOneStep.X + ", " + playerOneStep.Y);
            for (int i = -4; i <= 4; i++) {
                int newRow = playerOneStep.X + i * direction.getDirectionX();
                int newColumn = playerOneStep.Y + i * direction.getDirectionY();
                BoardCell newCell = new BoardCell(newRow, newColumn);
                if (isValidCell(newCell) && board.gameBoard[newCell.X][newCell.Y] == PlayerType.HumanOne.value) {
                    countWinCell++;
                    if (countWinCell == 3) {
                        return  (getForwardCell(newCell, direction) != null) ? getForwardCell(newCell, direction)
                                : ((getTailCell(newCell, direction) != null) ? getTailCell(newCell, direction)
                                : null);
                    }
                    //make checking condition for 4 cells in a row
                    if (countWinCell == 4) {

                    }
                    //make checking condition for 5 cells with one empty cell inside
                    if (countWinCell == 5) {
                        return  (getForwardCell(newCell, direction) != null) ? getForwardCell(newCell, direction)
                                : ((getTailCell(newCell, direction) != null) ? getTailCell(newCell, direction)
                                : null);
                    }
                }
            }
            return null;
        }
        return null;
    }

    private BoardCell getTailCell(BoardCell cell, Directions direction) {
        int tailRow = cell.X + direction.getDirectionX();
        int tailColumn = cell.Y + direction.getDirectionY();
        BoardCell new_last_cell = new BoardCell(tailRow, tailColumn);
        if (isValidCell(new_last_cell) && board.gameBoard[tailRow][tailColumn] == 0) {
            return new BoardCell(tailRow, tailColumn);
        }
        return null;
    }
    private BoardCell getForwardCell(BoardCell cell, Directions direction) {
        int tailRow = cell.X - 3 * direction.getDirectionX();
        int tailColumn = cell.Y - 3 * direction.getDirectionY();
        BoardCell new_last_cell = new BoardCell(tailRow, tailColumn);
        if (isValidCell(new_last_cell) && board.gameBoard[tailRow][tailColumn] == 0) {
            return new BoardCell(tailRow, tailColumn);
        }
        return null;
    }

    public boolean isValidCell(BoardCell cell) {
        return (cell.X >= 0 && cell.X < board.xSize) && (cell.Y >= 0 && cell.Y < board.ySize);
    }

}
