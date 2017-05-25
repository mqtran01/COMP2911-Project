import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Level Selector class which contains layout and controller
 * Used to choose a stage
 * @author Group 1 Tutorial H14A
 *
 */
public class RandomLevelSelector extends JPanel {
	private static final String[] BUTTON_TEXT = {"Easy", "Medium", "Hard", "Back", " Random"};
	private GamePanel gamePanel;
	//private boolean isRandomLevel;

	/**
	 * Constructor of the Level Selector object
	 * @param views as the layout collection
	 * @param mainPanel as the main visible panel
	 * @param settings as the application settings
	 */
	public RandomLevelSelector(final CardLayout views, final JPanel mainPanel, SettingsModel settings) {
		this.setLayout(new BorderLayout());

		this.gamePanel = null;
//		this.isRandomLevel = false;
		
		// Make selection buttons
		JButton easyBtn = new JButton(BUTTON_TEXT[0]);
		JButton medBtn = new JButton(BUTTON_TEXT[1]);
		JButton hardBtn = new JButton(BUTTON_TEXT[2]);
		JButton backBtn = new JButton(BUTTON_TEXT[3]);
//		JCheckBox randomBox = new JCheckBox(buttonText[4]);

		//Set the location of each button
		int btnWidth = 200;
		int btnHeight = 50;
		int startXPos = 400 - btnWidth/2;
		int startYPos = 150;
		easyBtn.setBounds(startXPos, startYPos + 50, btnWidth, btnHeight);
		medBtn.setBounds(startXPos, startYPos + 120, btnWidth, btnHeight);
		hardBtn.setBounds(startXPos, startYPos + 190, btnWidth, btnHeight);
		backBtn.setBounds(startXPos, startYPos + 290, btnWidth, btnHeight);
//		randomBox.setBounds(startXPos + 40, startYPos + 230, btnWidth-50, btnHeight);

		//Set button fonts
		Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
		easyBtn.setFont(gameFont);
		medBtn.setFont(gameFont);
		hardBtn.setFont(gameFont);
		backBtn.setFont(gameFont);
//		randomBox.setFont(gameFont);
		
//		//Set Random text colour according to spriteSet
//		if (settings.getSpriteSet().equals("Star Warehouse/")) {
//			randomBox.setForeground(Color.WHITE);
//		} else {
//			randomBox.setForeground(Color.BLACK);
//		}

		//Set button border
		Border buttonBorder = new LineBorder(Color.BLUE, 2);
		easyBtn.setBorder(buttonBorder);
		medBtn.setBorder(buttonBorder);
		hardBtn.setBorder(buttonBorder);
		backBtn.setBorder(buttonBorder);
//		randomBox.setOpaque(false);

		//Set random checkBox property and action
//		randomBox.setSelected(isRandomLevel);
//		randomBox.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (randomBox.isSelected()){
//				    isRandomLevel = true;
//				    System.out.println("Enabled random");
//				} else {
//				    isRandomLevel = false;
//				    System.out.println("Disabled random");
//				}
//			}
//		});
		
		//Set button actions
		easyBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Easy Button!");
				MapModel newMap;
//				if (isRandomLevel) {
				    newMap = new MapModel(0);
//				} else {
//				    newMap = new Map('a');
//				}
				GamePanel newGame = new GamePanel(views, mainPanel, newMap, settings, true);
				try {
					gamePanel.disableKeys();
					mainPanel.remove(gamePanel);
				} catch (Exception g) {
					System.out.println("Nothing to remove");
				}
				gamePanel = newGame;
				mainPanel.add(newGame, "Game");
				views.show(mainPanel, "Game");

			}
		});

		medBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Medium Button!");
				
				MapModel newMap;
//                if (isRandomLevel) {
                    newMap = new MapModel(1);
//                } else {
//                    newMap = new Map('b');
//                }
				GamePanel newGame = new GamePanel(views, mainPanel, newMap, settings, true);
				try {
					gamePanel.disableKeys();
					mainPanel.remove(gamePanel);
				} catch (Exception g) {
					System.out.println("Nothing to remove");
				}
				gamePanel = newGame;
				mainPanel.add(newGame, "Game");
				views.show(mainPanel, "Game");
			}
		});

		hardBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Hard Button!");
				
				MapModel newMap;
//                if (isRandomLevel) {
                    newMap = new MapModel(2);
//                } else {
//                    newMap = new Map('c');
//                }
				GamePanel newGame = new GamePanel(views, mainPanel, newMap, settings, true);
				try {
					gamePanel.disableKeys();
					mainPanel.remove(gamePanel);
				} catch (Exception g) {
					System.out.println("Nothing to remove");
				}
				gamePanel = newGame;
				mainPanel.add(newGame, "Game");
				views.show(mainPanel, "Game");
			}
		});

		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Back Button!");
				views.show(mainPanel, "Menu");
			}
		});

		//Add buttons to this
		this.add(easyBtn);
		this.add(medBtn);
		this.add(hardBtn);
		this.add(backBtn);
//		this.add(randomBox);

		ImageIcon bgImage = new ImageIcon("image/" + settings.getSpriteSet() + "Level_Select_Bg.png");
		JLabel  bgLabel = new JLabel("", bgImage, JLabel.CENTER);
		this.add(bgLabel);
		bgLabel.setBounds(0, 0, 800, 600);
	}


}
