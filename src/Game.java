import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
	private JLabel[][] grid;
	private int length; 
	private int height;

	public Game(CardLayout views, JPanel mainPanel, Map m)  {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setPreferredSize(new Dimension(800,600));
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
//		saveBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Clicked Save!");
//				//views.show(mainPanel, "Save");
//			}
//		});
//		
//		hintBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Clicked Hint!");
//				//views.show(mainPanel, "Hint");
//			}
//		});
//		quitBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Clicked Quit!");
//				System.exit(0);
//			}
//		});
		
		//add(saveBtn);
		//add(hintBtn);
		//add(quitBtn);
	}
}
