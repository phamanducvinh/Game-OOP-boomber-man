package GameController;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;

import static Constants.Constants.*;

public class SoundController extends Application {
    public static AudioClip backgroundSound = SoundController.playSound(SOUND_PATH[0]);
    public static AudioClip effectSound;
    @Override
    public void start(Stage stage) throws Exception {
    }

    public static void update() {
        Bomberman.sound = !Bomberman.sound;
        if(!Bomberman.sound) {
            backgroundSound.stop();
        } else {
            backgroundSound.play();
        }
    }

    public static void playEffectSound(String path) {
        if(Bomberman.sound) {
            effectSound = SoundController.playSound(path);
            effectSound.play();
        }
    }

    public static void playBackGroundSound(String path) {
        if(Bomberman.sound) {
            SoundController.backgroundSound.stop();
            SoundController.backgroundSound = SoundController.playSound(path);
            SoundController.backgroundSound.setCycleCount(1000);
            SoundController.backgroundSound.play();
        }
    }
    public static void loop() {
        if(!backgroundSound.isPlaying() && Bomberman.sound ) backgroundSound.play();
    }

    public static AudioClip playSound(String path) {
        File file = new File(path);
        Media media = new Media(file.toURI().toString());
        AudioClip audioClip = new AudioClip(media.getSource());
        return audioClip;
    }
}
