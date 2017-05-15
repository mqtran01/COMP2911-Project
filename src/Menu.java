import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu extends JPanel{
	private String[] buttonText = {"Start Game", "Load Game", "Settings", "Quit"};
	//private Game gamePanel;
	//private GameSettings settings;
	private final String m_background = "assets/MusicBackground.wav";
	GameSettings settings;

	/**
	 * Constructor for Menu Panel/View
	 */
	public Menu(final CardLayout views, final JPanel mainPanel, Game gamePanel, GameSettings settings) {
		this.setLayout(new BorderLayout());
		this.settings = settings;


		//this.gamePanel = gamePanel;
		//this.settings = settings;

		//Make new buttons
		JButton startGameBtn = new JButton(buttonText[0]);
		JButton loadGameBtn = new JButton(buttonText[1]);
		JButton settingsBtn = new JButton(buttonText[2]);
		JButton quitBtn = new JButton(buttonText[3]);

		//Set the location of each button
		int btnWidth = 200;
		int btnHeight = 50;
		int startXPos = 400 - btnWidth/2;
		int startYPos = 150;
		startGameBtn.setBounds(startXPos, startYPos, btnWidth, btnHeight);
		loadGameBtn.setBounds(startXPos, startYPos + 90, btnWidth, btnHeight);
		settingsBtn.setBounds(startXPos, startYPos + 190, btnWidth, btnHeight);
		quitBtn.setBounds(startXPos, startYPos + 290, btnWidth, btnHeight);

		//Set button fonts
		Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
		startGameBtn.setFont(gameFont);
		loadGameBtn.setFont(gameFont);
		settingsBtn.setFont(gameFont);
		quitBtn.setFont(gameFont);

		//Set button border
		Border buttonBorder = new LineBorder(Color.BLUE, 2);
		startGameBtn.setBorder(buttonBorder);
		loadGameBtn.setBorder(buttonBorder);
		settingsBtn.setBorder(buttonBorder);
		quitBtn.setBorder(buttonBorder);

		//Set button actions
		startGameBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Play Game!");
				views.show(mainPanel, "Level");
			}
		});

		loadGameBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Load Game!");
				try {
					Map map = SaveLoad.load();
					Game newGame = new Game(views, mainPanel, map, settings);
					try {
						gamePanel.disableKeys();
						mainPanel.remove(gamePanel);
					} catch (Exception g) {
						System.out.println("Nothing to remove");
					}
					mainPanel.add(newGame, "Game");
					map.printMap();
				} catch (IOException e1) {
					System.out.println("Load failed!");
					e1.printStackTrace();
				}

				views.show(mainPanel, "Game");
			}
		});

		settingsBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked settings!");
				views.show(mainPanel, "Settings");
			}
		});

		quitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Quit!");
				System.exit(0);
			}
		});

		//Add buttons to this
		this.add(startGameBtn);
		this.add(loadGameBtn);
		this.add(settingsBtn);
		this.add(quitBtn);

		ImageIcon titleImage = new ImageIcon(settings.getSpriteSet() + "Menu_Bg.png");
		JLabel  titleLabel = new JLabel("", titleImage, JLabel.CENTER);
		this.add(titleLabel);
		titleLabel.setBounds(0, 0, 800, 600);
		playSound(m_background);
	}

	private void playSound(String filename) {
		Thread musicThread = new Thread() {
			@Override
			public void run() {
				try{
					// Open an audio input stream.
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
					// Get a sound clip resource.
					Clip clip = AudioSystem.getClip();
					// Open audio clip and load samples from the audio input stream.
					clip.open(audioIn);
					clip.start();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		if (settings.isEnableMusic()) {
			System.out.println("Music enabled"); // TODO settings can stop music
			musicThread.start();
			loopSound(filename, musicThread);
		}
	}

	private void loopSound(String filename, Thread musicThread) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Looping sound");
				if (settings.isEnableMusic()) {
					playSound(filename);
				}
			}
		}, 165*1000);
	}
}