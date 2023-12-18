import helpers.PlayerNumber;

import java.util.Scanner;

public class GameAsisstant {
    static Scanner scanner = new Scanner(System.in);

    public static void startGame() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                     Start game!");
        System.out.println("-------------------------------------------------------");
    }
    public static void gameRule() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                     Game rule description");
        System.out.println("-------------------------------------------------------");
    }

    public static void setupPlayerName(Player player) {
        System.out.print("Player enter your name: ");
        String name = scanner.nextLine();
        player.playerName = name;
        System.out.printf("Nice to meet you, %s!", name);
        firsStep(player);
    }

    public static void firsStep(Player player) {
        System.out.println();
        System.out.printf("Let's start, %s. You are the first!", player.playerName);
    }

    public static void step(Player player, Field field, int x, int y) {
        field.setupStepInMatrix(player, x, y);
    }
}
