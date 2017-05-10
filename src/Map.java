import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Map implements Serializable {
	int[][] grid;//note: 0,0 is upper left of grid
	int seed;
	int player_x;
	int player_y;
	
	final static int WALL = 0;
	final static int EMPTY = 1;
	final static int PLAYER = 2;
	final static int BOX = 3;
	final static int GOAL = 4;
	final static int GOALBOX = 5;
	final static int GOALPLAYER = 6;
	

	/**
	 * constructor
	 * @param seed
	 * @param length
	 * @param height
	 */
	public Map(int seed, int length, int height){
        this.seed = seed;
        //grid = newMap(seed);
        MapGenerator mGen = new MapGenerator();
        // TODO change to random by seed rather than by system time
        Random rGen  = new Random(System.currentTimeMillis());
        
        ArrayList<int[][]> gridList =  new ArrayList<int[][]>();
        for (int i = 0; i < length * height; i++) {
            rGen.nextInt(10);
            int[][] part = mGen.createMapMatrix(rGen);
            gridList.add(part);
        }
        grid = mGen.mergeTemplates(gridList, length, height);
        boolean success = mGen.placeObjectives(grid, rGen, GOAL);
        if (!success) {
            // Handle it
            System.out.println("Couldn't find a spot in time for goal");
        }
        
        success = mGen.placeObjectives(grid, rGen, BOX);
        if (!success) {
            // Handle it
            System.out.println("Couldn't find a spot in time for box");
        }
            
    }
	
	//pregenerated maps, accessed using characters
	public Map(char seed){
		this.grid = new int[6][8];
		for (int x=0; x < 6; x++){
			grid[x][0]=WALL;
			grid[x][7]=WALL;
		}
		for (int y=0; y < 8; y++){
			grid[0][y]=WALL;
			grid[5][y]=WALL;
		}
		grid[1][1] = WALL;
		grid[2][1] = EMPTY;
		grid[3][1] = EMPTY;
		grid[4][1] = WALL;
		
		grid[1][2] = PLAYER;
		grid[2][2] = BOX;
		grid[3][2] = EMPTY;
		grid[4][2] = WALL;
		
		grid[1][3] = WALL;
		grid[2][3] = BOX;
		grid[3][3] = EMPTY;
		grid[4][3] = WALL;
		
		grid[1][4] = WALL;
		grid[2][4] = EMPTY;
		grid[3][4] = BOX;
		grid[4][4] = EMPTY;
		
		grid[1][5] = GOAL;
		grid[2][5] = BOX;
		grid[3][5] = EMPTY;
		grid[4][5] = EMPTY;
		
		grid[1][6] = GOAL;
		grid[2][6] = GOAL;
		grid[3][6] = GOALBOX;
		grid[4][6] = GOAL;
		
		player_x=1;
		player_y=2;

	}
	

	/**
	 * returns the value of a grid space given its x and y coordinates
	 * @param x
	 * @param y
	 * @return
	 */
	public int getTile(int x, int y){
		return grid[x][y];
	}

	/**
	 * helper function to change a grid tile to a value where the box or player is no longer there
	 * @param x
	 * @param y
	 * @return
	 */
	private int removeObject(int x, int y){
		if (grid[x][y]==PLAYER){
			return EMPTY;
		}
		else if (grid[x][y]==BOX){
			return EMPTY;
		}
		else if (grid[x][y]==GOALBOX){
			return GOAL;
		}
		else if (grid[x][y]==GOALPLAYER){
			return GOAL;
		}
		else {
			System.out.println("This should never occur, trying to remove an object from a grid position with no object");
			return -1;
		}
	}
	
	public void moveLeft(){
		System.out.println("left");
		System.out.println(String.format("moveTo(%d,%d,%d,%d)",player_x, player_y, player_x-1, player_y));
		if (moveTo(player_x, player_y, player_x-1, player_y)){
			player_x--;
		}
	}
	
	public void moveRight(){
		System.out.println("right");
		System.out.println(String.format("moveTo(%d,%d,%d,%d)",player_x, player_y, player_x+1, player_y));
		if (moveTo(player_x, player_y, player_x+1, player_y)){
			player_x++;
		}
	}
	
	public void moveUp(){
		System.out.println("up");
		System.out.println(String.format("moveTo(%d,%d,%d,%d)",player_x, player_y, player_x, player_y+1));
		if (moveTo(player_x, player_y, player_x, player_y-1)){
			player_y--;
		}
	}
	
	public void moveDown(){
		System.out.println("down");
		System.out.println(String.format("moveTo(%d,%d,%d,%d)",player_x, player_y, player_x, player_y-1));
		if (moveTo(player_x, player_y, player_x, player_y+1)){
			player_y++;
		}
	}
	
	/**
	 * attempts to move the player from one spot to another
	 * @param from_x
	 * @param from_y
	 * @param to_x
	 * @param to_y
	 * @pre (|from_x-to_x|<=1 XOR |from_y-to_y|<=1) AND grid[from_x][from_y]=player
	 * @post the grid is a valid state
	 * @return true if movement was successful, false otherwise (e.g. if there was a wall in the way)
	 */
	private boolean moveTo(int from_x, int from_y, int to_x, int to_y){
		if (grid[to_x][to_y] == WALL){//wall
			return false;//can never move into a wall
		}
		else if (grid[to_x][to_y] == EMPTY){//empty
			System.out.println("target is an empty");
			grid[to_x][to_y] = PLAYER;//set the to tile to be a player
			grid[from_x][from_y] = removeObject(from_x,from_y);
			return true;//can always move into an empty space
		}
		else if (grid[to_x][to_y] == PLAYER){//player, this should never happen
			System.out.println("error, attempted to move into a space that is occupied by the player");
			return false;
		}
		else if (grid[to_x][to_y] == BOX){//box	
			//calculate the next grid space over
			int new_x = 2*to_x-from_x;
			int new_y = 2*to_y-from_y;
			if (grid[new_x][new_y] == EMPTY || grid[new_x][new_y] == GOAL){//if the next grid space is not blocked
				//then move everything
				grid[from_x][from_y]= removeObject(from_x,from_y);//remove object from from
				//move the box out and the player in
				if (grid[to_x][to_y] == BOX){//box -> player
					grid[to_x][to_y] = PLAYER;
				}
				else{//grid[to_x][to_y] == 5  
					grid[to_x][to_y]= GOALPLAYER;//box + goal -> player + goal
				}
				//change third space
				if (grid[new_x][new_y] == EMPTY){
					grid[new_x][new_y]= BOX;//empty->box
				}
				else{//grid[to_x][to_y] == 4  
					grid[new_x][new_y]= GOALBOX;//goal -> goal+box
				}
				return true;
			}
			else{//otherwise return false;
				return false;
			}
		}
		else if (grid[to_x][to_y] == GOAL){//goal space
			grid[to_x][to_y] = GOALPLAYER;//set the to tile to be a player+goal
			grid[from_x][from_y] = removeObject(from_x,from_y);
			return true;
		}
		else if (grid[to_x][to_y] == GOALBOX){//goal + box
			int new_x = 2*to_x-from_x;
			int new_y = 2*to_y-from_y;
			if (grid[new_x][new_y] == EMPTY){//if the next grid space is not blocked
				grid[new_x][new_y] = BOX;//move box to next space
			}
			else if (grid[new_x][new_y] == GOAL){
				grid[new_x][new_y] = GOALBOX;//move box to next space
			}
			else{
				return false;//if the next space is blocked then the move fails
			}
			grid[to_x][to_y]=GOALPLAYER;//move player to this space
			grid[from_x][from_y] = removeObject(from_x,from_y);//move player off previous space
			return true;
		}
		else if (grid[to_x][to_y] == GOALPLAYER){//player + goal, this should never happen
			System.out.println("error, attempted to move into a space that is occupied by the player");
			return false;
		}
		return false;
	}
	
	/**
	 * returns true if the map is in a win state
	 * @return
	 */
	public boolean winState(){
		for (int x=0; x < grid.length; x++){
			for (int y=0; y < grid[0].length; y++){
				if (grid[x][y]==BOX){//if box not on goal then it is not a win state
					return false;
				}
			}
		}
		return true;
	}
	
	public int getLength() {
	    if (grid != null)
	        return grid.length;
	    return 0;
	}
	
	public int getHeight() {
	    if (grid != null && grid[0] != null)
	        return grid[0].length;
	    return 0;
	}
}
