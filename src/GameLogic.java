import helpers.ArraySort;
import helpers.ConstantStrings;
import helpers.Enums.Directions;
import helpers.Enums.PlayerType;
import models.BoardCell;

import java.util.Random;

public class GameLogic {

    Board board;
    public GameLogic(Board boardMain) {
        board = boardMain;
    }

    public boolean checkIfIsGameFinished(Player player, BoardCell cell) {
        boolean isWin = (checkDirectionForWin(player, cell, Directions.HORIZONTAL) ||
                checkDirectionForWin(player, cell, Directions.VERTICAL) ||
                checkDirectionForWin(player, cell, Directions.DiagonalTRtoBL) ||
                checkDirectionForWin(player, cell, Directions.DiagonalTLtoBR));
        if (isWin) {
            System.out.println(ConstantStrings.gameOver);
            System.out.println(ConstantStrings.congrats + player.playerName + ConstantStrings.youAreWinner);
            return true;
        }
        return false;
    }

    private boolean checkDirectionForWin(Player player, BoardCell cell, Directions direction) {
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

    public BoardCell checkAndGetCellForGuardPosition() {
        if (checkDirectionForGuardPosition(Directions.HORIZONTAL) != null) {
            return checkDirectionForGuardPosition(Directions.HORIZONTAL);
        }
        if (checkDirectionForGuardPosition(Directions.VERTICAL) != null) {
            return checkDirectionForGuardPosition(Directions.VERTICAL);
        }
        if (checkDirectionForGuardPosition(Directions.DiagonalTLtoBR) != null) {
            return checkDirectionForGuardPosition(Directions.DiagonalTLtoBR);
        }
        if (checkDirectionForGuardPosition(Directions.DiagonalTRtoBL) != null) {
            return checkDirectionForGuardPosition(Directions.DiagonalTRtoBL);
        }
        return null;
    }

    //check for preWin position when there are three "1"  in a row
    public BoardCell checkDirectionForGuardPosition(Directions direction) {
        int countWinCell = 0;
        BoardCell forwardTailCell = null;
        board.playerOneSteps.sort(new ArraySort());
        for (BoardCell playerOneStep : board.playerOneSteps) {
            System.out.println("playerOneStep: " + playerOneStep.X + ", " + playerOneStep.Y);
            for (int i = -4; i <= 4; i++) {
                int newRow = playerOneStep.X + i * direction.getDirectionX();
                int newColumn = playerOneStep.Y + i * direction.getDirectionY();
                BoardCell newCell = new BoardCell(newRow, newColumn);
                if (isValidCell(newCell) && board.gameBoard[newCell.X][newCell.Y] == PlayerType.HumanOne.value) {
                    countWinCell++;

                    if (countWinCell == 5) {

//                        forwardTailCell = (getForwardCell(newCell, direction) != null) ? getForwardCell(newCell, direction)
//                                : ((getTailCell(newCell, direction) != null) ? getTailCell(newCell, direction)
//                                : null);
                    }
                    //make checking condition for 4 cells in a row
                    if (countWinCell == 4) {
                        forwardTailCell = (getForwardCell(newCell, direction) != null) ? getForwardCell(newCell, direction)
                                : ((getTailCell(newCell, direction) != null) ? getTailCell(newCell, direction)
                                : null);
                    }
                    //make checking condition for 5 cells with one empty cell inside
                    if (countWinCell == 3) {
                        forwardTailCell = (getForwardCell(newCell, direction) != null) ? getForwardCell(newCell, direction)
                                : ((getTailCell(newCell, direction) != null) ? getTailCell(newCell, direction)
                                : null);
                    }

                } else {
                    countWinCell = 0;
                }
            }
            return forwardTailCell;
        }
        return forwardTailCell;
    }

    public BoardCell getRandomCell() {
        Random rand = new Random();
        int randX = rand.nextInt(board.xSize);
        int randY = rand.nextInt(board.ySize);
        BoardCell randomCell = new BoardCell(randX, randY);
        return new BoardCell(randomCell.X, randomCell.Y);
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
public Boolean isCellOccupiedOrOutOfBounds(BoardCell cell) {
        return (cell.X >= 0 && cell.X < board.xSize) && (cell.Y >= 0 && cell.Y < board.ySize)
                && !isOutOfBounds(cell)
                && !isCellOccupied(cell);
    }
    private Boolean isCellOccupied(BoardCell cell) {
        return board.gameBoard[cell.X][cell.Y] != 0;
    }
    private Boolean isOutOfBounds(BoardCell cell) {
        try {
            return board.gameBoard[cell.X][cell.Y] != 0;
        } catch(Error e) {
            return true;
        }
    }

}
