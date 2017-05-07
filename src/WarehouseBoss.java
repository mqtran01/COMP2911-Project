import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;

public class WarehouseBoss extends JFrame {
		
		private static final long serialVersionUID = 1L;
		private GUIView view;
		
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
			
			view = GUIView.getInstance();
			view.add("Menu", new Menu(this));
			view.setCurrentPanel("Menu");
			view.add("Settings", new Settings(this));
			
			//Set up the content pane.
			view.setCurrentPanel("Menu");
			this.getContentPane().add(view.getCurrentPanel());
			
			//Display the window.
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		    
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
		// End constructor
		
		public void update(String panelName){
		    System.out.println("We need to change the panel to " + panelName);
		    this.getContentPane().remove(view.getCurrentPanel());
		    view.setCurrentPanel(panelName);
            this.getContentPane().add(view.getCurrentPanel());
            this.revalidate();
            this.repaint();
            this.pack();
		}
		
}
