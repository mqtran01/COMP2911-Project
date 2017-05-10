import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Settings extends JPanel {
	private String[] text = {"   Music", "   SFX", "Back"};
	private GameSettings settings;
	
	/**
	 * Constructor for Settings Panel/View
	 */
	public Settings(CardLayout views, JPanel mainPanel, GameSettings settings) {
		this.setLayout(new BorderLayout());
		
		this.settings = settings;

		//Make new check boxes and button
		JCheckBox musicBox = new JCheckBox(text[0]);
		JCheckBox SFXBox = new JCheckBox(text[1]);
		JButton backBtn = new JButton(text[2]);

		//Make checkBox selected initially
		musicBox.setSelected(true);
		SFXBox.setSelected(true);
		
		//Set the location of each button
		int btnWidth = 200;
		int btnHeight = 50;
		int startXPos = 400 - btnWidth/2;
		int startYPos = 150;
		musicBox.setBounds(startXPos, startYPos, btnWidth, btnHeight - 15);
		SFXBox.setBounds(startXPos, startYPos + 65, btnWidth, btnHeight - 15);
		backBtn.setBounds(startXPos, startYPos + 290, btnWidth, btnHeight);


		//Set button fonts
		Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
		musicBox.setFont(gameFont);
		SFXBox.setFont(gameFont);
		backBtn.setFont(gameFont);

		//Set button border
		Border buttonBorder = new LineBorder(Color.BLUE, 2);
		backBtn.setBorder(buttonBorder);

		//Set button actions
		musicBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				settings.setEnableMusic(true);
				//TODO
			}
		});

		SFXBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});

		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				views.show(mainPanel, "Menu");
			}
		});

		//Add buttons to this
		this.add(musicBox);
		this.add(SFXBox);
		this.add(backBtn);
		
		ButtonGroup costumes = new ButtonGroup();
		
		JRadioButton redRobot = new JRadioButton("Red Robot");
		redRobot.setBounds(startXPos, startYPos + 130, 100, 25);
		
		JRadioButton blueRobot = new JRadioButton("Blue Robot");
		blueRobot.setBounds(startXPos + 100, startYPos + 130, 100, 25);
		
		if(settings.isCharacterColorRed()){
			//Red character selected
			redRobot.setSelected(true);
		} else {
			blueRobot.setSelected(true);
		}
		
		redRobot.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				settings.setIsCharacterRed(true);
			}
		});
		blueRobot.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				settings.setIsCharacterRed(false);
			}
		});
		costumes.add(redRobot);
		costumes.add(blueRobot);
		this.add(redRobot);
		this.add(blueRobot);
		
		
		ImageIcon redRobotImage = new ImageIcon("image/Player1.png");
		JLabel  redRobotLabel = new JLabel("", redRobotImage, JLabel.CENTER);
		redRobotLabel.setFont(gameFont);
		this.add(redRobotLabel);
		redRobotLabel.setBounds(startXPos, startYPos + 175, 100, 100);
		
		ImageIcon blueRobotImage = new ImageIcon("image/Player2.png");
		JLabel  blueRobotLabel = new JLabel("", blueRobotImage, JLabel.CENTER);
		blueRobotLabel.setFont(gameFont);
		this.add(blueRobotLabel);
		blueRobotLabel.setBounds(startXPos + 100, startYPos + 175, 100, 100);
		
		ImageIcon titleImage = new ImageIcon("image/menu_back3.png");
		JLabel  titleLabel = new JLabel("", titleImage, JLabel.CENTER);
		this.add(titleLabel);
		titleLabel.setBounds(0, 0, 800, 600);
	}
}