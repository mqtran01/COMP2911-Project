import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
			createAndShowGUI();
			new WarehouseBoss();
			
		}
		
		WarehouseBoss(){
			Map map = new Map('a');
			for (int y=0; y < 8; y++){
				for (int x=0; x < 6; x++){
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
		
		private static void createAndShowGUI() {
			//Create and set up the window.
			JFrame frame = new JFrame("Warehouse Boss");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Set Layout
			frame.setLayout(new BorderLayout());
			//To prevent window resizing
			frame.setResizable(false);
			//Set up the content pane.
			addComponentsToPane(frame.getContentPane());
			//Display the window.
			frame.pack();
			frame.setVisible(true);
			}
		
		public static void addComponentsToPane(Container pane) {
			String[] buttonText = {"Start Game", "Load Game", "Quit", "Settings"};
			
			JPanel panel = new JPanel(new BorderLayout());
			panel.setLayout(new GridLayout(5,1));
			
			ImageIcon image = new ImageIcon("image/title.png");
			JLabel label = new JLabel("", image, JLabel.CENTER);
			panel.add( label, BorderLayout.NORTH);

			int counter = 0;
			for (int i = 0; i < 4; i++) {
				JButton btn = new JButton(buttonText[counter++]);
				btn.setFont(new Font("Myriad Pro Light", Font.BOLD, 20));
				btn.setHorizontalAlignment(JTextField.CENTER);
				btn.setPreferredSize(new Dimension(300, 50));
				panel.add(btn);
			}
			
			pane.add(panel);		  
			
		}
}
