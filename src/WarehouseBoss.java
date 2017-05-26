import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main class to operate the Warehouse Boss game.
 * 
 * @author Group 1 Tutorial H14A
 *
 */
public class WarehouseBoss extends JFrame {
    private JPanel mainPanel;
    private CardLayout views;
    private Models models;
    private static SettingsModel settings;
    public static Clip clip;

    /**
     * Constructor of the overall game container. Also controls the background
     * music of the game.
     */
    public WarehouseBoss() {
        // Create and set up the window.
        super("Warehouse Boss");
        this.mainPanel = new JPanel();
        this.views = new CardLayout();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set preferred dimensions
        this.setPreferredSize(new Dimension(800, 600));

        // To prevent window resizing
        this.setResizable(false);

        try {
            settings = SaveLoad.loadSettings();
            if (settings == null) {
                settings = new SettingsModel();// if it failed, just create a new one
            }
        } catch (IOException e) {
            settings = new SettingsModel();// if it failed, just create a new one
        }

        this.models = new Models(settings);

        // Set mainPanel layout to CardLayout
        mainPanel.setLayout(views);

        // Create each view objects
        StoryLevelSelector storyLvlPanel = new StoryLevelSelector(this, models);
        RandomLevelSelector randLvlPanel = new RandomLevelSelector(this, models);
        SettingsPanel settingsPanel = new SettingsPanel(this, models);
        MenuPanel menuPanel = new MenuPanel(this, models);

        // Add panels to mainPanel, utilising CardLayout
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(storyLvlPanel, "Story");
        mainPanel.add(randLvlPanel, "Random");
        mainPanel.add(settingsPanel, "Settings");

        // Display menuPanel
        views.show(mainPanel, "Menu");

        // Add mainPanel to frame
        this.add(mainPanel);

        // Play BGM
        playSound("assets/" + settings.getSpriteSet() + "/MusicBackground.wav", settings);

        // Display the window.
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Changes current panel currently displayed
     * 
     * @param hash the key of the panel to switch to
     */
    public void swapPanel(String hash) {
        views.show(mainPanel, hash);
    }

    /**
     * Add a panel to the mainPanel - the library of views
     * 
     * @param panel to be added
     * @param hash the key to access the panel
     */
    public void addPanel(JPanel panel, String hash) {
        mainPanel.add(panel, hash);
    }

    /**
     * Remove a panel from the mainPanel
     * 
     * @param panel to be removed
     */
    public void removePanel(JPanel panel) {
        mainPanel.remove(panel);
    }

    /**
     * Plays the sound of the specific sound file.
     *
     * @param filename as the file path of the sound file
     * @param settings as the settings controller
     */
    static public void playSound(String filename, SettingsModel settings) {
        Thread musicThread = new Thread() {
            @Override
            public void run() {
                try {
                    // Open an audio input stream.
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
                    // Get a sound clip resource.
                    clip = AudioSystem.getClip();
                    // Open audio clip and load samples from the audio input
                    // stream.
                    clip.open(audioIn);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        if (settings.isEnableMusic()) {
            System.out.println("Music enabled");
            musicThread.start();
            loopSound(settings);
        }
    }

    /**
     * Changes the background music based on skin
     * 
     * @param skin as the skin set
     */
    static public void changeSound(String skin) {
        String path = "assets/" + skin + "MusicBackground.wav";
        System.out.println("changeSound!" + path);
        if (settings.isEnableMusic()) {
            try {
                // Open an audio input stream.
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(path));
                // Get a sound clip resource.
                clip = AudioSystem.getClip();
                // Open audio clip and load samples from the audio input stream.
                clip.open(audioIn);
                clip.start();
                loopSound(settings);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Creates a delay to loop the sound file after it is complete

     * @param settings as the settings controller
     */
    static private void loopSound(SettingsModel settings) {
        String skin = settings.getSpriteSet();
        int delay;
        if (skin.equals("Star Warehouse/")) {
            delay = 165 * 1000;
        } else {
            delay = 110 * 1000;
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // if music is enabled and skin has not been changed
                if (settings.isEnableMusic() && (skin.equals(settings.getSpriteSet()))) {
                    clip.loop(1);
                    System.out.println("loop");
                    loopSound(settings);
                }
            }
        }, delay);
    }

    /**
     * Main entry point for the Warehouse Boss game.
     * 
     * @param args is irrelevant
     */
    public static void main(String[] args) {
        new WarehouseBoss();
    }
}
