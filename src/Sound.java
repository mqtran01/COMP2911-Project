import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private final File background = new File("assets/MusicBackground.wav");
    private final File footsteps = new File("assets/MusicFootsteps.wav");
    private final File moveBox = new File("assets/MusicMoveBox.wav");
    private final File winGame = new File("assets/MusicWinGame.wav");

    public Sound(String soundName) {
        play(soundName);
    }

    private void play(String soundName) {
        File sound = getFileFromName(soundName);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);

        } catch (Exception e) {

        }
    }

    private File getFileFromName(String soundName) {
        switch(soundName) {
            case "background":
                return this.background;
            case "footsteps":
                return this.footsteps;
            case "moveBox":
                return this.moveBox;
            case "winGame":
                return this.winGame;
        }
        return null;
    }
}