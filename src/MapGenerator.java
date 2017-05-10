import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MapGenerator {
	final int WALL = 0;
	final int EMPTY = 1;
	final int PLAYER = 2;
	final int BOX = 3;
	final int GOAL = 4;
	final int GOALBOX = 5;
	final int GOALPLAYER = 6;

	/**
	 * List of 3x3 modular templates based on seeds
	 * @param seed
	 * @return
	 */
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
		// TODO add border
		int xOffset = 1;
		int yOffset = 1;
		Iterator<int[][]> iMaps = maps.iterator();

		//int[][] joined = new int[3*length][3*height];
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
	 * Places the specified objective in 2 spots
	 * @param grid
	 * @param rGen
	 * @param objective
	 * @return
	 */
	public boolean placeObjectives(int[][] grid, Random rGen, int objective) {
		// Finds the size of the matrix
		int size  = grid.length * grid[0].length;
		int cap = (int) Math.pow(size, 2);

		// Attempts to add 2 objectives
		for (int i = 0; i < 2; i++) {
			boolean placed = false;
			int retry = 0;
			while (!placed) {
				// Finds a random place
				int num = rGen.nextInt(size);
				int xPos = num / grid.length;
				int yPos = num % grid[0].length;
				// Check if valid position
				if (checkSides(grid, xPos, yPos, objective)) {
					grid[xPos][yPos] = objective;
					placed = true;
					if (objective == Map.PLAYER)
					    return true;
				} else {
					retry++;
					//System.out.println("Wrong " + retry);
				}
				if (retry > cap) {
					//System.out.println("CANNOT!");
					return false;
				}

			}
		}
		return true;

	}

	/**
	 * Checks that at least 3 adjacent sides are free before placing
	 * @param grid
	 * @param xPos
	 * @param yPos
	 * @return
	 */
	private boolean checkSides(int[][] grid, int xPos, int yPos, int objective) {
		int i = 0;
		// Check self is empty
		if (isClear(grid, xPos, yPos)) {
			// Box has an additional restriction cannot be placed on edge
			if (objective == Map.BOX) {
				if (xPos <= 0 || xPos >= grid.length - 1 || yPos <= 0 || yPos >= grid[0].length - 1)
					return false;
			}
			// Checks the 4 adjacent spots
			if (isClear(grid, xPos, yPos - 1))
				i++;
			if (isClear(grid, xPos, yPos + 1))
				i++;
			if (isClear(grid, xPos - 1, yPos))
				i++;
			if (isClear(grid, xPos + 1, yPos))
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
	private boolean isClear(int[][] grid, int xPos, int yPos) {
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
	        grid[i][0] = Map.WALL;
	        grid[i][height-1] = Map.WALL;
	    }
	    for (int i = 1; i < height-1; i++) {
	        grid[0][i] = Map.WALL;
	        grid[length-1][i] = Map.WALL;
	    }
	    return grid;
	}
}
