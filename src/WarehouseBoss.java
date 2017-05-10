import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WarehouseBoss extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel mainPanel = new JPanel();
	JPanel menuPanel = new JPanel();
	JPanel lvlSelPanel = new JPanel();
	//JPanel gamePanel = new JPanel();
	Game gamePanel;
	//JPanel settingsPanel = new JPanel();
	Settings settingsPanel;
	CardLayout views = new CardLayout();
	
	Map map;

	public static void main(String[] args){
		new WarehouseBoss();
	}

	WarehouseBoss(){
		//Create and set up the window.
		super("Warehouse Boss");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//To prevent window resizing
		this.setPreferredSize(new Dimension(800,600));
		this.setResizable(false);			
		
		GameSettings settings = new GameSettings();

		//Map map = new Map(0, 4, 3);
		Map map = new Map('a');
		

		mainPanel.setLayout(views);
		
		gamePanel = new Game(views, mainPanel, map, settings);
		lvlSelPanel.add(new LevelSelector(views, mainPanel, gamePanel));
		//gamePanel.add(new Game(views, mainPanel, map));
		//settingsPanel.add(new Settings(views, mainPanel));
		settingsPanel = new Settings(views, mainPanel, settings);
		menuPanel.add(new Menu(views, mainPanel, gamePanel));
		
		mainPanel.add(menuPanel, "Menu");
		mainPanel.add(lvlSelPanel, "Level");
		mainPanel.add(gamePanel, "Game");
		mainPanel.add(settingsPanel, "Settings");
		views.show(mainPanel, "Menu");

		this.add(mainPanel);

		//Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		/*for (int y=0; y < map.getHeight(); y++){
			for (int x=0; x < map.getLength(); x++){
				System.out.print(map.getTile(x, y));
			}
			System.out.print("\n");
		}*/

	}
	// End constructor
}