import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Game extends JPanel {
	private JLabel[][] grid;
	private int length; 
	private int height;

	public Game(CardLayout views, JPanel mainPanel, Map m)  {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setPreferredSize(new Dimension(800,600));
		length = m.getLength();
		height = m.getHeight();
		grid = new JLabel[length][height];
		
		ImageIcon wall = resizedImage(Map.WALL, length, height);
		ImageIcon empty = resizedImage(Map.EMPTY, length, height);
	    ImageIcon player = resizedImage(Map.PLAYER, length, height);
		ImageIcon box = resizedImage(Map.BOX, length, height);
		ImageIcon goal = resizedImage(Map.GOAL, length, height);
		ImageIcon goalBox = resizedImage(Map.GOALBOX, length, height);
		ImageIcon goalPlayer = resizedImage(Map.GOALPLAYER, length, height);

		

		for (int y=0; y<height; y++){
			for (int x=0; x<length; x++){
				if (m.getTile(x, y) == Map.WALL){
				    grid[x][y] = new JLabel("", wall, JLabel.CENTER);

				} else if (m.getTile(x, y) == Map.EMPTY){
					grid[x][y] = new JLabel("", empty, JLabel.CENTER);

				} else if (m.getTile(x, y) == Map.PLAYER){
					grid[x][y] = new JLabel("", player, JLabel.CENTER);

				} else if (m.getTile(x, y) == Map.BOX){
					grid[x][y] = new JLabel("", box, JLabel.CENTER);

				} else if (m.getTile(x, y) == Map.GOAL){
					grid[x][y] = new JLabel("", goal, JLabel.CENTER);

				} else if (m.getTile(x, y) == Map.GOALBOX){
					grid[x][y] = new JLabel("", goalBox, JLabel.CENTER);

				} else if (m.getTile(x, y) == Map.GOALPLAYER){
					grid[x][y] = new JLabel("", goalPlayer, JLabel.CENTER);
				}
				
				double scaledLen = 800/length;
		        double scaledHgt = 500/height;
		        int proportion = (int) Math.min(scaledLen, scaledHgt);
		        grid[x][y].setPreferredSize(new Dimension(proportion,proportion));
				//grid[x][y].setPreferredSize(new Dimension(800/length,500/height));
				add(grid[x][y]);
			}
		}

		Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
		Border buttonBorder = new LineBorder(Color.BLUE, 2);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.setFont(gameFont);
		saveBtn.setBorder(buttonBorder);
		saveBtn.setPreferredSize(new Dimension((800-3)/3, 68));
		
		JButton hintBtn = new JButton("Hint");
		hintBtn.setFont(gameFont);
		hintBtn.setBorder(buttonBorder);
		hintBtn.setPreferredSize(new Dimension((800-3)/3, 68));
		
		JButton quitBtn = new JButton("Main Menu");
		quitBtn.setFont(gameFont);
		quitBtn.setBorder(buttonBorder);
		quitBtn.setPreferredSize(new Dimension((800-3)/3, 68));

		
		saveBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Save!");
				//views.show(mainPanel, "Save");
			}
		});
		
		hintBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Hint!");
				//views.show(mainPanel, "Hint");
			}
		});
		quitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Main Menu!");
				views.show(mainPanel, "Menu");;
			}
		});
		
		add(saveBtn);
		add(hintBtn);
		add(quitBtn);
	}
	
	private ImageIcon resizedImage(int item, int length, int height) {
	    
	    String imgLoc = null;
	    switch (item) {
	        case Map.WALL:
	            imgLoc = "image/Wall.png";
	            break;
	        case Map.EMPTY:
	            imgLoc = "image/Empty.png";
                break;
	        case Map.PLAYER:
	            imgLoc = "image/Wall.png"; // TODO proper image
                break;
            case Map.BOX:
                imgLoc = "image/Box.png";
                break;
            case Map.GOAL:
                imgLoc = "image/Goal.png";
                break;
            case Map.GOALBOX:
                imgLoc = "image/Wall.png"; // TODO proper image
                break;
            case Map.GOALPLAYER:
                imgLoc = "image/Wall.png"; // TODO proper image
                break;
	    }
	    
	    BufferedImage img = null;
	    try {
	        img = ImageIO.read(new File(imgLoc));
	    } catch (IOException e) {
	        return null;
	    }
	    
	    double scaledLen = 800/length;
	    double scaledHgt = 500/height;
	    int proportion = (int) Math.min(scaledLen, scaledHgt);
	    Image dimg = img.getScaledInstance(proportion, proportion, Image.SCALE_SMOOTH);
	    return new ImageIcon(dimg);
	}
}
