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
	JPanel gamePanel = new JPanel();
	JPanel settingsPanel = new JPanel();
	CardLayout views = new CardLayout();

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

		Map map = new Map(0, 4, 3);

		mainPanel.setLayout(views);
		menuPanel.add(new Menu(views, mainPanel));
		gamePanel.add(new Game(views, mainPanel, map));
		settingsPanel.add(new Settings(views, mainPanel));

		mainPanel.add(menuPanel, "Menu");
		mainPanel.add(gamePanel, "Game");
		mainPanel.add(settingsPanel, "Settings");
		views.show(mainPanel, "Menu");

		this.add(mainPanel);

		//Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);


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
	// End constructor
}