package spaceinv.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.IPositionable;
import spaceinv.model.SpaceInv;
import spaceinv.model.levels.Level0;

import static spaceinv.model.AbstractMovable.Direction;
import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;
import static spaceinv.model.SpaceInv.ONE_SEC;


/*
 * The GUI for the SpaceInv game.
 * See: https://www.youtube.com/watch?v=axlx3o0codc
 *
 * No application logic here just GUI and event handling
 * and input to model
 *
 * Run this to run the game
 *
 */
public class SpaceInvGUI extends Application {


    private SpaceInv spaceInv;          // Reference to the OO model
    private boolean running = false;    // Is game running?
    private boolean showOSD = false;

    // ------- Keyboard handling ----------------------------------

    private void keyPressed(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case LEFT:
                spaceInv.setGunMovingDirection(Direction.LEFT);
                break;
            case RIGHT:
                spaceInv.setGunMovingDirection(Direction.RIGHT);
                break;
            case SPACE:
                spaceInv.fireGun();
                break;
            case F12:
                showOSD = !showOSD;
                break;
            default:  // Nothing
        }
    }

    private void keyReleased(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case LEFT:
            case RIGHT:
                spaceInv.setGunMovingDirection(Direction.NONE);
                break;
            default: // Nothing
        }
    }

    // ---- Menu handling -----------------

    private void handleMenu(ActionEvent e) {
        MenuItem mi = (MenuItem) e.getSource();
        switch (mi.getText()) {
            case "New":
                newGame();
                break;
            case "Stop":
                stopGame();
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Play":
                toggleMusic();
                break;
            default: // Nothing
        }
    }

    // ---------- Menu actions ---------------------

    private void newGame() {

        spaceInv = new SpaceInv(new Level0()); // TODO Create the OO model by using a Level parameter

        renderBackground();
        timer.start();

        running = true;
    }

    private void stopGame() {
        running = false;
        timer.stop();
    }

    private void toggleMusic() {
        // TODO
    }

    // --- Handling events coming form the model -----

    private void handleModelEvent(Event evt) {
        if (evt.type == Event.Type.ROCKET_HIT_SHIP) {
            // TODO
        } else if (evt.type == Event.Type.ROCKET_LAUNCHED) {
            // TODO
        } else if (evt.type == Event.Type.BOMB_HIT_GROUND) {
            // TODO
        } else if (evt.type == Event.Type.BOMB_HIT_GUN) {
            // TODO
        } else if (evt.type == Event.Type.BOMB_DROPPED) {

        } else if (evt.type == Event.Type.ROCKET_OUT_OF_BOUNDS){
            spaceInv.removeRocket();
        }
    }

    // ************* Rendering and JavaFX below (nothing to do)  *************
    private long lastRender = 0;
    private void render(long now) {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        for (IPositionable d : spaceInv.getPositionables()) {
            Image i = Assets.INSTANCE.get(d.getClass());
            fg.drawImage(i, d.getX(), d.getY(), d.getWidth(), d.getHeight());
        }
        fg.setFill(Assets.INSTANCE.colorFgText);
        fg.setFont(Font.font(Assets.INSTANCE.fontSize));
        fg.fillText(String.valueOf(spaceInv.getPoints()), 50, 50);
        if(showOSD){
            fg.fillText("FPS: " + (ONE_SEC / (now - lastRender)), 700, 50);
            fg.fillText("Version: " + "1.0", 700, 75);
        }
        lastRender = now;
    }

    private void renderExplosion(double x, double y) {
        new Explosion().start(x, y, fg);
    }

    private void renderBackground() {
        bg.drawImage(Assets.INSTANCE.background, 0, 0, GAME_WIDTH, GAME_HEIGHT);
    }

    private AnimationTimer timer;
    private GraphicsContext fg;   // Foreground
    private GraphicsContext bg;   // Background
    private SpaceInvMenu menuBar;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        // Menu
        menuBar = new SpaceInvMenu(this::handleMenu);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        // Drawing areas
        Canvas backGround = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        bg = backGround.getGraphicsContext2D();
        Canvas foreGround = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        fg = foreGround.getGraphicsContext2D();

        Pane pane = new Pane(backGround, foreGround);
        root.setCenter(pane);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                spaceInv.update(now);
                render(now);
                Event e = EventService.remove();
                if (e != null) {
                    SpaceInvGUI.this.handleModelEvent(e);
                }

            }
        };

        bg.drawImage(Assets.INSTANCE.splash, 0, 0, GAME_WIDTH, GAME_HEIGHT);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Space Invaders");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
