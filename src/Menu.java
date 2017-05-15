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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu extends JPanel{
	private String[] buttonText = {"Start Game", "Load Game", "Settings", "Quit"};
	//private Game gamePanel;
	//private GameSettings settings;
	GameSettings settings;

	/**
	 * Constructor for Menu Panel/View
	 */
	public Menu(final CardLayout views, final JPanel mainPanel, GameSettings settings) {
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
					if (map != null) {
						Game newGame = new Game(views, mainPanel, map, settings, false);
//					try {
//						gamePanel.disableKeys();
//						mainPanel.remove(gamePanel);
//					} catch (Exception g) {
//						System.out.println("Nothing to remove");
//					}
						mainPanel.add(newGame, "Game");
						map.printMap();
					} else {
						//Direct to level select when save file is null
						views.show(mainPanel, "Level");
					}
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
	}
}