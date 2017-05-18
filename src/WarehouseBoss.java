import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WarehouseBoss extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel mainPanel = new JPanel();
	JPanel menuPanel = new JPanel();
	JPanel lvlSelPanel = new JPanel();
	//JPanel gamePanel = new JPanel();
	//Game gamePanel;
	JPanel settingsPanel = new JPanel();
	//Settings settingsPanel;
	CardLayout views = new CardLayout();
	
	Map map;
	
	//private final String m_background = "assets/MusicBackground.wav";
	public static Clip clip;

	public static void main(String[] args){
		new WarehouseBoss();
	}

	WarehouseBoss(){
		//Create and set up the window.
		super("Warehouse Boss");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//To prevent window resizing
		this.setPreferredSize(new Dimension(800,600));
		this.setResizable(false);			
		
		//GameSettings settings = new GameSettings();
		GameSettings settings;
		try {
			settings = SaveLoad.loadSettings();
			if (settings == null){
				settings = new GameSettings();//if it failed, just create a new one
			}
		} catch (IOException e) {
			settings = new GameSettings();//if it failed, just create a new one
		}

		//Map map = new Map(0, 4, 3);
		//Map map = new Map('a');
		

		mainPanel.setLayout(views);
		
		//gamePanel = new Game(views, mainPanel, map, settings);
		lvlSelPanel.add(new LevelSelector(views, mainPanel, settings));
		//gamePanel.add(new Game(views, mainPanel, map));
		//settingsPanel.add(new Settings(views, mainPanel));
		settingsPanel.add(new Settings(views, mainPanel, settings));
		menuPanel.add(new Menu(views, mainPanel, settings));
		
		mainPanel.add(menuPanel, "Menu");
		mainPanel.add(lvlSelPanel, "Level");
		//mainPanel.add(gamePanel, "Game");
		mainPanel.add(settingsPanel, "Settings");
		views.show(mainPanel, "Menu");

		this.add(mainPanel);

		//Play BGM
		String path = settings.getSpriteSet();
		playSound("assets/" + path + "/MusicBackground.wav", settings);


		//Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		/*for (int y=0; y < map.getHeight(); y++){
			for (int x=0; x < map.getLength(); x++){
				System.out.print(map.getTile(x, y));
			}
			System.out.print("\n");
		}*/

	}
	// End constructor
	static private void playSound(String filename, GameSettings settings) {
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

	static private void loopSound(String filename, Thread musicThread, GameSettings settings) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Looping sound");
				if (settings.isEnableMusic()) {
					playSound(filename, settings);
				}
			}
		}, 165*1000);
	}

	static public void changeSound(String skin, GameSettings settings) {
		String path = "assets/" + skin + "MusicBackground.wav";
		System.out.println("changeSound!" + path);
		playSound(path, settings);
	}
}