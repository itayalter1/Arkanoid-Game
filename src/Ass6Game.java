// 206132284 Itay Alter

import Interfaces.LevelInformation;
import Objects.GameFlow;
import Objects.AnimationRunner;
import Objects.DirectHitLevel;
import Objects.WideEasyLevel;
import Objects.StarryNight;
import biuoop.GUI;

import java.util.ArrayList;

/**
 * The "Ass6Game" class represents the main entry point for running the Arkanoid
 * game. It creates a GUI window, initializes a list of game levels based on
 * command-line arguments or default levels, creates an "AnimationRunner" and a
 * "GameFlow", and then runs the levels using the "GameFlow" instance.
 */
public class Ass6Game {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;

    /**
     * The main entry point for running the Arkanoid game. This method initializes
     * the game by creating a GUI window with the specified title and size. It
     * then creates a list to hold the levels of the game. If no command-line
     * arguments are provided, it adds the default levels (DirectHitLevel,
     * WideEasyLevel, and Green3Level) to the list. If command-line arguments are
     * provided, it processes them and adds the corresponding levels to the list.
     * It then creates an AnimationRunner and a GameFlow object to handle the game's
     * animation and flow. Finally, it runs the levels in the game flow, starting
     * the game.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Create a GUI window with the specified title and size
        GUI gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        // Create a list to hold the levels of the game
        ArrayList<LevelInformation> levels = new ArrayList<>();
        // If no command-line arguments provided, add default levels
        if (args.length == 0) {
            levels.add(new DirectHitLevel());
            levels.add(new WideEasyLevel());
            levels.add(new StarryNight());
        } else {
            boolean flag = false;
            // Process command-line arguments and add corresponding levels
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    levels.add(new DirectHitLevel());
                    flag = true;
                } else if (args[i].equals("2")) {
                    levels.add(new WideEasyLevel());
                    flag = true;
                } else if (args[i].equals("3")) {
                    levels.add(new StarryNight());
                    flag = true;
                }
            }
            if (!flag) {
                levels.add(new DirectHitLevel());
                levels.add(new WideEasyLevel());
                levels.add(new StarryNight());
            }
        }
        AnimationRunner run = new AnimationRunner(gui);
        GameFlow newGameLevel = new GameFlow(run, gui.getKeyboardSensor());
        // Run the levels in the game flow
        newGameLevel.runLevels(levels);
    }
}
