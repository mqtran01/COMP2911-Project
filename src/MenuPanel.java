import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Class of the Main Menu with layout and controller
 * Used to navigate to other panels
 * @author Group 1 Tutorial H14A
 *
 */
public class MenuPanel extends JPanel{
	private static final String[] BUTTON_TEXT = {"Start Game", "Load Game", "Random Game", "Settings", "Quit"};
//	private SettingsModel settings;
	private Models models;

	/**
	 * Constructor for Menu Panel/View
	 */
	public MenuPanel(WarehouseBoss warehouseBoss, Models models) {
		this.models = models;
		this.setLayout(new BorderLayout());
//		this.settings = settings;
		
		//Make new buttons
		JButton startGameBtn = new JButton(BUTTON_TEXT[0]);
		JButton loadGameBtn = new JButton(BUTTON_TEXT[1]);
		JButton randGameBtn = new JButton(BUTTON_TEXT[2]);
		JButton settingsBtn = new JButton(BUTTON_TEXT[3]);
		JButton quitBtn = new JButton(BUTTON_TEXT[4]);

		//Set the location of each button
		int btnWidth = 200;
		int btnHeight = 50;
		int startXPos = 400 - btnWidth/2;
		int startYPos = 150;
		startGameBtn.setBounds(startXPos, startYPos, btnWidth, btnHeight);
		loadGameBtn.setBounds(startXPos, startYPos + 80, btnWidth, btnHeight);
		randGameBtn.setBounds(startXPos, startYPos + 160, btnWidth, btnHeight);
		settingsBtn.setBounds(startXPos, startYPos + 240, btnWidth, btnHeight);
		quitBtn.setBounds(startXPos, startYPos + 320, btnWidth, btnHeight);
		
		//Set button fonts
		Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
		startGameBtn.setFont(gameFont);
		loadGameBtn.setFont(gameFont);
		randGameBtn.setFont(gameFont);
		settingsBtn.setFont(gameFont);
		quitBtn.setFont(gameFont);


		//Set button border
		Border buttonBorder = new LineBorder(Color.BLUE, 2);
		startGameBtn.setBorder(buttonBorder);
		loadGameBtn.setBorder(buttonBorder);
		randGameBtn.setBorder(buttonBorder);
		settingsBtn.setBorder(buttonBorder);
		quitBtn.setBorder(buttonBorder);


		//Set button listeners
		startGameBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Play Game!");
				warehouseBoss.swapPanel("Story");
			}
		});

		loadGameBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Load Game!");
				try {
					MapModel map = SaveLoad.load();
					if (map != null) {
						GamePanel newGame = new GamePanel(warehouseBoss, map, false, models);
						warehouseBoss.addPanel(newGame, "Game");
						map.printMap();
					} else {
						//Direct to level select when save file is null
						warehouseBoss.swapPanel("Story");
					}
				} catch (IOException e1) {
					System.out.println("Load failed!");
					e1.printStackTrace();
				}

				warehouseBoss.swapPanel("Game");
			}
		});
		
		randGameBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Random Game!");
				warehouseBoss.swapPanel("Random");
			}
		});


		settingsBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked settings!");
				warehouseBoss.swapPanel("Settings");
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
		this.add(randGameBtn);
		this.add(settingsBtn);
		this.add(quitBtn);

		//Add bg
		ImageIcon bgImage = new ImageIcon("image/" + models.getSpriteSet() + "Menu_Bg.png");
		JLabel  bgLabel = new JLabel("", bgImage, JLabel.CENTER);
		this.add(bgLabel);
		bgLabel.setBounds(0, 0, 800, 600);
	}
}