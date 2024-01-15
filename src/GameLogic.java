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
        if (getCellCheckDirectionForGuardPosition(Directions.HORIZONTAL) != null) {
            return getCellCheckDirectionForGuardPosition(Directions.HORIZONTAL);
        }
        if (getCellCheckDirectionForGuardPosition(Directions.VERTICAL) != null) {
            return getCellCheckDirectionForGuardPosition(Directions.VERTICAL);
        }
        if (getCellCheckDirectionForGuardPosition(Directions.DiagonalTLtoBR) != null) {
            return getCellCheckDirectionForGuardPosition(Directions.DiagonalTLtoBR);
        }
        if (getCellCheckDirectionForGuardPosition(Directions.DiagonalTRtoBL) != null) {
            return getCellCheckDirectionForGuardPosition(Directions.DiagonalTRtoBL);
        }
        return null;
    }

    public BoardCell getCellCheckDirectionForGuardPosition(Directions direction) {
        int countCellsInARow = 0;
        int countCellBetweenOnes = 0;
        BoardCell nextMoveCell = null;
        BoardCell centerCell = null;
        BoardCell newCell = new BoardCell(-4, -4);
        board.playerOneSteps.sort(new ArraySort());
        for (BoardCell playerOneStep : board.playerOneSteps) {
            for (int i = 0; i <= 4; i++) {
                newCell.X = playerOneStep.X + i * direction.getDirectionX();
                newCell.Y = playerOneStep.Y + i * direction.getDirectionY();
                if (!isValidCell(newCell)) {
                    break;
                }
                    switch (board.getValueForCell(newCell)) {
                        case 1:
                            countCellsInARow++;
                            break;
                        case 0:
                            countCellBetweenOnes ++;
                            centerCell = new BoardCell(newCell.X, newCell.Y);
                        default: break;
                    }
                    if (countCellsInARow == 2) {
                        if (isSecondAndThirdCheckedCellIsEmpty(newCell, direction)){
                            break;
                        }
                    }
                    if (countCellsInARow == 3 && countCellBetweenOnes == 1) {
                        return centerCell;
                    }
                    if (countCellsInARow == 3) {
                        nextMoveCell = getCellCheckedHeadAndTail(newCell, direction);
                        return nextMoveCell;
                    }
            }
            countCellBetweenOnes = 0;
            countCellsInARow = 0;
        }
        return nextMoveCell;
    }

    public BoardCell getRandomCell() {
        Random rand = new Random();
        int randX = rand.nextInt(board.xSize);
        int randY = rand.nextInt(board.ySize);
        BoardCell randomCell = new BoardCell(randX, randY);
        return new BoardCell(randomCell.X, randomCell.Y);
    }

    private boolean isSecondAndThirdCheckedCellIsEmpty(BoardCell cell, Directions direction) {
        int secondRow = cell.X + direction.getDirectionX();
        int secondColumn = cell.Y + direction.getDirectionY();
        BoardCell secondCheckedCell = new BoardCell(secondRow, secondColumn);
        int thirdRow = cell.X + direction.getDirectionX() * 2;
        int thirdColumn = cell.Y + direction.getDirectionY() * 2;
        BoardCell thirdCheckedCell = new BoardCell(thirdRow, thirdColumn);
        return isValidCell(secondCheckedCell) && board.getValueForCell(secondCheckedCell) == 0
                && isValidCell(thirdCheckedCell) && board.getValueForCell(thirdCheckedCell) == 0;
    }
    private BoardCell getCellCheckedHeadAndTail(BoardCell cell, Directions direction) {
        if (headCellIsEmpty(cell, direction) && (tailCellIsEmpty(cell, direction))) {
            int tailRow = cell.X + direction.getDirectionX();
            int tailColumn = cell.Y + direction.getDirectionY();
            return new BoardCell(tailRow, tailColumn);
        }
        return null;
    }
    private boolean tailCellIsEmpty(BoardCell cell, Directions direction) {
        int tailRow = cell.X + direction.getDirectionX();
        int tailColumn = cell.Y + direction.getDirectionY();
        BoardCell newLastCell = new BoardCell(tailRow, tailColumn);
        return isValidCell(newLastCell) && board.getValueForCell(newLastCell) == 0;
    }
    private boolean headCellIsEmpty(BoardCell cell, Directions direction) {
        int headRow = cell.X - 3 * direction.getDirectionX();
        int headColumn = cell.Y - 3 * direction.getDirectionY();
        BoardCell newLastCell = new BoardCell(headRow, headColumn);
        return isValidCell(newLastCell) && board.getValueForCell(newLastCell) == 0;
    }

    private BoardCell getCenterCell(BoardCell cell, Directions direction) {
        BoardCell newCell = new BoardCell(cell.X, cell.Y);
        if (board.getValueForCell(newCell) == 0) {
            return newCell;
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
