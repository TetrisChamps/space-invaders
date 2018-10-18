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
import spaceinv.model.AbstractMovable;
import spaceinv.model.IPositionable;
import spaceinv.model.SpaceInv;
import spaceinv.model.levels.Level0;
import spaceinv.model.projectiles.Bomb;
import spaceinv.model.ships.AbstractSpaceShip;

import java.applet.AudioClip;

import static spaceinv.model.AbstractMovable.Direction;
import static spaceinv.model.SpaceInv.*;


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
                EventService.add(new Event(Event.Type.ROCKET_LAUNCHED));
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
        switch (evt.type) {
            case BOMB_HIT_GUN:
                EventService.add(new Event(Event.Type.GAME_OVER));
                break;
            case BOMB_HIT_GROUND:
                spaceInv.removeBomb((Bomb) evt.data);
                explostionAtMovable((AbstractMovable) evt.data);
                break;
            case ROCKET_HIT_SHIP:
                spaceInv.shipHit((AbstractSpaceShip) evt.data);
                explostionAtMovable((AbstractMovable) evt.data);
                break;
            case SHIP_HIT_GROUND:
                EventService.add(new Event(Event.Type.GAME_OVER));
                break;
            case ROCKET_LAUNCHED:
                spaceInv.fireGun();
                break;
            case ROCKET_OUT_OF_BOUNDS:
                spaceInv.removeRocket();
                break;
            case GAME_OVER:
                renderEndScreen();
                break;
            case DEBUG:
                break;
            case BOMB_DROPPED:
                spaceInv.dropBomb((Bomb) evt.data);
                break;
            case EXCEPTION:
                break;
        }
    }

    private void explostionAtMovable(AbstractMovable movable){
        renderExplosion(movable.getX(), movable.getY());
        Assets.INSTANCE.rocketHitShip.play();
    }

    // ************* Rendering and JavaFX below (nothing to do)  *************
    private long lastRender = 0;

    private void render(long now) {
        renderGame(now);
        lastRender = now;
    }

    private void renderGame(long now){
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        for (IPositionable d : spaceInv.getPositionables()) {
            Image i = Assets.INSTANCE.get(d.getClass());
            fg.drawImage(i, d.getX(), d.getY(), d.getWidth(), d.getHeight());
        }
        fg.setFill(Assets.INSTANCE.colorFgText);
        fg.setFont(Font.font(Assets.INSTANCE.fontSize));
        fg.fillText(String.valueOf(spaceInv.getPoints()), 50, 50);
        if (showOSD) {
            fg.fillText("FPS: " + (ONE_SEC / (now - lastRender)), 700, 50);
            fg.fillText("Version: " + "1.0", 700, 75);
        }
    }

    private void renderEndScreen(){
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        Image i = Assets.INSTANCE.splash;
        fg.drawImage(i, 0, 0, GAME_WIDTH, GAME_HEIGHT);
        fg.setFill(Assets.INSTANCE.colorFgText);
        fg.setFont(Font.font(40));
        fg.fillText(String.valueOf("Points: " + spaceInv.getPoints()), 320, 380);
        stopGame();
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
