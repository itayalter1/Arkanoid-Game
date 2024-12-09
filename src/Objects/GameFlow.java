// 206132284 Itay Alter
package Objects;

import Interfaces.LevelInformation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The "GameFlow" class manages the flow of the game, including running levels,
 * displaying end-of-game messages, and handling user input. It utilizes an
 * "AnimationRunner" and a KeyboardSensor for animation control and user input,
 * respectively. It also includes a "ScoreIndicator" to track and display the
 * player's score during the game. The "runLevels" method takes a list of
 * "LevelInformation" objects as input and iterates over each level. For each
 * level, it creates a GameLevel instance, initializes it, and runs the level
 * until there are no remaining balls or blocks. If there are remaining balls
 * after completing a level, the game is over. Otherwise, it displays an
 * end-of-game message on the screen, including the final score. The game waits
 * for the player to press the space key to close the game.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private ScoreIndicator scoreIndicator;

    /**
     * Constructs a new GameFlow with the specified AnimationRunner and KeyboardSensor.
     *
     * @param ar The AnimationRunner responsible for running the game animations.
     * @param ks The KeyboardSensor used for detecting keyboard input.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.scoreIndicator = new ScoreIndicator(new ScoreTrackingListener(new Counter()));
    }

    /**
     * Runs the game levels sequentially based on the provided list of level
     * information. Each level is initialized and played until there are no
     * remaining balls or blocks. If there are remaining balls after completing
     * all levels, the game ends.
     *
     * @param levels The list of level information defining the game levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.scoreIndicator, this.runner);
            level.initialize();
            // Run the level until there are no remaining balls or blocks
            while (!level.getRemainingBalls() && !level.getRemainingBlocks()) {
                level.run();
            }
            // If there are remaining balls, the game is lost and the GUI is closed
            if (level.getRemainingBalls()) {
                this.runner.getGui().close();
                return;
            }
        }
        // Display the win screen and wait for space key press to close the GUI
        DrawSurface d = this.runner.getGui().getDrawSurface();
        this.runner.run(new KeyPressStoppableAnimation(this.keyboard, keyboard.SPACE_KEY,
                new WinScreen(this.keyboard, this.scoreIndicator)));
        this.runner.getGui().show(d);
        // Wait for space key press to exit the game
        while (true) {
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.runner.getGui().close();
                break;
            }
        }
    }
}
