import java.util.Scanner;

public class WarehouseBoss {
		public static void main(String[] args){
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
}
