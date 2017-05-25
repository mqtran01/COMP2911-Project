import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Settings class which contains the layout and controls for
 * the game settings
 * @author Group 1 Tutorial H14A
 *
 */
public class Settings extends JPanel {
	private static final String[] BUTTON_TEXT = {"   Music", "   SFX", "Back"};
	private static final String[] SPRITE_SETS = {"Star Warehouse", "PokeManGo"};
	private GameSettings settings;
	//private final String m_background = "assets/MusicBackground.wav";

	/**
	 * Constructor for Settings Panel/View
	 */
	public Settings(CardLayout views, JPanel mainPanel, GameSettings settings) {
		this.setLayout(new BorderLayout());
		this.settings = settings;

		//Make new check boxes and button
		JCheckBox musicBox = new JCheckBox(BUTTON_TEXT[0]);
		JCheckBox SFXBox = new JCheckBox(BUTTON_TEXT[1]);
		JButton backBtn = new JButton(BUTTON_TEXT[2]);

		//Make checkBox selected initially if settings say it is
		musicBox.setSelected(settings.isEnableMusic());
		SFXBox.setSelected(settings.isEnableSFX());

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
				if (musicBox.isSelected()){
					settings.setEnableMusic(true);
					String skin = settings.getSpriteSet();
					WarehouseBoss.playSound("assets/" + skin + "MusicBackground.wav", settings);
				} else {
					settings.setEnableMusic(false);
					try{
						WarehouseBoss.clip.stop();
					}
					catch(Exception e1){
						
					}
				}
			}
		});

		SFXBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (SFXBox.isSelected()){settings.setEnableSFX(true);}
				else{settings.setEnableSFX(false);}

			}
		});

		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveLoad.saveSettings(Settings.this.settings);
				} catch (IOException e1) {
					System.out.println("save failed");
				}
				views.show(mainPanel, "Menu");
			}
		});

		//Add buttons to this
		this.add(musicBox);
		this.add(SFXBox);
		this.add(backBtn);

		//Create spriteSet selection drop down box
		JComboBox<String> spriteSetList = new JComboBox<String>(SPRITE_SETS);
		spriteSetList.setBounds(startXPos, startYPos + 130, btnWidth, 25);
		
		//Set active spriteSet to selected
		int i = 0;
		for (String s : SPRITE_SETS) {
			if (settings.getSpriteSet().equals(s + "/"))
				spriteSetList.setSelectedIndex(i);
			i++;
		}
		
		//Add listener to comboBox
		spriteSetList.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> combo = (JComboBox<String>)e.getSource();
				String selectedSkin = (String) combo.getSelectedItem();
				settings.setSpriteSet(selectedSkin + "/");
				try{
					WarehouseBoss.clip.stop();
				}
				catch(Exception e1){
					
				}
				WarehouseBoss.changeSound(selectedSkin + "/", settings);
				update(views, mainPanel);
			}
		});

		this.add(spriteSetList);


		String spriteSet = "image/" + settings.getSpriteSet();

		//Display player skins
		ImageIcon skin1Image = new ImageIcon(spriteSet + "Player1.png");
		JLabel  skin1Label = new JLabel("", skin1Image, JLabel.CENTER);
		skin1Label.setBounds(startXPos, startYPos + 175, 100, 100);

		ImageIcon skin2Image = new ImageIcon(spriteSet + "Player2.png");
		JLabel  skin2Label = new JLabel("", skin2Image, JLabel.CENTER);
		skin2Label.setBounds(startXPos + 100, startYPos + 175, 100, 100);

		//Set active skin to selected
		if (settings.isSkin1()) {
			skin1Label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
		} else {
			skin2Label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
		}
		
		//Add listeners
		skin1Label.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				skin1Label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
				skin2Label.setBorder(null);
				settings.setSkin(true);
			}
		});

		skin2Label.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				skin2Label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
				skin1Label.setBorder(null);
				settings.setSkin(false);
			}
		});

		this.add(skin1Label);
		this.add(skin2Label);

		//Add bg
		ImageIcon titleImage = new ImageIcon(spriteSet+ "Menu_Bg.png");
		JLabel  titleLabel = new JLabel("", titleImage, JLabel.CENTER);
		this.add(titleLabel);
		titleLabel.setBounds(0, 0, 800, 600);
	}
	
	/**
	 * Updates all panels to the new skin set
	 * @param views as the list of panels
	 * @param mainPanel as the main control panel
	 */
	private void update(CardLayout views, JPanel mainPanel) {
		mainPanel.removeAll();
		
		mainPanel.add(new JPanel().add(new Menu(views, mainPanel, settings)), "Menu");
		mainPanel.add(new JPanel().add(new StoryLevelSelector(views, mainPanel, settings)), "Story");
		mainPanel.add(new JPanel().add(new RandomLevelSelector(views, mainPanel, settings)), "Random");
		mainPanel.add(new JPanel().add(new Settings(views, mainPanel, settings)), "Settings");
		views.show(mainPanel, "Settings");
	}
}