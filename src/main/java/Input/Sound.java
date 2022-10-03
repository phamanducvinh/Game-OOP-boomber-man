package Input;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;

public class Sound extends Application {

    private static AudioClip backgroundSound = Sound.playSound("Area1");
    private static AudioClip stageCleared = Sound.playSound("StageCleared");
    public static AudioClip playSound(String action) {
        String path = "/sound/" + action + ".wav";
        Media media = new Media(new File(path).toURI().toString());
        AudioClip audioClip = new AudioClip(media.getSource());
        audioClip.play();
        return audioClip;
    }
    @Override
    public void start(Stage stage) throws Exception {
    }

    public static AudioClip getBackgroundSound() {
        return backgroundSound;
    }

    public static AudioClip getStageCleared() {
        return stageCleared;
    }

    public static void setBackgroundSound(AudioClip backgroundSound) {
        Sound.backgroundSound = backgroundSound;
    }

    public static void setStageCleared(AudioClip stageCleared) {
        Sound.stageCleared = stageCleared;
    }
}
