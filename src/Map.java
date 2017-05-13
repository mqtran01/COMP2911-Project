import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
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
	
	final static int EASY = 0;
	final static int MEDIUM = 1;
	final static int HARD = 2;
	

	/**
	 * constructor
	 * @param seed
	 * @param length
	 * @param height
	 */
	public Map(int seed, int length, int height){
        this.seed = seed;
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
        
        mGen.placeObjectives(grid, rGen, PLAYER);
            
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
	
	public Map(int difficulty) {
	    // TODO not seeding right now
	    this.seed  = -1;
	    int length;
	    int height;
	    int objectives;
	    switch (difficulty) {
	        case EASY:
	            length = 2;
	            height = 3;
	            objectives = 2;
	            break;
	        case MEDIUM:
	            length = 4;
	            height = 3;
	            objectives = 3;
	            break;
	        case HARD:
	            length = 6;
	            height = 4;
	            objectives = 5;
	            break;
	        default:
	            length = 10;
                height = 10;
                objectives = 10;
	    }
	    
	    Random rGen  = new Random(System.currentTimeMillis());
        
	    boolean success = false;
	    while (!success) {
            ArrayList<int[][]> gridList =  new ArrayList<int[][]>();
            for (int i = 0; i < length * height; i++) {
                rGen.nextInt(10);
                int[][] part = createMapMatrix(rGen);
                gridList.add(part);
            }
            this.grid = mergeTemplates(gridList, length, height);
            success = addMapElements(GOAL, objectives ,rGen);
            if (!success)
                continue;
            success = addMapElements(BOX, objectives ,rGen);
            if (!success)
                continue;
            success = addMapElements(PLAYER, 1 ,rGen);
            if (!success)
                continue;
            success = connectedMap(objectives);
	    }
        
	    
	    
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
			System.out.println("moveTo was successful");
			printMap();
		}
	}
	
	public void moveRight(){
		System.out.println("right");
		System.out.println(String.format("moveTo(%d,%d,%d,%d)",player_x, player_y, player_x+1, player_y));
		if (moveTo(player_x, player_y, player_x+1, player_y)){
			player_x++;
			System.out.println("moveTo was successful");
			printMap();
		}
	}
	
	public void moveUp(){
		System.out.println("up");
		System.out.println(String.format("moveTo(%d,%d,%d,%d)",player_x, player_y, player_x, player_y+1));
		if (moveTo(player_x, player_y, player_x, player_y-1)){
			player_y--;
			System.out.println("moveTo was successful");
			printMap();
		}
	}
	
	public void moveDown(){
		System.out.println("down");
		System.out.println(String.format("moveTo(%d,%d,%d,%d)",player_x, player_y, player_x, player_y-1));
		if (moveTo(player_x, player_y, player_x, player_y+1)){
			player_y++;
			System.out.println("moveTo was successful");
			printMap();
		}
	}
	
	public void printMap(){
		System.out.println("printing internal map");
		for (int y=0;y<getHeight();y++){
			for (int x = 0;x<getLength();x++){
				System.out.print(getTile(x,y));
			}
			System.out.print("\n");
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
	
	
	private String createTemplate(int seed) {
        switch (seed) {
        case 0:
            return "111" + 
            "111" +
            "111";
        case 1:
            return "011" + 
            "111" + 
            "111";
        case 2:
            return "001" + 
            "111" + 
            "111";
        case 3:
            return "000" + 
            "111" + 
            "111";
        case 4:
            return "010" + 
            "111" + 
            "111";
        case 5:
            return "111" + 
            "101" + 
            "111";
        case 6:
            return "011" + 
            "101" + 
            "111";
        case 7:
            return "011" + 
            "101" + 
            "110";
        case 8:
            return "011" + 
            "110" + 
            "110";
        case 9:
            return "010" + 
            "111" + 
            "101";
        default: // For debugging purposes ONLY
            return "123" + 
            "456" + 
            "789";
        }
    }

    /**
     * Creates a matrix based on modular template
     * @param seed
     * @return
     */
    public int[][] createMapMatrix(Random rGen) {
        int[][] grid = new int[3][3];
        int item = rGen.nextInt(10);
        String mapString = createTemplate(item);
        int rotation = rGen.nextInt(4);
        if (rotation == 0) {
            grid = rotate0(mapString);
        } else if (rotation == 1) {
            grid = rotate90(mapString);
        } else if (rotation == 2) {
            grid = rotate180(mapString);
        } else { // 4
            grid = rotate270(mapString);
        }
        return grid;
    }

    /**
     * Creates 3x3 templates together to form a larger map
     * @param maps
     * @param length
     * @param height
     * @return
     */
    public int[][] mergeTemplates(ArrayList<int[][]> maps, int length, int height) {
        int xOffset = 1;
        int yOffset = 1;
        Iterator<int[][]> iMaps = maps.iterator();

        int[][] joined = createOuterWall(3*length + 2, 3 * height + 2);
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < height; x++) {
                int[][] part = iMaps.next();
                for (int k = 0; k < 3; k++) {
                    for (int j = 0; j < 3; j++) {
                        joined[xOffset + j][yOffset + k] = part[j][k];
                    }
                }
                yOffset += 3;
            }
            xOffset += 3;
            yOffset = 1;

        }
        return joined;
    }

    /**
     * Creates the map out of the template string without rotating
     * @param template
     * @return
     */
    private int[][] rotate0(String template) {
        int[][] grid = new int[3][3];
        int i = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                grid[x][y] = Character.getNumericValue(template.charAt(i));
                i++;
            }

        }
        return grid;
    }

    /**
     * Creates the map out of the template string rotate 90 degrees clockwise
     * @param template
     * @return
     */
    private int[][] rotate90(String template) {
        int[][] grid = new int[3][3];
        int i = 0;
        for (int x = 2; x >= 0; x--) {
            for (int y = 0; y < 3; y++) {
                grid[x][y] = Character.getNumericValue(template.charAt(i));
                i++;
            }
        }
        return grid;
    }


    /**
     * Creates the map out of the template string rotate 180 degrees clockwise
     * @param template
     * @return
     */
    private int[][] rotate180(String template) {
        int[][] grid = new int[3][3];
        int i = 0;
        for (int y = 2; y >= 0; y--) {
            for (int x = 2; x >= 0; x--) {
                grid[x][y] = Character.getNumericValue(template.charAt(i));
                i++;
            }
        }
        return grid;
    }

    /**
     * Creates the map out of the template string rotate 270 degrees clockwise
     * @param template
     * @return
     */
    private int[][] rotate270(String template) {
        int[][] grid = new int[3][3];
        int i = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 2; y >= 0; y--) {
                grid[x][y] = Character.getNumericValue(template.charAt(i));
                i++;
            }
        }
        return grid;
    }

    /**
     * Checks that at least 3 adjacent sides are free before placing
     * @param grid
     * @param xPos
     * @param yPos
     * @return
     */
    private boolean checkSides(int xPos, int yPos, int objective) {
        int i = 0;
        // Check self is empty
        if (isClear(xPos, yPos)) {
            // Box has an additional restriction cannot be placed on edge
            if (objective == Map.BOX) {
                if (xPos <= 1 || xPos >= grid.length - 2 || yPos <= 1 || yPos >= grid[0].length - 2)
                    return false;
            }
            // Checks the 4 adjacent spots
            if (isClear(xPos, yPos - 1))
                i++;
            if (isClear(xPos, yPos + 1))
                i++;
            if (isClear(xPos - 1, yPos))
                i++;
            if (isClear(xPos + 1, yPos))
                i++;
        }
        if (i >= 3)
            return true;
        return false;
    }

    /**
     * Check if the spot is empty
     * @param grid
     * @param xPos
     * @param yPos
     * @return
     */
    private boolean isClear(int xPos, int yPos) {
        // Out of bounds check
        if (xPos < 0 || xPos >= grid.length || yPos < 0 || yPos >= grid[0].length)
            return false;
        // Check if spot is empty
        if (grid[xPos][yPos] == EMPTY) 
            return true;
        return false;
    }
    
    private int[][] createOuterWall(int length, int height) {
        int[][] grid = new int[length][height];
        for (int i = 0; i < length; i++) {
            grid[i][0] = WALL;
            grid[i][height-1] = WALL;
        }
        for (int i = 1; i < height-1; i++) {
            grid[0][i] = WALL;
            grid[length-1][i] = WALL;
        }
        return grid;
    }
    
    private boolean addMapElements(int element, int count, Random rGen) {
        int size  = grid.length * grid[0].length;
        int cap = (int) Math.pow(size, 2);

        for (int i = 0; i < count; i++) {
            boolean placed = false;
            int retry = 0;
            while (!placed) {
                // Finds a random place
                int num = rGen.nextInt(size);
                int xPos = num / grid.length;
                int yPos = num % grid[0].length;
                // Special case for player
                if (element == PLAYER) {
                     if (isClear(xPos, yPos)) {
                         grid[xPos][yPos] = element;
                         this.player_x = xPos;
                         this.player_y = yPos;
                         return true;
                     } else {
                         retry++;
                     }
                } else {
                    // Box and goals
                    if (checkSides(xPos, yPos, element)) {
                        grid[xPos][yPos] = element;
                        placed = true;
                        
                    } else {
                        retry++;
                    }
                }
                if (retry > cap) {
                    return false;
                }

            }
        }
        return true;
    }
    
    private boolean connectedMap(int objectives) {
        Queue<Coordinate> frontier = new LinkedList<Coordinate>();
        int[][] wallMap = copyWalls();
        int numGoals = 0;
        int numBoxes = 0;
        frontier.add(new Coordinate(player_x, player_y));
        wallMap[player_x][player_y] = 1;
        while (!frontier.isEmpty()) {
            Coordinate curr = frontier.poll();
            int currX = curr.getX();
            int currY = curr.getY();
            int target = grid[currX][currY];
            if (target == GOAL)
                numGoals++;
            else if (target == BOX)
                numBoxes++;
            
            if (numGoals == objectives && numBoxes == objectives)
                return true;
            
            if (wallMap[currX - 1][currY] == 0) {
                frontier.add(new Coordinate(currX - 1,currY));
                wallMap[currX - 1][currY] = 1;
            }
            if (wallMap[currX + 1][currY] == 0) {
                frontier.add(new Coordinate(currX + 1,currY));
                wallMap[currX + 1][currY] = 1;
            }
            if (wallMap[currX][currY - 1] == 0) {
                frontier.add(new Coordinate(currX,currY - 1));
                wallMap[currX][currY - 1] = 1;
            }
            if (wallMap[currX][currY + 1] == 0) {
                frontier.add(new Coordinate(currX,currY + 1));
                wallMap[currX][currY + 1] = 1;
            }
        }
        return false;
    }
    
    private int[][] copyWalls() {
        int[][] wallMap = new int[grid.length][grid[0].length];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getLength(); x++) {
                if (grid[x][y] == WALL) {
                    wallMap[x][y] = -1;
                } else {
                    wallMap[x][y] = 0;
                }
            }
        }
        return wallMap;
    }
}
