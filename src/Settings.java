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

public class Settings extends JPanel {
	private String[] text = {"   Music", "   SFX", "Back"};

	/**
	 * Constructor for Settings Panel/View
	 */
	public Settings(CardLayout views, JPanel mainPanel) {
		this.setLayout(new BorderLayout());

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
		musicBox.setBounds(startXPos, startYPos, btnWidth, btnHeight);
		SFXBox.setBounds(startXPos, startYPos + 100, btnWidth, btnHeight);
		backBtn.setBounds(startXPos, startYPos + 300, btnWidth, btnHeight);


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

		ImageIcon titleImage = new ImageIcon("image/menu_back3.png");
		JLabel  titleLabel = new JLabel("", titleImage, JLabel.CENTER);
		this.add(titleLabel);
		titleLabel.setBounds(0, 0, 800, 600);
	}
}