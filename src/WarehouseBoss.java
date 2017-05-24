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
 * @author Group 1 Tutorial H14A
 *
 */
public class WarehouseBoss extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel mainPanel = new JPanel();
	CardLayout views = new CardLayout();

	public static Clip clip;

	/**
	 * Main entry point for the Warehouse Boss game.
	 * @param args is irrelevant
	 */
	public static void main(String[] args){
		new WarehouseBoss();
	}

	/**
	 * Constructor of the overall game container.
	 * Also controls the background music of the game.
	 */
	WarehouseBoss(){
		//Create and set up the window.
		super("Warehouse Boss");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set preferred dimensions
		this.setPreferredSize(new Dimension(800,600));
		
		//To prevent window resizing
		this.setResizable(false);			
		
		GameSettings settings;
		try {
			settings = SaveLoad.loadSettings();
			if (settings == null){
				settings = new GameSettings();//if it failed, just create a new one
			}
		} catch (IOException e) {
			settings = new GameSettings();//if it failed, just create a new one
		}

		//Set mainPanel layout to CardLayout
		mainPanel.setLayout(views);
		
		//Create each view objects
		StoryLevelSelector storyLvlPanel = new StoryLevelSelector(views, mainPanel, settings);
		RandomLevelSelector randLvlPanel = new RandomLevelSelector(views, mainPanel, settings);
		Settings settingsPanel = new Settings(views, mainPanel, settings);
		Menu menuPanel = new Menu(views, mainPanel, settings);
		
		//Add panels to mainPanel, utilising CardLayout
		mainPanel.add(menuPanel, "Menu");
		mainPanel.add(storyLvlPanel, "Story");
		mainPanel.add(randLvlPanel, "Random");
		mainPanel.add(settingsPanel, "Settings");
		
		//Display menuPanel
		views.show(mainPanel, "Menu");

		//Add mainPanel to frame
		this.add(mainPanel);

		//Play BGM
		playSound("assets/" + settings.getSpriteSet() + "/MusicBackground.wav", settings);

		//Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Plays the sound of the specific sound file.
	 * @param filename as the file path of the sound file
	 * @param settings as the settings controller
	 */
	static public void playSound(String filename, GameSettings settings) {
		Thread musicThread = new Thread() {
			@Override
			public void run() {
				try{
					// Open an audio input stream.
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
					// Get a sound clip resource.
					clip = AudioSystem.getClip();
					// Open audio clip and load samples from the audio input stream.
					clip.open(audioIn);
					clip.start();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		if (settings.isEnableMusic()) {
			System.out.println("Music enabled");
			musicThread.start();
			loopSound(filename, musicThread, settings);
		}
	}

	/**
	 * Creates a delay to loop the sound file after it is complete
	 * @param filename as the specified file name
	 * @param musicThread as the thread playing the music
	 * @param settings as the settings controller
	 */
	static private void loopSound(String filename, Thread musicThread, GameSettings settings) {
		String skin = settings.getSpriteSet();
		int delay;
		if (skin.equals("Star Warehouse/")) {
			delay = 165*1000;
		} else {
			delay = 110*1000;
		}
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Looping sound");
				// if music is enabled and skin has not been changed
				if (settings.isEnableMusic() && (skin.equals(settings.getSpriteSet()))) {
					playSound(filename, settings);
				}
			}
		}, delay);
	}
	
	/**
	 * Changes the background music based on skin
	 * @param skin as the skin set
	 * @param settings as the settings controller
	 */
	static public void changeSound(String skin, GameSettings settings) {
		String path = "assets/" + skin + "MusicBackground.wav";
		clip.stop();
		System.out.println("changeSound!" + path);
		playSound(path, settings);
	}
}