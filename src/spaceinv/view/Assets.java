package spaceinv.view;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import spaceinv.model.Gun;
import spaceinv.model.projectiles.Bomb;
import spaceinv.model.projectiles.Rocket;
import spaceinv.model.ships.BattleCruiser;
import spaceinv.model.ships.Bomber;
import spaceinv.model.ships.Frigate;
import spaceinv.model.statics.Ground;

import java.util.HashMap;
import java.util.Map;


/*
   Defining all assets used by application
   Assets stored in directory assets.

   *** Nothing to do here ***

*/

public enum Assets {
    INSTANCE;           // Only one object

    private final String IMAGE_DIR = "file:assets/img/";
    private final String SOUND_DIR = "file:assets/sound/";

    private final Map<Object, Image> objectImage = new HashMap<>();

    // ------------ Handling Visual ------------------------

    public final Color colorFgText = Color.WHITE;
    public final int fontSize = 18;

    public final Image background = getImage("spaceBackground.png");
    public final Image splash = getImage("splash.jpg");
    public final Image explosion = getImage("explosion.png");

    // All classes bound here (objects must be bound else where, not done in this application)
    {
        bind(BattleCruiser.class, getImage("battlecruiser.png"));
        bind(Bomber.class, getImage("bomber.png"));
        bind(Frigate.class, getImage("frigate.png"));
        bind(Bomb.class, getImage("bomb.png"));
        bind(Rocket.class, getImage("rocket.png"));
        bind(Gun.class, getImage("cannon.png"));
        bind(Ground.class, getImage("ground.png"));
    }

    // -------------- Audio handling -----------------------------

    public final AudioClip rocketHitShip = getSound("explosion1.wav");
    // TODO, some problems with playing different file formats, wav bad .. too big files
    // public final AudioClip music = getSound("music.wav");

    // -------------- Connect classes or objects to images -----------------

    // This will bind an object to an image to be used in GUI
    public <T> void bind(T object, Image image) {
        objectImage.put(object, image);
    }

    // This will bind a class to an image to be used in GUI
    public void bind(Class<?> clazz, Image image) {
        objectImage.put(clazz, image);
    }

    // Get a previously bound image for an object
    public <T> Image get(T a) {
        return objectImage.get(a);
    }

    // Get a previously bound image for some class
    public Image get(Class<?> clazz) {
        return objectImage.get(clazz);
    }

    // ---------- Helpers for file handling ------------------------

    private Image getImage(String fileName) {
        return new Image(IMAGE_DIR + fileName);
    }

    public AudioClip getSound(String fileName) {
        return new AudioClip(SOUND_DIR + fileName);
    }
}
