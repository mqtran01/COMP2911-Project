public class Map {
	int[][] grid;
	int seed;
	
	/*grid internal representation
	 * 0: wall
	 * 1: empty space
	 * 2: player (and hence empty space once it has moved)
	 * 3: box (and hence empty space once it is moved)
	 * 4: goal (and hence space that can be moved into)
	 * 5: goal+box
	 * 6: player+goal
	 */
	
	/**
	 * constructor
	 * @param seed the seed for the map grid
	 */
	public Map(int seed){
		this.seed = seed;
		grid = newMap(seed);
		
		//generate grid here
		
		//add boxes and goals
		
		//give player initial coordinates
	}
	
	/**
	 * generates a grid given an integer seed
	 * @param seed the seed for the map
	 * @return a grid
	 */
	private int[][] newMap(int seed){
		int[][] newGrid = new int[5][5];
		return newGrid;
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
		if (grid[x][y]==2){
			return 1;
		}
		else if (grid[x][y]==3){
			return 1;
		}
		else if (grid[x][y]==5){
			return 4;
		}
		else if (grid[x][y]==6){
			return 4;
		}
		else {
			System.out.println("This should never occur, trying to remove an object from a grid position with no object");
			return -1;
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
	public boolean moveTo(int from_x, int from_y, int to_x, int to_y){
		if (grid[to_x][to_y] == 0){//wall
			return false;//can never move into a wall
		}
		else if (grid[to_x][to_y] == 1){//empty
			grid[to_x][to_y] = 2;//set the to tile to be a player
			grid[from_x][from_y] = removeObject(from_x,from_y);
			return true;//can always move into an empty space
		}
		else if (grid[to_x][to_y] == 2){//player, this should never happen
			System.out.println("error, attempted to move into a space that is occupied by the player");
			return false;
		}
		else if (grid[to_x][to_y] == 3){//box	
			//calculate the next grid space over
			int new_x = 2*to_x-from_x;
			int new_y = 2*to_y-from_y;
			if (grid[new_x][new_y] == 1 || grid[new_x][new_y] == 4){//if the next grid space is not blocked
				//then move everything
				grid[from_x][from_y]= removeObject(from_x,from_y);//remove object from from
				//move the box out and the player in
				if (grid[to_x][to_y] == 3){//box -> player
					grid[to_x][to_y] = 2;
				}
				else{//grid[to_x][to_y] == 5  
					grid[to_x][to_y]= 6;//box + goal -> player + goal
				}
				//change third space
				if (grid[new_x][new_y] == 1){
					grid[new_x][new_y]= 3;//empty->box
				}
				else{//grid[to_x][to_y] == 4  
					grid[new_x][new_y]= 5;//goal -> goal+box
				}
				return true;
			}
			else{//otherwise return false;
				return false;
			}
		}
		else if (grid[to_x][to_y] == 4){//goal space
			grid[to_x][to_y] = 6;//set the to tile to be a player+goal
			grid[from_x][from_y] = removeObject(from_x,from_y);
			return true;
		}
		else if (grid[to_x][to_y] == 5){//goal + box
			//TODO: change the state
			return true;
		}
		else if (grid[to_x][to_y] == 6){//player + goal, this should never happen
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
				if (grid[x][y]==3){//if box not on goal then it is not a win state
					return false;
				}
			}
		}
		return true;
	}
}
