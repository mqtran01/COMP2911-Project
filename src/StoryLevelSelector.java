import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class StoryLevelSelector extends JPanel {
	private GamePanel gamePanel;
	private char[] seed = {'a','b','c'};
	private Models models;
	
	public StoryLevelSelector(final CardLayout views, final JPanel mainPanel, SettingsModel settings, Models models) {
		this.setLayout(new BorderLayout());
		this.models = models;
		ImageIcon bgImage = new ImageIcon("image/" + settings.getSpriteSet() + "bg.png");
		JLabel  bgLabel = new JLabel("", bgImage, JLabel.CENTER);
		bgLabel.setBounds(0, 0, 800, 600);
		bgLabel.setLayout(new GridBagLayout());
		
		//Set constraints
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,30,5);
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		
		//Set font and border settings
		Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
		Border buttonBorder = new LineBorder(Color.BLUE, 2);
		
		int counter = 0;
		int y = -1;
		while (counter < 30) {
			if (counter%10 == 0) y++;
			
			gbc.gridx = counter%10;
			gbc.gridy = y;
			
			JButton level = new JButton(Integer.toString(counter+1));
			level.setPreferredSize(new Dimension(60,60));
			level.setFont(gameFont);
			level.setBorder(buttonBorder);
			
			final int levelNum = counter%10 + 1;
			final char levelSeed = seed[y];
			
			level.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					MapModel newMap = new MapModel(levelSeed, levelNum);
					GamePanel newGame = new GamePanel(views, mainPanel, newMap, settings, false, models);
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
			
			//Use to determine which levels can user play
			if (counter > settings.getNumLevelsCleared()) level.setEnabled(false);
			
			//Add level button the label
			bgLabel.add(level, gbc);
			
			counter++;
		}
		
		//Add bg to the view - Game Panel 
		this.add(bgLabel);
		
		//Add backBtn
		JButton backBtn = new JButton("Back");
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 3;
		gbc.gridwidth = 4;
		gbc.gridy = 4;
		bgLabel.add(backBtn, gbc);
		
		//Add font and border
		backBtn.setFont(gameFont);
		backBtn.setBorder(buttonBorder);

		//Add listener
		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Back Button!");
				views.show(mainPanel, "Menu");
			}
		});
	}
}
