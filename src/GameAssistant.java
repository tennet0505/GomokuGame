import java.util.ArrayList;
import java.util.Scanner;

public class GameAssistant {
    static Scanner scanner = new Scanner(System.in);
    Field field;

    public GameAssistant(Field fieldMain) {
        field = fieldMain;
    }
    public void startGame() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                     Start game!");
        System.out.println("-------------------------------------------------------");
    }
    public void gameRule() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                     Game rule description");
        System.out.println("-------------------------------------------------------");
    }

    public void setupPlayerName(Player player) {
        System.out.print("Player enter your name: ");
        String name = scanner.nextLine();
        player.playerName = name;
        System.out.printf("Nice to meet you, %s!", name);
        firsStep(player);
    }

    private void firsStep(Player player) {
        System.out.println();
        System.out.printf("Let's start, %s. You are the first!", player.playerName);
    }

    public void step(Player player, int x, int y) {
        field.setupStepInMatrix(player, x, y);
    }
    public ArrayList<String> fetchSteps(Player player) {
        return field.fetchSteps(player);
    }
}
