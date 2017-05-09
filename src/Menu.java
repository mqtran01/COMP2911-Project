import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu extends JPanel{
	private String[] buttonText = {"Start Game", "Load Game", "Settings", "Quit"};

	/**
	 * Constructor for Menu Panel/View
	 */
	public Menu(final CardLayout views, final JPanel mainPanel) {
		this.setLayout(new BorderLayout());

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
				//views.show(mainPanel, "Load");
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

		ImageIcon titleImage = new ImageIcon("image/menu_back3.png");
		JLabel  titleLabel = new JLabel("", titleImage, JLabel.CENTER);
		this.add(titleLabel);
		titleLabel.setBounds(0, 0, 800, 600);
	}
}