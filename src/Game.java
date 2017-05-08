import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
	//private WarehouseBoss controller;
	private JLabel[][] grid;
	private int length; 
	private int height;
	
	public Game(WarehouseBoss controller, Map m) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		length = m.getLength();
		height = m.getHeight();
		grid = new JLabel[length][height];
		
		for (int y=0; y<height; y++){
			for (int x=0; x<length; x++){
				if (m.getTile(x, y) == m.WALL){
					grid[x][y] = new JLabel("", new ImageIcon("image/Wall.png"), JLabel.CENTER);
					
				} else if (m.getTile(x, y) == m.EMPTY){
					grid[x][y] = new JLabel("", new ImageIcon("image/Empty.png"), JLabel.CENTER);
					
				} else if (m.getTile(x, y) == m.PLAYER){
					grid[x][y] = new JLabel("", new ImageIcon("image/Wall.png"), JLabel.CENTER);
					
				} else if (m.getTile(x, y) == m.BOX){
					grid[x][y] = new JLabel("", new ImageIcon("image/Box.png"), JLabel.CENTER);
					
				} else if (m.getTile(x, y) == m.GOAL){
					grid[x][y] = new JLabel("", new ImageIcon("image/Goal.png"), JLabel.CENTER);
					
				} else if (m.getTile(x, y) == m.GOALBOX){
					grid[x][y] = new JLabel("", new ImageIcon("image/Wall.png"), JLabel.CENTER);
					
				} else if (m.getTile(x, y) == m.GOALPLAYER){
					grid[x][y] = new JLabel("", new ImageIcon("image/Wall.png"), JLabel.CENTER);
				}
				grid[x][y].setPreferredSize(new Dimension(800/9,500/6));
				add(grid[x][y]);
			}
		}
		
		
//		JButton saveBtn = new JButton("Save");
//		JButton hintBtn = new JButton("Hint");
//		JButton quitBtn = new JButton("Quit");
//		
//		saveBtn.setBounds(50,550, 50, 25);
//		hintBtn.setBounds(150,550, 50, 25);
//		quitBtn.setBounds(250,550, 50, 25);
//		
//		this.add(saveBtn);
//		this.add(hintBtn);
//		this.add(quitBtn);
		
//		saveBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Clicked Save!");
//				//controller.update("Save");
//			}
//		});
//		
//		hintBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Clicked Hint!");
//				//controller.update("Hint");
//			}
//		});
//		quitBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Clicked Quit!");
//				System.exit(0);
//			}
//		});
		
		//panel.add(btnPanel);
	}
}
