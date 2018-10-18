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

    private String IMAGE_DIR = "file:assets/img/Normal/";
    private final String SOUND_DIR = "file:assets/sound/";

    private Map<Object, Image> objectImage = new HashMap<>();



    public void smurfMode() {
        IMAGE_DIR = "file:assets/img/Smurfs/";
        bindSprites();
    }

    public void normalMode() {
        IMAGE_DIR = "file:assets/img/Normal/";
        bindSprites();
    }
    public void bindSprites() {

        Map<Object, Image> newObjectImage = new HashMap<>();

        bind(BattleCruiser.class, getImage("battlecruiser.png"), newObjectImage);
        bind(Bomber.class, getImage("bomber.png"), newObjectImage);
        bind(Frigate.class, getImage("frigate.png"), newObjectImage);
        bind(Bomb.class, getImage("bomb.png"), newObjectImage);
        bind(Rocket.class, getImage("rocket.png"), newObjectImage);
        bind(Gun.class, getImage("cannon.png"), newObjectImage);
        bind(Ground.class, getImage("ground.png"), newObjectImage);

        this.objectImage = newObjectImage;
        background = getImage("Background.png");
        splash = getImage("splash.jpg");
        explosion = getImage("explosion.png");

    }

    // ------------ Handling Visual ------------------------

    public final Color colorFgText = Color.WHITE;
    public final int fontSize = 18;

    public Image background ;
    public Image splash;
    public Image explosion;

    // All classes bound here (objects must be bound else where, not done in this application)

    // -------------- Audio handling -----------------------------

    public final AudioClip rocketHitShip[] = {getSound("explosion1.wav"), getSound("explosion2.wav")};
    // TODO, some problems with playing different file formats, wav bad .. too big files
    public final AudioClip backgroundMusic = getSound("spacemusic.mp3");
    public final AudioClip rocketLaunched = getSound("rocket.wav");

    // -------------- Connect classes or objects to images -----------------

    // This will bind an object to an image to be used in GUI
    public <T> void bind(T object, Image image) {
        objectImage.put(object, image);
    }

    // This will bind a class to an image to be used in GUI
    public void bind(Class<?> clazz, Image image, Map<Object, Image> objectImage) {
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
