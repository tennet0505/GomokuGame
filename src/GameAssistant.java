import helpers.ConstantStrings;
import helpers.Enums.GameType;
import helpers.Enums.PlayerType;
import helpers.ErrorMessage;
import models.BoardCell;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GameAssistant {
    static Scanner scanner = new Scanner(System.in);
    Board board;
    Boolean isFirstPlayerTurn = true;
    Boolean isGameFinished = false;
    GameType gameType = GameType.HumanVsHuman;

    public GameAssistant(Board boardMain) {
        board = boardMain;
    }
    public void setGameTitle() {
        System.out.println(ConstantStrings.dividerLine);
        System.out.println(ConstantStrings.startGameTitle);
        System.out.println(ConstantStrings.dividerLine);
    }

    public void setExampleBoard() {
       board.setExampleBoard();
    }

    public void setBoardSize() {
        System.out.println(ConstantStrings.defaultBordSize);
        System.out.println(ConstantStrings.setBordSize);
        String isSetBoard = scanner.nextLine();
        System.out.println();
        if (Objects.equals(isSetBoard, ConstantStrings.yes)) {
            System.out.print(ConstantStrings.enterXsize);
            String xSize = scanner.nextLine();
            System.out.print(ConstantStrings.enterYsize);
            String ySize = scanner.nextLine();
            int newXSize = Integer.parseInt(xSize);
            int newYSize = Integer.parseInt(ySize);
            board = new Board(newXSize, newYSize);
        }
    }

    public void setupNameFor(Player player) {
        String playerNumber = player.playerType== PlayerType.HumanOne ? ConstantStrings.playerOne : ConstantStrings.playerTwo;
        System.out.print(playerNumber + ConstantStrings.enterName);
        String name = scanner.nextLine();
        player.playerName = name;
        System.out.printf(ConstantStrings.niceToMeetYou, name);
        System.out.println();
    }

    public void startGame(Player player) {
        System.out.println();
        System.out.printf(ConstantStrings.letsStart, player.playerName);
    }

    public void checkStepsTurnPlayers(Player player1, Player player2) {
        if (isFirstPlayerTurn) {
            isFirstPlayerTurn = false;
            makeStep(player1);
        } else {
            isFirstPlayerTurn = true;
            makeStep(player2);
        }
        if (gameIsFinished()) {
            checkStepsTurnPlayers(player1, player2);
        }
    }

    //Private functions:
    private void makeStep(Player player) {
        BoardCell cell = new BoardCell(0, 0);
        System.out.println();
        switch (player.playerType) {
            case PlayerType.HumanOne, PlayerType.HumanTwo:
                System.out.print("\uD83E\uDD14 -=[ " + player.playerName +  " ]=-" + ConstantStrings.chooseXCoordinate);
                int x = scanner.nextInt();
                System.out.print(ConstantStrings.chooseYCoordinate);
                int y = scanner.nextInt();
                cell.X = x;
                cell.Y = y;
                break;
            case PlayerType.AI:
                Random rand = new Random();
                int randX = rand.nextInt(board.xSize);
                int randY = rand.nextInt(board.ySize);
                BoardCell randomCell = new BoardCell(randX, randY);
                System.out.print("/_-=(*_~)=-_/ I choose: " + "X: " + randomCell.X + ", " + "Y: " + randomCell.Y);
                cell.X = randomCell.X;
                cell.Y = randomCell.Y;
                break;
        }
        if (isValidLocation(cell)) {
            board.setupStepOnBoard(player, cell);
            isGameFinished = board.checkIfIsWinStepFor(player, cell);
        } else {
            System.out.print(ErrorMessage.errorWrongLocation);
            makeStep(player);
        }
    }

    private Boolean gameIsFinished() {
        if (!isGameFinished) {
            return true;
        } else {
            System.out.println(ConstantStrings.gameOver);
            return false;
        }
    }

    private Boolean isValidLocation(BoardCell cell) {
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
