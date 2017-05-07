import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
	private WarehouseBoss controller;
	private JPanel[][] boxes;
	private GridComponent[][] grid;
	private int length; 
	private int height;
	
	public Game(WarehouseBoss controller, Map m) {
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(m.getLength()/3, m.getLength()/3, 0 ,0));
		add(panel, BorderLayout.CENTER);
		this.controller = controller;
		length = m.getLength();
		height = m.getHeight();
		
		this.boxes = new JPanel[length/3][height/3];
		for (int y=0; y < height/3; y++) {
			for (int x=0; x < length/3; x++) {
				this.boxes[x][y] = new JPanel(new GridLayout(3,3,0,0));
				panel.add((this.boxes[x][y]));
			}
		}
		/**
		 * for (int y=0; y < map.getHeight(); y++){
                for (int x=0; x < map.getLength(); x++){
                    System.out.print(map.getTile(x, y));
                }
                System.out.print("\n");
            }
			
		 */
		this.grid = new GridComponent[length][height];
		for (int y=0; y < height; y++) {
			for (int x=0; x < length; x++) {
				this.grid[x][y] = new GridComponent(x,y, m.getTile(x, y));
				this.boxes[x/3][y/3].add(this.grid[x][y]);
			}
		}
		
		ImageIcon titleImage = new ImageIcon("image/menu_back1.png");
		JLabel titleLabel = new JLabel("", titleImage, JLabel.CENTER);
		panel.add(titleLabel, BorderLayout.SOUTH);
		titleLabel.setBounds(0, 0, 800, 600);
	}
}
