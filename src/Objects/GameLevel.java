// 206132284 Itay Alter
package Objects;

import java.awt.Color;
import java.util.ArrayList;

import Interfaces.Animation;
import Interfaces.Collidable;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The "GameLevel" class represents an arbitrary level in the game and implements
 * the "Animation" interface. It manages the sprites and environment of the level,
 * including the background, balls, blocks, and game elements such as the paddle
 * and score indicator. The class is responsible for initializing the level, running
 * the animation loop, and determining when the level should stop. It also handles
 * user input, such as pausing the game. Additionally, it includes constants for
 * screen dimensions, fonts, and waiting time. The class provides methods to add
 * and remove collidables and sprites, as well as checking the remaining balls
 * and blocks in the level.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Block background;
    private ArrayList<Ball> balls;
    private BlockRemover blockRemover = new BlockRemover(this);
    private BallRemover ballRemover = new BallRemover(this);
    private Block ballKiller;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private LevelInformation level;
    private boolean running;
    static final double X_ORIGIN = 0;
    static final int NUMBER_OF_FRAMES = 60;
    static final int LEFT_BOUND_WIDTH = 20;
    static final int BOTTOM_BOUND_WIDTH = 600;
    static final int BOTTOM_SCREEN = 800;
    static final int END_OF_LEVEL_POINTS = 100;
    static final int END_OF_LEVEL_MESSAGE_PART1_FONT = 35;
    static final int END_OF_LEVEL_MESSAGE_PART2_FONT = 45;
    static final int START_OF_TEXT_X = 250;
    static final int START_OF_TEXT_Y = 350;
    static final int WAITING_TIME = 3;
    static final int COUNT_FROM = 3;
    static final int NUM_OF_SECONDS = 2;
    static final String PAUSE = "p";

    /**
     * Creates a new instance of the GameLevel class.
     *
     * @param level  the level information for the game level
     * @param score  the score indicator for the game level
     * @param runner the animation runner for the game level
     */
    public GameLevel(LevelInformation level, ScoreIndicator score, AnimationRunner runner) {
        this.runner = runner;
        this.level = level;
        this.ballRemover.setRemainingBalls(new Counter(level.balls().size()));
        this.blockRemover.setRemainingBlocks(new Counter(level.blocks().size()));
        this.scoreIndicator = score;
        this.scoreIndicator.setLevelName(level.levelName());
        this.balls = level.balls();
    }

    /**
     * Sets the background color of the level. Creates a new block that covers
     * the entire screen and sets its color to the given color.
     */
    public void setBackground() {
        this.background = (Block) this.level.getBackground();
    }

    /**
     * Sets the game's balls list to a given ArrayList of balls.
     *
     * @param balls the ArrayList of balls to set as the game's balls list.
     */
    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    /**
     * Returns the game environment of the game.
     *
     * @return the game environment of the game.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a sprite to the list of sprites in the game.
     *
     * @param s the sprite to be added to the game
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }


    /**
     * The method initializes the game by performing several steps:
     * 1. Adds the score listener to the score indicator frame.
     * 2. Adds the score indicator frame to the list of sprites.
     * 3. Creates a new paddle and adds it to the game.
     * 4. Generates a random balls and adds it to the list of sprites.
     * 5. Generates random blocks and adds them to the lists of sprites and collidables.
     * 6. Adds the block remover and score listeners to each block.
     * 7. Creates a ball killer block and adds it to the game.
     * 8. Adds frame blocks to the lists of sprites and collidables.
     */
    public void initialize() {
        this.setBackground();
        this.scoreIndicator.getFrame().addHitListener(this.scoreIndicator.getScore());
        this.addSprite(this.scoreIndicator.getFrame());
        Paddle paddle = new Paddle(this.runner.getGui(), this.level.paddleWidth(),
                this.level.paddleSpeed(), this.level.paddleUpperLeft());
        paddle.addToGame(this);
        this.balls = this.level.balls();
        for (int i = 0; i < this.balls.size(); i++) {
            this.balls.get(i).setVelocity(this.level.initialBallVelocities().get(i));
        }
        for (Ball ball : this.balls) {
            this.addSprite(ball);
            ball.setGameEnvironment(this.environment);
        }
        // Generates random blocks
        ArrayList<Block> blocks = this.level.blocks();
        // Adds the generated blocks to the lists of sprites and collidables
        for (Block block : blocks) {
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreIndicator.getScore());
        }
        this.ballKiller = new Block(new Point(X_ORIGIN, BOTTOM_BOUND_WIDTH),
                BOTTOM_SCREEN, LEFT_BOUND_WIDTH, Color.cyan);
        this.ballKiller.addHitListener(ballRemover);
        this.sprites.addSprite(this.ballKiller);
        this.environment.addCollidable(this.ballKiller);
        //Adds frame blocks to the lists of sprites and collidables
        blocks.addAll(Block.frameBlocks());
        for (Block block : blocks) {
            this.addSprite(block);
            this.addCollidable(block);
        }
    }

    /**
     * Performs one frame of the game animation. Draws the game's background,
     * sprites, and score indicator on the given draw surface. Notifies all
     * sprites that the time has passed. Checks if the 'p' key is pressed to
     * pause the game.
     *
     * @param d The draw surface on which to draw the game elements.
     */
    public void doOneFrame(DrawSurface d) {
        // Draw the game's background, sprites, and score indicator
        this.level.drawBackground(d);
        this.sprites.drawAllOn(d);
        this.scoreIndicator.drawOn(d);
        //Notify all sprites that the time has passed
        try {
            this.sprites.notifyAllTimePassed();
        } catch (Exception e) {
        }
        // Check if the 'p' key is pressed to pause the game
        if (this.runner.getGui().getKeyboardSensor().isPressed(PAUSE)) {
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(),
                    this.runner.getGui().getKeyboardSensor().SPACE_KEY,
                    new PauseScreen(this.runner.getGui().getKeyboardSensor())));
        }
    }

    /**
     * Checks if the animation should stop. Determines whether the animation
     * should continue running or stop based on the game conditions. If all
     * blocks have been removed, displays the end-of-level message and waits for
     * a certain time. If all balls have been removed, displays the loss screen
     * and waits for the space key to be pressed.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        int framesPerSecond = NUMBER_OF_FRAMES;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        DrawSurface d = this.runner.getGui().getDrawSurface();
        long startTime = System.currentTimeMillis();
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        // Check if all blocks have been removed from the game
        if (this.blockRemover.getRemainingBlocks().getValue() == 0) {
            this.scoreIndicator.getScore().getCurrentScore().increase(END_OF_LEVEL_POINTS);
            this.background.drawOn(d);
            this.sprites.drawAllOn(d);
            this.scoreIndicator.drawOn(d);
            // Display end-of-level message
            ScreenMessage message = new ScreenMessage("  Well done! You passed"
                    + " the stage!");
            message.drawOn(d, END_OF_LEVEL_MESSAGE_PART1_FONT);
            d.drawText(START_OF_TEXT_X, START_OF_TEXT_Y, " Your score is "
                            + this.scoreIndicator.getScore().getCurrentScore().getValue() + "!",
                    END_OF_LEVEL_MESSAGE_PART2_FONT);
            this.runner.getGui().show(d);
            this.runner.getSleeper().sleepFor((int) Math.pow(milliSecondLeftToSleep, WAITING_TIME));
            return !this.running;
        }
        // Check if all balls have been removed from the game
        if (this.ballRemover.getRemainingBalls().getValue() == 0) {
            this.background.drawOn(d);
            this.sprites.drawAllOn(d);
            this.scoreIndicator.drawOn(d);
            // Display lose screen
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(),
                    this.runner.getGui().getKeyboardSensor().SPACE_KEY,
                    new LoseScreen(this.runner.getGui().getKeyboardSensor(), this.scoreIndicator)));
            this.runner.getGui().show(d);
            // Wait for space key to be pressed
            while (true) {
                if (this.runner.getGui().getKeyboardSensor().isPressed(KeyboardSensor.SPACE_KEY)) {
                    break;
                }
            }
            return !this.running;
        }
        return this.running;
    }

    /**
     * Runs the game. Starts the game loop and continuously runs the animation
     * until the game should stop. Each iteration of the loop calls the
     * 'doOneFrame' method to update the game state and redraw the screen.
     * The loop also manages the frame rate by calculating the time taken for
     * each frame and sleeps if necessary.
     */
    public void run() {
        this.running = true;
        int framesPerSecond = NUMBER_OF_FRAMES;
        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, this.sprites));
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // Game loop
        while (this.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.runner.getGui().getDrawSurface();
            this.doOneFrame(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.runner.getSleeper().sleepFor(milliSecondLeftToSleep);
            }
            this.runner.getGui().show(d);
        }
    }

    /**
     * Removes a collidable object from the game's environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().getCollidables().remove(c);
    }

    /**
     * Removes a sprite object from the game's sprites list.
     *
     * @param s the sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Checks if there are any remaining balls in the game.
     *
     * @return true if there are no remaining balls, false otherwise
     */
    public boolean getRemainingBalls() {
        if (this.ballRemover.getRemainingBalls().getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if there are any remaining block in the games.
     *
     * @return true if there are no remaining blocks, false otherwise
     */
    public boolean getRemainingBlocks() {
        if (this.blockRemover.getRemainingBlocks().getValue() == 0) {
            return true;
        }
        return false;
    }
}
