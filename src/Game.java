import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

		this.grid = new GridComponent[length][height];
		for (int y=0; y < height; y++) {
			for (int x=0; x < length; x++) {
				this.grid[x][y] = new GridComponent(x,y, m.getTile(x, y));
				this.boxes[x/3][y/3].add(this.grid[x][y]);
			}
		}
		
		JButton saveBtn = new JButton("Save");
		JButton hintBtn = new JButton("Hint");
		JButton quitBtn = new JButton("Quit");
		JPanel btnPanel = new JPanel(new GridLayout(1,3));
		
		btnPanel.add(saveBtn);
		btnPanel.add(hintBtn);
		btnPanel.add(quitBtn);
		
		saveBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Clicked Save!");
				//controller.update("Save");
			}
		});
		
		hintBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Clicked Hint!");
				//controller.update("Hint");
			}
		});
		quitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Quit!");
				System.exit(0);
			}
		});
		
		panel.add(btnPanel, BorderLayout.SOUTH);
	}
}
