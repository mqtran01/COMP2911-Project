import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel{
	private String[] buttonText = {"Start Game", "Load Game", "Settings", "Quit"};
	private WarehouseBoss controller;
	
	/**
	 * Constructor for Menu Panel/View
	 */
	public Menu(WarehouseBoss controller) {
		this.setLayout(new BorderLayout());
		//this.setLayout(new GridLayout(5,1));
		this.controller = controller;
		
		//Make new buttons
		JButton startGameBtn = new JButton(buttonText[0]);
		JButton loadGameBtn = new JButton(buttonText[1]);
		JButton settingsBtn = new JButton(buttonText[2]);
		JButton quitBtn = new JButton(buttonText[3]);
		
		//Set the location of each button
		int btnWidth = 200;
		int btnHeight = 50;
		int startXPos = 400 - btnWidth/2;
		int startYPos = 75;
		startGameBtn.setBounds(startXPos, startYPos + 100, btnWidth, btnHeight);
		loadGameBtn.setBounds(startXPos, startYPos + 200, btnWidth, btnHeight);
		settingsBtn.setBounds(startXPos, startYPos + 300, btnWidth, btnHeight);
		quitBtn.setBounds(startXPos, startYPos + 400, btnWidth, btnHeight);
		
		//Set button fonts
		Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
		startGameBtn.setFont(gameFont);
		loadGameBtn.setFont(gameFont);
		settingsBtn.setFont(gameFont);
		quitBtn.setFont(gameFont);
		
		//Set button actions
		startGameBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//GUIView.getInstance().setCurrentPanel("Play");
				//controller.update();
			}
		});
		
		loadGameBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//GUIView.getInstance().setCurrentPanel("Load");
				//controller.update();
			}
		});
		
		settingsBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			    System.out.println("Clicked settings!");
			    //GUIView.getInstance().setCurrentPanel("Settings");
				controller.update("Settings");
				
			}
		});
		
		quitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Add buttons to this
		this.add(startGameBtn);
		this.add(loadGameBtn);
		this.add(settingsBtn);
		this.add(quitBtn);
		
		ImageIcon titleImage = new ImageIcon("image/menu_back1.png");
		JLabel  titleLabel = new JLabel("", titleImage, JLabel.CENTER);
		this.add(titleLabel);
		titleLabel.setBounds(0, 0, 800, 600);
	}
}