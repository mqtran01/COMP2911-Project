import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WarehouseBoss extends JFrame {
	
		private static final long serialVersionUID = 1L;
		
		public static void main(String[] args){
			new WarehouseBoss();
			
		}
		
		WarehouseBoss(){
			//Create and set up the window.
			super("Warehouse Boss");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Set Layout
			this.setLayout(new BorderLayout());
			//To prevent window resizing
			this.setPreferredSize(new Dimension(800,600));
			this.setResizable(false);
			
			//Set up the content pane.
			addComponentsToPane(this.getContentPane());
			
			//Display the window.
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
			
		    /*
			Map map = new Map('a');
		    for (int y=0; y < 8; y++){
                for (int x=0; x < 6; x++){
                    System.out.print(map.getTile(x, y));
                }
                System.out.print("\n");
            }
            */
		    
		    Map map = new Map(0, 3, 2);
		    for (int y=0; y < map.getHeight(); y++){
                for (int x=0; x < map.getLength(); x++){
                    System.out.print(map.getTile(x, y));
                }
                System.out.print("\n");
            }
			
			
			Scanner sc = new Scanner(System.in);
			String input;
			while(!(input = sc.next()).equals("x")){
				System.out.println(input);
				if (input.equals("w")){
					map.moveUp();
				}
				else if (input.equals("d")){
					map.moveRight();
				}
				else if (input.equals("s")){
					map.moveDown();
				}
				else if (input.equals("a")){
					map.moveLeft();
				}
				for (int y=0; y < 8; y++){
					for (int x=0; x < 6; x++){
						System.out.print(map.getTile(x, y));
					}
					System.out.print("\n");
				}
				if (map.winState())break;
			}
			sc.close();
			System.out.println("You win");
			
		}
		
		public static void addComponentsToPane(Container pane) {
			String[] buttonText = {"Start Game", "Load Game", "Settings", "Quit"};
			
			JPanel panel = new JPanel(new BorderLayout());
			//panel.setLayout(new GridLayout(5,1));
			panel.setLayout(null);
			
			//Make new buttons
			JButton startGameBtn = new JButton(buttonText[0]);
			JButton loadGameBtn = new JButton(buttonText[1]);
			JButton settingsBtn = new JButton(buttonText[2]);
			JButton quitBtn = new JButton(buttonText[3]);
			
			//Add buttons to panel
			panel.add(startGameBtn);
			panel.add(loadGameBtn);
			panel.add(settingsBtn);
			panel.add(quitBtn);
			
			
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
			quitBtn.addActionListener(e->System.exit(0));
			
			ImageIcon titleImage = new ImageIcon("image/menu_back1.png");
			JLabel  titleLabel = new JLabel("", titleImage, JLabel.CENTER);
			panel.add(titleLabel);
			titleLabel.setBounds(0, 0, 800, 600);
			pane.add(panel);		  
			
		}
}
