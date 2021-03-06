import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * The Map Model class which contains details of all elements of a single instance of
 * the map.
 * 
 * @author Group 1 Tutorial H14A
 *
 */
public class MapModel implements Serializable {
    private int[][] grid;// note: 0,0 is upper left of grid
    private int player_x;
    private int player_y;
    private int mapLevel;

    // Map elements
    final static int WALL = 0;
    final static int EMPTY = 1;
    final static int PLAYER = 2;
    final static int BOX = 3;
    final static int GOAL = 4;
    final static int GOALBOX = 5;
    final static int GOALPLAYER = 6;

    // Difficulty settings
    final static char EASY = 'e';
    final static char MEDIUM = 'm';
    final static char HARD = 'h';
    
    // Random Level
    final static int RANDOM_LEVEL = -1;

    /**
     * Constructor is a pre-generated map based on a seed.
     * 
     * @param level as the level to play
     */
	public MapModel(int level) {
		this.mapLevel = level;
		
		// Pre-loaded Maps

		if (level == 1) {
			// EASY MAP 1
			this.grid = new int[6][8];
			for (int x = 0; x < 6; x++) {
				grid[x][0] = WALL;
				grid[x][7] = WALL;
			}
			for (int y = 0; y < 8; y++) {
				grid[0][y] = WALL;
				grid[5][y] = WALL;
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

			player_x = 1;
			player_y = 2;

		} else if (level == 2) {
			// EASY MAP 2
			this.grid = new int[6][9];
			for (int x = 0; x < 6; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[5][y] = WALL;
			}

			grid[1][1] = EMPTY;
			grid[2][1] = WALL;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = EMPTY;

			grid[1][4] = WALL;
			grid[2][4] = EMPTY;
			grid[3][4] = WALL;
			grid[4][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = EMPTY;
			grid[3][5] = WALL;
			grid[4][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = BOX;
			grid[3][6] = WALL;
			grid[4][6] = GOAL;

			grid[1][7] = EMPTY;
			grid[2][7] = EMPTY;
			grid[3][7] = WALL;
			grid[4][7] = PLAYER;

			player_x = 4;
			player_y = 7;

		} else if (level == 3) {
			// EASY MAP 3
			this.grid = new int[6][9];
			for (int x = 0; x < 6; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[5][y] = WALL;
			}

			grid[1][1] = PLAYER;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;

			grid[1][2] = WALL;
			grid[2][2] = EMPTY;
			grid[3][2] = BOX;
			grid[4][2] = EMPTY;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = WALL;

			grid[1][4] = EMPTY;
			grid[2][4] = EMPTY;
			grid[3][4] = WALL;
			grid[4][4] = GOAL;

			grid[1][5] = WALL;
			grid[2][5] = EMPTY;
			grid[3][5] = EMPTY;
			grid[4][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;

			grid[1][7] = EMPTY;
			grid[2][7] = EMPTY;
			grid[3][7] = EMPTY;
			grid[4][7] = WALL;

			player_x = 1;
			player_y = 1;

		} else if (level == 4) {
			// EASY MAP 4
			this.grid = new int[7][9];
			for (int x = 0; x < 7; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[6][y] = WALL;
			}

			grid[1][1] = WALL;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = WALL;
			grid[5][1] = WALL;

			grid[1][2] = WALL;
			grid[2][2] = EMPTY;
			grid[3][2] = EMPTY;
			grid[4][2] = BOX;
			grid[5][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = EMPTY;
			grid[5][3] = EMPTY;

			grid[1][4] = EMPTY;
			grid[2][4] = EMPTY;
			grid[3][4] = EMPTY;
			grid[4][4] = EMPTY;
			grid[5][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = EMPTY;
			grid[5][5] = WALL;

			grid[1][6] = GOAL;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;

			grid[1][7] = PLAYER;
			grid[2][7] = WALL;
			grid[3][7] = EMPTY;
			grid[4][7] = EMPTY;
			grid[5][7] = EMPTY;

			player_x = 1;
			player_y = 7;

		} else if (level == 5) {
			// EASY MAP 5
			this.grid = new int[7][9];
			for (int x = 0; x < 7; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[6][y] = WALL;
			}

			grid[1][1] = EMPTY;
			grid[2][1] = WALL;
			grid[3][1] = WALL;
			grid[4][1] = EMPTY;
			grid[5][1] = EMPTY;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = EMPTY;
			grid[4][2] = BOX;
			grid[5][2] = PLAYER;

			grid[1][3] = WALL;
			grid[2][3] = WALL;
			grid[3][3] = EMPTY;
			grid[4][3] = EMPTY;
			grid[5][3] = WALL;

			grid[1][4] = EMPTY;
			grid[2][4] = EMPTY;
			grid[3][4] = EMPTY;
			grid[4][4] = EMPTY;
			grid[5][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = WALL;
			grid[5][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;

			grid[1][7] = WALL;
			grid[2][7] = WALL;
			grid[3][7] = EMPTY;
			grid[4][7] = WALL;
			grid[5][7] = GOAL;

			player_x = 5;
			player_y = 2;

		} else if (level == 6) {
			// EASY MAP 6
			this.grid = new int[8][9];
			for (int x = 0; x < 8; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[6][y] = WALL;
			}

			grid[1][1] = PLAYER;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = WALL;
			grid[5][1] = EMPTY;
			grid[6][1] = GOAL;

			grid[1][2] = EMPTY;
			grid[2][2] = WALL;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;
			grid[6][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = EMPTY;
			grid[5][3] = WALL;
			grid[6][3] = EMPTY;

			grid[1][4] = EMPTY;
			grid[2][4] = BOX;
			grid[3][4] = EMPTY;
			grid[4][4] = EMPTY;
			grid[5][4] = WALL;
			grid[6][4] = EMPTY;

			grid[1][5] = WALL;
			grid[2][5] = EMPTY;
			grid[3][5] = WALL;
			grid[4][5] = EMPTY;
			grid[5][5] = WALL;
			grid[6][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = WALL;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = EMPTY;

			grid[1][7] = WALL;
			grid[2][7] = WALL;
			grid[3][7] = EMPTY;
			grid[4][7] = EMPTY;
			grid[5][7] = EMPTY;
			grid[6][7] = EMPTY;

			player_x = 1;
			player_y = 1;

		} else if (level == 7) {
			// EASY MAP 7
			this.grid = new int[8][9];
			for (int x = 0; x < 8; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[6][y] = WALL;
			}

			grid[1][1] = EMPTY;
			grid[2][1] = WALL;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;
			grid[5][1] = EMPTY;
			grid[6][1] = EMPTY;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;
			grid[6][2] = WALL;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = EMPTY;
			grid[4][3] = WALL;
			grid[5][3] = EMPTY;
			grid[6][3] = EMPTY;

			grid[1][4] = WALL;
			grid[2][4] = EMPTY;
			grid[3][4] = WALL;
			grid[4][4] = WALL;
			grid[5][4] = EMPTY;
			grid[6][4] = WALL;

			grid[1][5] = EMPTY;
			grid[2][5] = EMPTY;
			grid[3][5] = WALL;
			grid[4][5] = EMPTY;
			grid[5][5] = EMPTY;
			grid[6][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = BOX;
			grid[3][6] = EMPTY;
			grid[4][6] = WALL;
			grid[5][6] = GOAL;
			grid[6][6] = EMPTY;

			grid[1][7] = EMPTY;
			grid[2][7] = EMPTY;
			grid[3][7] = WALL;
			grid[4][7] = WALL;
			grid[5][7] = EMPTY;
			grid[6][7] = PLAYER;

			player_x = 6;
			player_y = 7;

		} else if (level == 8) {
			// EASY MAP 8
			this.grid = new int[8][9];
			for (int x = 0; x < 8; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[6][y] = WALL;
			}

			grid[1][1] = WALL;
			grid[2][1] = WALL;
			grid[3][1] = EMPTY;
			grid[4][1] = PLAYER;
			grid[5][1] = WALL;
			grid[6][1] = WALL;

			grid[1][2] = WALL;
			grid[2][2] = EMPTY;
			grid[3][2] = EMPTY;
			grid[4][2] = BOX;
			grid[5][2] = EMPTY;
			grid[6][2] = WALL;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = EMPTY;
			grid[5][3] = EMPTY;
			grid[6][3] = WALL;

			grid[1][4] = EMPTY;
			grid[2][4] = EMPTY;
			grid[3][4] = EMPTY;
			grid[4][4] = EMPTY;
			grid[5][4] = EMPTY;
			grid[6][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = EMPTY;
			grid[3][5] = EMPTY;
			grid[4][5] = WALL;
			grid[5][5] = EMPTY;
			grid[6][5] = EMPTY;

			grid[1][6] = WALL;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = EMPTY;

			grid[1][7] = WALL;
			grid[2][7] = WALL;
			grid[3][7] = GOAL;
			grid[4][7] = EMPTY;
			grid[5][7] = WALL;
			grid[6][7] = WALL;

			player_x = 4;
			player_y = 1;
		} else if (level == 9) {
			// EASY MAP 9
			this.grid = new int[8][9];
			for (int x = 0; x < 8; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[6][y] = WALL;
			}

			grid[1][1] = WALL;
			grid[2][1] = WALL;
			grid[3][1] = EMPTY;
			grid[4][1] = WALL;
			grid[5][1] = EMPTY;
			grid[6][1] = EMPTY;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;
			grid[6][2] = WALL;

			grid[1][3] = EMPTY;
			grid[2][3] = WALL;
			grid[3][3] = EMPTY;
			grid[4][3] = WALL;
			grid[5][3] = EMPTY;
			grid[6][3] = WALL;

			grid[1][4] = PLAYER;
			grid[2][4] = BOX;
			grid[3][4] = EMPTY;
			grid[4][4] = WALL;
			grid[5][4] = EMPTY;
			grid[6][4] = WALL;

			grid[1][5] = EMPTY;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = WALL;
			grid[5][5] = EMPTY;
			grid[6][5] = WALL;

			grid[1][6] = EMPTY;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = EMPTY;

			grid[1][7] = EMPTY;
			grid[2][7] = WALL;
			grid[3][7] = WALL;
			grid[4][7] = EMPTY;
			grid[5][7] = EMPTY;
			grid[6][7] = GOAL;

			player_x = 1;
			player_y = 4;

		} else if (level == 10) {
			// EASY MAP 10
			this.grid = new int[8][9];
			for (int x = 0; x < 8; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[6][y] = WALL;
			}

			grid[1][1] = WALL;
			grid[2][1] = EMPTY;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = EMPTY;
			grid[6][1] = EMPTY;

			grid[1][2] = EMPTY;
			grid[2][2] = WALL;
			grid[3][2] = EMPTY;
			grid[4][2] = WALL;
			grid[5][2] = WALL;
			grid[6][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = EMPTY;
			grid[4][3] = WALL;
			grid[5][3] = EMPTY;
			grid[6][3] = WALL;

			grid[1][4] = EMPTY;
			grid[2][4] = WALL;
			grid[3][4] = EMPTY;
			grid[4][4] = WALL;
			grid[5][4] = EMPTY;
			grid[6][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = EMPTY;
			grid[3][5] = EMPTY;
			grid[4][5] = EMPTY;
			grid[5][5] = BOX;
			grid[6][5] = GOAL;

			grid[1][6] = EMPTY;
			grid[2][6] = WALL;
			grid[3][6] = EMPTY;
			grid[4][6] = WALL;
			grid[5][6] = BOX;
			grid[6][6] = GOAL;

			grid[1][7] = EMPTY;
			grid[2][7] = EMPTY;
			grid[3][7] = EMPTY;
			grid[4][7] = WALL;
			grid[5][7] = PLAYER;
			grid[6][7] = EMPTY;

			player_x = 5;
			player_y = 7;

		} else if (level == 11) {
			// MEDIUM MAP 1
			this.grid = new int[14][12];
			for (int x = 0; x < 14; x++) {
				grid[x][0] = WALL;
				grid[x][11] = WALL;
			}
			for (int y = 0; y < 12; y++) {
				grid[0][y] = WALL;
				grid[13][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = WALL;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = WALL;
			grid[7][1] = WALL;
			grid[8][1] = EMPTY;
			grid[9][1] = WALL;
			grid[10][1] = WALL;
			grid[11][1] = WALL;
			grid[12][1] = WALL;

			grid[1][2] = WALL;
			grid[2][2] = WALL;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;
			grid[6][2] = EMPTY;
			grid[7][2] = WALL;
			grid[8][2] = EMPTY;
			grid[9][2] = WALL;
			grid[10][2] = EMPTY;
			grid[11][2] = EMPTY;
			grid[12][2] = WALL;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = EMPTY;
			grid[4][3] = EMPTY;
			grid[5][3] = EMPTY;
			grid[6][3] = EMPTY;
			grid[7][3] = WALL;
			grid[8][3] = EMPTY;
			grid[9][3] = WALL;
			grid[10][3] = EMPTY;
			grid[11][3] = EMPTY;
			grid[12][3] = WALL;

			grid[1][4] = WALL;
			grid[2][4] = EMPTY;
			grid[3][4] = EMPTY;
			grid[4][4] = WALL;
			grid[5][4] = WALL;
			grid[6][4] = EMPTY;
			grid[7][4] = EMPTY;
			grid[8][4] = EMPTY;
			grid[9][4] = EMPTY;
			grid[10][4] = EMPTY;
			grid[11][4] = EMPTY;
			grid[12][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = EMPTY;
			grid[5][5] = EMPTY;
			grid[6][5] = EMPTY;
			grid[7][5] = EMPTY;
			grid[8][5] = EMPTY;
			grid[9][5] = WALL;
			grid[10][5] = EMPTY;
			grid[11][5] = EMPTY;
			grid[12][5] = WALL;

			grid[1][6] = WALL;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = WALL;
			grid[6][6] = EMPTY;
			grid[7][6] = EMPTY;
			grid[8][6] = EMPTY;
			grid[9][6] = WALL;
			grid[10][6] = GOAL;
			grid[11][6] = WALL;
			grid[12][6] = WALL;

			grid[1][7] = WALL;
			grid[2][7] = WALL;
			grid[3][7] = BOX;
			grid[4][7] = WALL;
			grid[5][7] = WALL;
			grid[6][7] = WALL;
			grid[7][7] = WALL;
			grid[8][7] = WALL;
			grid[9][7] = WALL;
			grid[10][7] = GOAL;
			grid[11][7] = WALL;
			grid[12][7] = WALL;

			grid[1][8] = WALL;
			grid[2][8] = EMPTY;
			grid[3][8] = EMPTY;
			grid[4][8] = EMPTY;
			grid[5][8] = BOX;
			grid[6][8] = EMPTY;
			grid[7][8] = EMPTY;
			grid[8][8] = PLAYER;
			grid[9][8] = EMPTY;
			grid[10][8] = EMPTY;
			grid[11][8] = EMPTY;
			grid[12][8] = WALL;

			grid[1][9] = WALL;
			grid[2][9] = EMPTY;
			grid[3][9] = EMPTY;
			grid[4][9] = EMPTY;
			grid[5][9] = WALL;
			grid[6][9] = WALL;
			grid[7][9] = WALL;
			grid[8][9] = WALL;
			grid[9][9] = EMPTY;
			grid[10][9] = EMPTY;
			grid[11][9] = EMPTY;
			grid[12][9] = WALL;

			grid[1][10] = WALL;
			grid[2][10] = WALL;
			grid[3][10] = WALL;
			grid[4][10] = WALL;
			grid[5][10] = WALL;
			grid[6][10] = EMPTY;
			grid[7][10] = EMPTY;
			grid[8][10] = WALL;
			grid[9][10] = WALL;
			grid[10][10] = WALL;
			grid[11][10] = WALL;
			grid[12][10] = WALL;

			player_x = 8;
			player_y = 8;

		} else if (level == 12) {
			// MEDIUM MAP 2
			this.grid = new int[13][13];
			for (int x = 0; x < 13; x++) {
				grid[x][0] = WALL;
				grid[x][12] = WALL;
			}
			for (int y = 0; y < 13; y++) {
				grid[0][y] = WALL;
				grid[12][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = WALL;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = EMPTY;
			grid[7][1] = WALL;
			grid[8][1] = WALL;
			grid[9][1] = WALL;
			grid[10][1] = WALL;
			grid[11][1] = EMPTY;

			grid[1][2] = WALL;
			grid[2][2] = WALL;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;
			grid[5][2] = WALL;
			grid[6][2] = WALL;
			grid[7][2] = WALL;
			grid[8][2] = EMPTY;
			grid[9][2] = EMPTY;
			grid[10][2] = WALL;
			grid[11][2] = WALL;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = EMPTY;
			grid[4][3] = EMPTY;
			grid[5][3] = EMPTY;
			grid[6][3] = PLAYER;
			grid[7][3] = EMPTY;
			grid[8][3] = EMPTY;
			grid[9][3] = EMPTY;
			grid[10][3] = EMPTY;
			grid[11][3] = WALL;

			grid[1][4] = WALL;
			grid[2][4] = EMPTY;
			grid[3][4] = EMPTY;
			grid[4][4] = WALL;
			grid[5][4] = WALL;
			grid[6][4] = WALL;
			grid[7][4] = WALL;
			grid[8][4] = WALL;
			grid[9][4] = EMPTY;
			grid[10][4] = EMPTY;
			grid[11][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = WALL;
			grid[5][5] = EMPTY;
			grid[6][5] = GOAL;
			grid[7][5] = EMPTY;
			grid[8][5] = WALL;
			grid[9][5] = EMPTY;
			grid[10][5] = WALL;
			grid[11][5] = WALL;

			grid[1][6] = WALL;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = WALL;
			grid[5][6] = BOX;
			grid[6][6] = GOAL;
			grid[7][6] = BOX;
			grid[8][6] = WALL;
			grid[9][6] = EMPTY;
			grid[10][6] = EMPTY;
			grid[11][6] = WALL;

			grid[1][7] = WALL;
			grid[2][7] = EMPTY;
			grid[3][7] = EMPTY;
			grid[4][7] = EMPTY;
			grid[5][7] = EMPTY;
			grid[6][7] = EMPTY;
			grid[7][7] = EMPTY;
			grid[8][7] = EMPTY;
			grid[9][7] = EMPTY;
			grid[10][7] = EMPTY;
			grid[11][7] = WALL;

			grid[1][8] = WALL;
			grid[2][8] = EMPTY;
			grid[3][8] = EMPTY;
			grid[4][8] = EMPTY;
			grid[5][8] = EMPTY;
			grid[6][8] = EMPTY;
			grid[7][8] = EMPTY;
			grid[8][8] = WALL;
			grid[9][8] = EMPTY;
			grid[10][8] = EMPTY;
			grid[11][8] = WALL;

			grid[1][9] = WALL;
			grid[2][9] = WALL;
			grid[3][9] = WALL;
			grid[4][9] = WALL;
			grid[5][9] = BOX;
			grid[6][9] = GOAL;
			grid[7][9] = BOX;
			grid[8][9] = WALL;
			grid[9][9] = EMPTY;
			grid[10][9] = EMPTY;
			grid[11][9] = WALL;

			grid[1][10] = EMPTY;
			grid[2][10] = EMPTY;
			grid[3][10] = EMPTY;
			grid[4][10] = WALL;
			grid[5][10] = EMPTY;
			grid[6][10] = GOAL;
			grid[7][10] = EMPTY;
			grid[8][10] = WALL;
			grid[9][10] = WALL;
			grid[10][10] = WALL;
			grid[11][10] = WALL;

			grid[1][11] = EMPTY;
			grid[2][11] = EMPTY;
			grid[3][11] = EMPTY;
			grid[4][11] = WALL;
			grid[5][11] = WALL;
			grid[6][11] = WALL;
			grid[7][11] = WALL;
			grid[8][11] = WALL;
			grid[9][11] = EMPTY;
			grid[10][11] = EMPTY;
			grid[11][11] = EMPTY;

			player_x = 6;
			player_y = 3;

		} else if (level == 13) {
			// MEDIUM MAP 3
			this.grid = new int[13][8];
			for (int x = 0; x < 13; x++) {
				grid[x][0] = WALL;
				grid[x][7] = WALL;
			}
			for (int y = 0; y < 8; y++) {
				grid[0][y] = WALL;
				grid[12][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = WALL;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = WALL;
			grid[7][1] = WALL;
			grid[8][1] = WALL;
			grid[9][1] = WALL;
			grid[10][1] = EMPTY;
			grid[11][1] = EMPTY;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = EMPTY;
			grid[4][2] = BOX;
			grid[5][2] = EMPTY;
			grid[6][2] = EMPTY;
			grid[7][2] = EMPTY;
			grid[8][2] = GOAL;
			grid[9][2] = GOAL;
			grid[10][2] = GOAL;
			grid[11][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = BOX;
			grid[4][3] = EMPTY;
			grid[5][3] = BOX;
			grid[6][3] = EMPTY;
			grid[7][3] = EMPTY;
			grid[8][3] = EMPTY;
			grid[9][3] = WALL;
			grid[10][3] = EMPTY;
			grid[11][3] = EMPTY;

			grid[1][4] = EMPTY;
			grid[2][4] = WALL;
			grid[3][4] = WALL;
			grid[4][4] = EMPTY;
			grid[5][4] = WALL;
			grid[6][4] = WALL;
			grid[7][4] = WALL;
			grid[8][4] = WALL;
			grid[9][4] = EMPTY;
			grid[10][4] = GOAL;
			grid[11][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = BOX;
			grid[5][5] = EMPTY;
			grid[6][5] = EMPTY;
			grid[7][5] = PLAYER;
			grid[8][5] = EMPTY;
			grid[9][5] = EMPTY;
			grid[10][5] = WALL;
			grid[11][5] = WALL;

			grid[1][6] = EMPTY;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = WALL;
			grid[7][6] = WALL;
			grid[8][6] = WALL;
			grid[9][6] = WALL;
			grid[10][6] = WALL;
			grid[11][6] = WALL;

			player_x = 7;
			player_y = 5;

		} else if (level == 14) {
			// MEDIUM MAP 4
			this.grid = new int[8][8];
			for (int x = 0; x < 8; x++) {
				grid[x][0] = WALL;
				grid[x][7] = WALL;
			}
			for (int y = 0; y < 8; y++) {
				grid[0][y] = WALL;
				grid[7][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;
			grid[5][1] = WALL;
			grid[6][1] = WALL;

			grid[1][2] = WALL;
			grid[2][2] = EMPTY;
			grid[3][2] = BOX;
			grid[4][2] = PLAYER;
			grid[5][2] = WALL;
			grid[6][2] = WALL;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = EMPTY;
			grid[4][3] = EMPTY;
			grid[5][3] = WALL;
			grid[6][3] = WALL;

			grid[1][4] = WALL;
			grid[2][4] = WALL;
			grid[3][4] = WALL;
			grid[4][4] = EMPTY;
			grid[5][4] = WALL;
			grid[6][4] = WALL;

			grid[1][5] = EMPTY;
			grid[2][5] = EMPTY;
			grid[3][5] = BOX;
			grid[4][5] = EMPTY;
			grid[5][5] = BOX;
			grid[6][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = GOAL;
			grid[3][6] = GOAL;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = GOAL;

			player_x = 4;
			player_y = 2;

		} else if (level == 15) {
			// MEDIUM MAP 5
			this.grid = new int[10][9];
			for (int x = 0; x < 10; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[9][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = GOAL;
			grid[3][1] = EMPTY;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = WALL;
			grid[7][1] = WALL;
			grid[8][1] = WALL;

			grid[1][2] = GOAL;
			grid[2][2] = GOAL;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;
			grid[5][2] = WALL;
			grid[6][2] = WALL;
			grid[7][2] = WALL;
			grid[8][2] = WALL;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = EMPTY;
			grid[4][3] = EMPTY;
			grid[5][3] = WALL;
			grid[6][3] = WALL;
			grid[7][3] = WALL;
			grid[8][3] = WALL;

			grid[1][4] = WALL;
			grid[2][4] = WALL;
			grid[3][4] = EMPTY;
			grid[4][4] = EMPTY;
			grid[5][4] = EMPTY;
			grid[6][4] = EMPTY;
			grid[7][4] = WALL;
			grid[8][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = PLAYER;
			grid[5][5] = WALL;
			grid[6][5] = EMPTY;
			grid[7][5] = EMPTY;
			grid[8][5] = WALL;

			grid[1][6] = WALL;
			grid[2][6] = WALL;
			grid[3][6] = WALL;
			grid[4][6] = EMPTY;
			grid[5][6] = BOX;
			grid[6][6] = BOX;
			grid[7][6] = BOX;
			grid[8][6] = EMPTY;

			grid[1][7] = WALL;
			grid[2][7] = WALL;
			grid[3][7] = WALL;
			grid[4][7] = EMPTY;
			grid[5][7] = EMPTY;
			grid[6][7] = EMPTY;
			grid[7][7] = EMPTY;
			grid[8][7] = EMPTY;

			player_x = 4;
			player_y = 5;

		} else if (level == 16) {
			// MEDIUM MAP 6
			this.grid = new int[10][12];
			for (int x = 0; x < 10; x++) {
				grid[x][0] = WALL;
				grid[x][11] = WALL;
			}
			for (int y = 0; y < 12; y++) {
				grid[0][y] = WALL;
				grid[9][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = WALL;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = WALL;
			grid[7][1] = WALL;
			grid[8][1] = WALL;

			grid[1][2] = WALL;
			grid[2][2] = WALL;
			grid[3][2] = EMPTY;
			grid[4][2] = WALL;
			grid[5][2] = GOAL;
			grid[6][2] = EMPTY;
			grid[7][2] = GOAL;
			grid[8][2] = WALL;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = EMPTY;
			grid[4][3] = BOX;
			grid[5][3] = EMPTY;
			grid[6][3] = GOAL;
			grid[7][3] = EMPTY;
			grid[8][3] = WALL;

			grid[1][4] = WALL;
			grid[2][4] = EMPTY;
			grid[3][4] = BOX;
			grid[4][4] = WALL;
			grid[5][4] = GOAL;
			grid[6][4] = BOX;
			grid[7][4] = GOAL;
			grid[8][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = EMPTY;
			grid[3][5] = EMPTY;
			grid[4][5] = BOX;
			grid[5][5] = EMPTY;
			grid[6][5] = GOAL;
			grid[7][5] = EMPTY;
			grid[8][5] = WALL;

			grid[1][6] = WALL;
			grid[2][6] = WALL;
			grid[3][6] = EMPTY;
			grid[4][6] = WALL;
			grid[5][6] = BOX;
			grid[6][6] = EMPTY;
			grid[7][6] = WALL;
			grid[8][6] = WALL;

			grid[1][7] = WALL;
			grid[2][7] = EMPTY;
			grid[3][7] = BOX;
			grid[4][7] = EMPTY;
			grid[5][7] = EMPTY;
			grid[6][7] = WALL;
			grid[7][7] = WALL;
			grid[8][7] = EMPTY;

			grid[1][8] = WALL;
			grid[2][8] = EMPTY;
			grid[3][8] = EMPTY;
			grid[4][8] = EMPTY;
			grid[5][8] = WALL;
			grid[6][8] = WALL;
			grid[7][8] = EMPTY;
			grid[8][8] = EMPTY;

			grid[1][9] = WALL;
			grid[2][9] = PLAYER;
			grid[3][9] = EMPTY;
			grid[4][9] = WALL;
			grid[5][9] = WALL;
			grid[6][9] = EMPTY;
			grid[7][9] = EMPTY;
			grid[8][9] = EMPTY;

			grid[1][10] = WALL;
			grid[2][10] = WALL;
			grid[3][10] = WALL;
			grid[4][10] = WALL;
			grid[5][10] = EMPTY;
			grid[6][10] = EMPTY;
			grid[7][10] = EMPTY;
			grid[8][10] = EMPTY;

			player_x = 2;
			player_y = 9;

		} else if (level == 17) {
			// MEDIUM MAP 7
			this.grid = new int[9][12];
			for (int x = 0; x < 9; x++) {
				grid[x][0] = WALL;
				grid[x][11] = WALL;
			}
			for (int y = 0; y < 12; y++) {
				grid[0][y] = WALL;
				grid[8][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = WALL;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = WALL;
			grid[7][1] = EMPTY;

			grid[1][2] = WALL;
			grid[2][2] = WALL;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;
			grid[6][2] = WALL;
			grid[7][2] = EMPTY;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = BOX;
			grid[4][3] = WALL;
			grid[5][3] = EMPTY;
			grid[6][3] = WALL;
			grid[7][3] = WALL;

			grid[1][4] = WALL;
			grid[2][4] = EMPTY;
			grid[3][4] = EMPTY;
			grid[4][4] = GOAL;
			grid[5][4] = EMPTY;
			grid[6][4] = PLAYER;
			grid[7][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = WALL;
			grid[3][5] = GOAL;
			grid[4][5] = GOAL;
			grid[5][5] = GOAL;
			grid[6][5] = EMPTY;
			grid[7][5] = WALL;

			grid[1][6] = WALL;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = BOX;
			grid[5][6] = EMPTY;
			grid[6][6] = WALL;
			grid[7][6] = WALL;

			grid[1][7] = WALL;
			grid[2][7] = EMPTY;
			grid[3][7] = BOX;
			grid[4][7] = WALL;
			grid[5][7] = WALL;
			grid[6][7] = WALL;
			grid[7][7] = EMPTY;

			grid[1][8] = WALL;
			grid[2][8] = EMPTY;
			grid[3][8] = EMPTY;
			grid[4][8] = BOX;
			grid[5][8] = EMPTY;
			grid[6][8] = WALL;
			grid[7][8] = EMPTY;

			grid[1][9] = WALL;
			grid[2][9] = WALL;
			grid[3][9] = EMPTY;
			grid[4][9] = EMPTY;
			grid[5][9] = EMPTY;
			grid[6][9] = WALL;
			grid[7][9] = EMPTY;

			grid[1][10] = EMPTY;
			grid[2][10] = WALL;
			grid[3][10] = WALL;
			grid[4][10] = WALL;
			grid[5][10] = WALL;
			grid[6][10] = WALL;
			grid[7][10] = EMPTY;

			player_x = 6;
			player_y = 4;

		} else if (level == 18) {
			// MEDIUM MAP 8
			this.grid = new int[12][8];
			for (int x = 0; x < 12; x++) {
				grid[x][0] = WALL;
				grid[x][7] = WALL;
			}
			for (int y = 0; y < 8; y++) {
				grid[0][y] = WALL;
				grid[11][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = WALL;
			grid[3][1] = GOAL;
			grid[4][1] = WALL;
			grid[5][1] = EMPTY;
			grid[6][1] = EMPTY;
			grid[7][1] = GOAL;
			grid[8][1] = GOAL;
			grid[9][1] = GOAL;
			grid[10][1] = GOAL;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = BOX;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;
			grid[6][2] = EMPTY;
			grid[7][2] = EMPTY;
			grid[8][2] = EMPTY;
			grid[9][2] = EMPTY;
			grid[10][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = EMPTY;
			grid[4][3] = WALL;
			grid[5][3] = WALL;
			grid[6][3] = WALL;
			grid[7][3] = WALL;
			grid[8][3] = EMPTY;
			grid[9][3] = WALL;
			grid[10][3] = EMPTY;

			grid[1][4] = WALL;
			grid[2][4] = WALL;
			grid[3][4] = EMPTY;
			grid[4][4] = EMPTY;
			grid[5][4] = EMPTY;
			grid[6][4] = EMPTY;
			grid[7][4] = BOX;
			grid[8][4] = BOX;
			grid[9][4] = EMPTY;
			grid[10][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = BOX;
			grid[5][5] = WALL;
			grid[6][5] = EMPTY;
			grid[7][5] = EMPTY;
			grid[8][5] = BOX;
			grid[9][5] = PLAYER;
			grid[10][5] = WALL;

			grid[1][6] = EMPTY;
			grid[2][6] = WALL;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = WALL;
			grid[6][6] = WALL;
			grid[7][6] = WALL;
			grid[8][6] = EMPTY;
			grid[9][6] = EMPTY;
			grid[10][6] = WALL;

			player_x = 9;
			player_y = 5;

		} else if (level == 19) {
			// MEDIUM MAP 9
			this.grid = new int[11][11];
			for (int x = 0; x < 11; x++) {
				grid[x][0] = WALL;
				grid[x][10] = WALL;
			}
			for (int y = 0; y < 11; y++) {
				grid[0][y] = WALL;
				grid[10][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = WALL;
			grid[5][1] = GOAL;
			grid[6][1] = WALL;
			grid[7][1] = WALL;
			grid[8][1] = WALL;
			grid[9][1] = WALL;

			grid[1][2] = WALL;
			grid[2][2] = EMPTY;
			grid[3][2] = BOX;
			grid[4][2] = EMPTY;
			grid[5][2] = BOX;
			grid[6][2] = EMPTY;
			grid[7][2] = BOX;
			grid[8][2] = EMPTY;
			grid[9][2] = WALL;

			grid[1][3] = WALL;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = WALL;
			grid[5][3] = GOAL;
			grid[6][3] = WALL;
			grid[7][3] = WALL;
			grid[8][3] = EMPTY;
			grid[9][3] = WALL;
			grid[10][3] = EMPTY;

			grid[1][4] = WALL;
			grid[2][4] = EMPTY;
			grid[3][4] = WALL;
			grid[4][4] = EMPTY;
			grid[5][4] = EMPTY;
			grid[6][4] = EMPTY;
			grid[7][4] = WALL;
			grid[8][4] = EMPTY;
			grid[9][4] = WALL;

			grid[1][5] = GOAL;
			grid[2][5] = EMPTY;
			grid[3][5] = GOAL;
			grid[4][5] = EMPTY;
			grid[5][5] = PLAYER;
			grid[6][5] = BOX;
			grid[7][5] = GOAL;
			grid[8][5] = EMPTY;
			grid[9][5] = GOAL;

			grid[1][6] = WALL;
			grid[2][6] = BOX;
			grid[3][6] = WALL;
			grid[4][6] = WALL;
			grid[5][6] = EMPTY;
			grid[6][6] = WALL;
			grid[7][6] = WALL;
			grid[8][6] = EMPTY;
			grid[9][6] = WALL;

			grid[1][7] = EMPTY;
			grid[2][7] = BOX;
			grid[3][7] = EMPTY;
			grid[4][7] = BOX;
			grid[5][7] = GOALBOX;
			grid[6][7] = WALL;
			grid[7][7] = WALL;
			grid[8][7] = EMPTY;
			grid[9][7] = WALL;

			grid[1][8] = EMPTY;
			grid[2][8] = EMPTY;
			grid[3][8] = EMPTY;
			grid[4][8] = EMPTY;
			grid[5][8] = EMPTY;
			grid[6][8] = EMPTY;
			grid[7][8] = EMPTY;
			grid[8][8] = EMPTY;
			grid[9][8] = WALL;

			grid[1][9] = WALL;
			grid[2][9] = WALL;
			grid[3][9] = WALL;
			grid[4][9] = WALL;
			grid[5][9] = GOAL;
			grid[6][9] = WALL;
			grid[7][9] = WALL;
			grid[8][9] = WALL;
			grid[9][9] = WALL;

			player_x = 5;
			player_y = 5;

		} else if (level == 20) {
			// MEDIUM MAP 10
			this.grid = new int[6][8];
			for (int x = 0; x < 6; x++) {
				grid[x][0] = WALL;
				grid[x][7] = WALL;
			}
			for (int y = 0; y < 8; y++) {
				grid[0][y] = WALL;
				grid[5][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;

			grid[1][2] = GOAL;
			grid[2][2] = BOX;
			grid[3][2] = GOAL;
			grid[4][2] = EMPTY;

			grid[1][3] = BOX;
			grid[2][3] = GOALPLAYER;
			grid[3][3] = BOX;
			grid[4][3] = EMPTY;

			grid[1][4] = GOAL;
			grid[2][4] = BOX;
			grid[3][4] = GOALBOX;
			grid[4][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = EMPTY;
			grid[3][5] = EMPTY;
			grid[4][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = EMPTY;
			grid[3][6] = WALL;
			grid[4][6] = WALL;

			player_x = 2;
			player_y = 3;

		} else if (level == 21) {
			// HARD MAP 1
			this.grid = new int[11][10];
			for (int x = 0; x < 10; x++) {
				grid[x][0] = WALL;
				grid[x][9] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[10][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;
			grid[5][1] = EMPTY;
			grid[6][1] = EMPTY;
			grid[7][1] = EMPTY;
			grid[8][1] = EMPTY;
			grid[9][1] = WALL;

			grid[1][2] = WALL;
			grid[2][2] = BOX;
			grid[3][2] = WALL;
			grid[4][2] = WALL;
			grid[5][2] = WALL;
			grid[6][2] = WALL;
			grid[7][2] = WALL;
			grid[8][2] = EMPTY;
			grid[9][2] = WALL;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = EMPTY;
			grid[5][3] = EMPTY;
			grid[6][3] = EMPTY;
			grid[7][3] = WALL;
			grid[8][3] = GOAL;
			grid[9][3] = WALL;

			grid[1][4] = EMPTY;
			grid[2][4] = EMPTY;
			grid[3][4] = EMPTY;
			grid[4][4] = EMPTY;
			grid[5][4] = PLAYER;
			grid[6][4] = EMPTY;
			grid[7][4] = WALL;
			grid[8][4] = GOAL;
			grid[9][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = BOX;
			grid[3][5] = WALL;
			grid[4][5] = WALL;
			grid[5][5] = EMPTY;
			grid[6][5] = WALL;
			grid[7][5] = WALL;
			grid[8][5] = GOAL;
			grid[9][5] = WALL;

			grid[1][6] = WALL;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = EMPTY;
			grid[7][6] = EMPTY;
			grid[8][6] = EMPTY;
			grid[9][6] = EMPTY;

			grid[1][7] = WALL;
			grid[2][7] = BOX;
			grid[3][7] = WALL;
			grid[4][7] = WALL;
			grid[5][7] = WALL;
			grid[6][7] = WALL;
			grid[7][7] = EMPTY;
			grid[8][7] = EMPTY;
			grid[9][7] = EMPTY;

			grid[1][8] = WALL;
			grid[2][8] = EMPTY;
			grid[3][8] = EMPTY;
			grid[4][8] = EMPTY;
			grid[5][8] = EMPTY;
			grid[6][8] = EMPTY;
			grid[7][8] = EMPTY;
			grid[8][8] = WALL;
			grid[9][8] = WALL;

			player_x = 5;
			player_y = 4;
		} else if (level == 22) {
			// HARD MAP 2
			this.grid = new int[19][16];
			for (int x = 0; x < 19; x++) {
				grid[x][0] = WALL;
				grid[x][15] = WALL;
			}
			for (int y = 0; y < 16; y++) {
				grid[0][y] = WALL;
				grid[18][y] = WALL;
			}
			grid[1][1] = PLAYER;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;
			grid[5][1] = WALL;
			grid[6][1] = GOAL;
			grid[7][1] = EMPTY;
			grid[8][1] = EMPTY;
			grid[9][1] = WALL;
			grid[10][1] = GOAL;
			grid[11][1] = WALL;
			grid[12][1] = WALL;
			grid[13][1] = WALL;
			grid[14][1] = WALL;
			grid[15][1] = WALL;
			grid[16][1] = WALL;
			grid[17][1] = WALL;

			grid[1][2] = EMPTY;
			grid[2][2] = WALL;
			grid[3][2] = WALL;
			grid[4][2] = EMPTY;
			grid[5][2] = BOX;
			grid[6][2] = EMPTY;
			grid[7][2] = WALL;
			grid[8][2] = EMPTY;
			grid[9][2] = WALL;
			grid[10][2] = GOAL;
			grid[11][2] = EMPTY;
			grid[12][2] = EMPTY;
			grid[13][2] = EMPTY;
			grid[14][2] = EMPTY;
			grid[15][2] = EMPTY;
			grid[16][2] = EMPTY;
			grid[17][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = WALL;
			grid[3][3] = WALL;
			grid[4][3] = EMPTY;
			grid[5][3] = BOX;
			grid[6][3] = EMPTY;
			grid[7][3] = WALL;
			grid[8][3] = EMPTY;
			grid[9][3] = WALL;
			grid[10][3] = EMPTY;
			grid[11][3] = WALL;
			grid[12][3] = EMPTY;
			grid[13][3] = BOX;
			grid[14][3] = EMPTY;
			grid[15][3] = WALL;
			grid[16][3] = BOX;
			grid[17][3] = EMPTY;

			grid[1][4] = EMPTY;
			grid[2][4] = EMPTY;
			grid[3][4] = EMPTY;
			grid[4][4] = EMPTY;
			grid[5][4] = WALL;
			grid[6][4] = GOAL;
			grid[7][4] = EMPTY;
			grid[8][4] = EMPTY;
			grid[9][4] = WALL;
			grid[10][4] = GOAL;
			grid[11][4] = WALL;
			grid[12][4] = EMPTY;
			grid[13][4] = EMPTY;
			grid[14][4] = BOX;
			grid[15][4] = WALL;
			grid[16][4] = EMPTY;
			grid[17][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = WALL;
			grid[3][5] = WALL;
			grid[4][5] = WALL;
			grid[5][5] = WALL;
			grid[6][5] = WALL;
			grid[7][5] = WALL;
			grid[8][5] = BOX;
			grid[9][5] = WALL;
			grid[10][5] = EMPTY;
			grid[11][5] = WALL;
			grid[12][5] = EMPTY;
			grid[13][5] = BOX;
			grid[14][5] = EMPTY;
			grid[15][5] = WALL;
			grid[16][5] = BOX;
			grid[17][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = EMPTY;
			grid[7][6] = EMPTY;
			grid[8][6] = EMPTY;
			grid[9][6] = WALL;
			grid[10][6] = GOAL;
			grid[11][6] = WALL;
			grid[12][6] = EMPTY;
			grid[13][6] = BOX;
			grid[14][6] = EMPTY;
			grid[15][6] = WALL;
			grid[16][6] = EMPTY;
			grid[17][6] = EMPTY;

			grid[1][7] = WALL;
			grid[2][7] = EMPTY;
			grid[3][7] = WALL;
			grid[4][7] = EMPTY;
			grid[5][7] = WALL;
			grid[6][7] = WALL;
			grid[7][7] = WALL;
			grid[8][7] = EMPTY;
			grid[9][7] = WALL;
			grid[10][7] = EMPTY;
			grid[11][7] = WALL;
			grid[12][7] = EMPTY;
			grid[13][7] = BOX;
			grid[14][7] = EMPTY;
			grid[15][7] = WALL;
			grid[16][7] = BOX;
			grid[17][7] = EMPTY;

			grid[1][8] = WALL;
			grid[2][8] = BOX;
			grid[3][8] = WALL;
			grid[4][8] = EMPTY;
			grid[5][8] = WALL;
			grid[6][8] = GOAL;
			grid[7][8] = BOX;
			grid[8][8] = GOAL;
			grid[9][8] = EMPTY;
			grid[10][8] = GOAL;
			grid[11][8] = WALL;
			grid[12][8] = EMPTY;
			grid[13][8] = EMPTY;
			grid[14][8] = WALL;
			grid[15][8] = WALL;
			grid[16][8] = EMPTY;
			grid[17][8] = EMPTY;

			grid[1][9] = EMPTY;
			grid[2][9] = EMPTY;
			grid[3][9] = BOX;
			grid[4][9] = EMPTY;
			grid[5][9] = WALL;
			grid[6][9] = EMPTY;
			grid[7][9] = WALL;
			grid[8][9] = WALL;
			grid[9][9] = WALL;
			grid[10][9] = EMPTY;
			grid[11][9] = WALL;
			grid[12][9] = EMPTY;
			grid[13][9] = BOX;
			grid[14][9] = EMPTY;
			grid[15][9] = BOX;
			grid[16][9] = BOX;
			grid[17][9] = EMPTY;

			grid[1][10] = EMPTY;
			grid[2][10] = EMPTY;
			grid[3][10] = BOX;
			grid[4][10] = EMPTY;
			grid[5][10] = WALL;
			grid[6][10] = GOAL;
			grid[7][10] = EMPTY;
			grid[8][10] = EMPTY;
			grid[9][10] = EMPTY;
			grid[10][10] = EMPTY;
			grid[11][10] = WALL;
			grid[12][10] = EMPTY;
			grid[13][10] = EMPTY;
			grid[14][10] = EMPTY;
			grid[15][10] = EMPTY;
			grid[16][10] = EMPTY;
			grid[17][10] = EMPTY;

			grid[1][11] = EMPTY;
			grid[2][11] = WALL;
			grid[3][11] = BOX;
			grid[4][11] = WALL;
			grid[5][11] = WALL;
			grid[6][11] = BOX;
			grid[7][11] = WALL;
			grid[8][11] = WALL;
			grid[9][11] = WALL;
			grid[10][11] = EMPTY;
			grid[11][11] = WALL;
			grid[12][11] = WALL;
			grid[13][11] = WALL;
			grid[14][11] = WALL;
			grid[15][11] = BOX;
			grid[16][11] = WALL;
			grid[17][11] = WALL;

			grid[1][12] = EMPTY;
			grid[2][12] = EMPTY;
			grid[3][12] = BOX;
			grid[4][12] = EMPTY;
			grid[5][12] = EMPTY;
			grid[6][12] = EMPTY;
			grid[7][12] = WALL;
			grid[8][12] = GOAL;
			grid[9][12] = GOAL;
			grid[10][12] = EMPTY;
			grid[11][12] = GOAL;
			grid[12][12] = GOAL;
			grid[13][12] = EMPTY;
			grid[14][12] = EMPTY;
			grid[15][12] = GOAL;
			grid[16][12] = GOAL;
			grid[17][12] = GOAL;

			grid[1][13] = EMPTY;
			grid[2][13] = EMPTY;
			grid[3][13] = BOX;
			grid[4][13] = EMPTY;
			grid[5][13] = WALL;
			grid[6][13] = EMPTY;
			grid[7][13] = WALL;
			grid[8][13] = EMPTY;
			grid[9][13] = WALL;
			grid[10][13] = GOAL;
			grid[11][13] = WALL;
			grid[12][13] = EMPTY;
			grid[13][13] = EMPTY;
			grid[14][13] = EMPTY;
			grid[15][13] = EMPTY;
			grid[16][13] = GOAL;
			grid[17][13] = GOAL;

			grid[1][14] = EMPTY;
			grid[2][14] = EMPTY;
			grid[3][14] = EMPTY;
			grid[4][14] = EMPTY;
			grid[5][14] = EMPTY;
			grid[6][14] = EMPTY;
			grid[7][14] = WALL;
			grid[8][14] = EMPTY;
			grid[9][14] = EMPTY;
			grid[10][14] = EMPTY;
			grid[11][14] = EMPTY;
			grid[12][14] = EMPTY;
			grid[13][14] = EMPTY;
			grid[14][14] = EMPTY;
			grid[15][14] = GOAL;
			grid[16][14] = GOAL;
			grid[17][14] = GOAL;

			player_x = 1;
			player_y = 1;
		} else if (level == 23) {
			// HARD MAP 3
			this.grid = new int[11][9];
			for (int x = 0; x < 11; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[10][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = EMPTY;
			grid[3][1] = WALL;
			grid[4][1] = EMPTY;
			grid[5][1] = PLAYER;
			grid[6][1] = EMPTY;
			grid[7][1] = WALL;
			grid[8][1] = EMPTY;
			grid[9][1] = EMPTY;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = WALL;
			grid[4][2] = EMPTY;
			grid[5][2] = BOX;
			grid[6][2] = EMPTY;
			grid[7][2] = WALL;
			grid[8][2] = EMPTY;
			grid[9][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = BOX;
			grid[5][3] = GOAL;
			grid[6][3] = BOX;
			grid[7][3] = WALL;
			grid[8][3] = EMPTY;
			grid[9][3] = EMPTY;

			grid[1][4] = WALL;
			grid[2][4] = WALL;
			grid[3][4] = WALL;
			grid[4][4] = GOAL;
			grid[5][4] = BOX;
			grid[6][4] = GOAL;
			grid[7][4] = WALL;
			grid[8][4] = WALL;
			grid[9][4] = EMPTY;

			grid[1][5] = WALL;
			grid[2][5] = EMPTY;
			grid[3][5] = GOAL;
			grid[4][5] = BOX;
			grid[5][5] = GOAL;
			grid[6][5] = BOX;
			grid[7][5] = GOAL;
			grid[8][5] = WALL;
			grid[9][5] = WALL;

			grid[1][6] = EMPTY;
			grid[2][6] = EMPTY;
			grid[3][6] = BOX;
			grid[4][6] = GOAL;
			grid[5][6] = BOX;
			grid[6][6] = GOAL;
			grid[7][6] = BOX;
			grid[8][6] = EMPTY;
			grid[9][6] = EMPTY;

			grid[1][7] = EMPTY;
			grid[2][7] = EMPTY;
			grid[3][7] = EMPTY;
			grid[4][7] = EMPTY;
			grid[5][7] = GOAL;
			grid[6][7] = EMPTY;
			grid[7][7] = EMPTY;
			grid[8][7] = EMPTY;
			grid[9][7] = EMPTY;

			player_x = 5;
			player_y = 1;

		} else if (level == 24) {
			// HARD MAP 4
			this.grid = new int[10][9];
			for (int x = 0; x < 10; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[8][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = WALL;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = EMPTY;
			grid[7][1] = EMPTY;
			grid[8][1] = WALL;

			grid[1][2] = EMPTY;
			grid[2][2] = BOX;
			grid[3][2] = EMPTY;
			grid[4][2] = BOX;
			grid[5][2] = EMPTY;
			grid[6][2] = BOX;
			grid[7][2] = EMPTY;
			grid[8][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = GOAL;
			grid[4][3] = EMPTY;
			grid[5][3] = GOAL;
			grid[6][3] = EMPTY;
			grid[7][3] = WALL;
			grid[8][3] = GOAL;

			grid[1][4] = WALL;
			grid[2][4] = BOX;
			grid[3][4] = WALL;
			grid[4][4] = WALL;
			grid[5][4] = BOX;
			grid[6][4] = BOX;
			grid[7][4] = GOAL;
			grid[8][4] = EMPTY;

			grid[1][5] = WALL;
			grid[2][5] = EMPTY;
			grid[3][5] = GOAL;
			grid[4][5] = WALL;
			grid[5][5] = GOAL;
			grid[6][5] = EMPTY;
			grid[7][5] = GOAL;
			grid[8][5] = EMPTY;

			grid[1][6] = WALL;
			grid[2][6] = BOX;
			grid[3][6] = EMPTY;
			grid[4][6] = PLAYER;
			grid[5][6] = EMPTY;
			grid[6][6] = EMPTY;
			grid[7][6] = WALL;
			grid[8][6] = WALL;

			grid[1][7] = WALL;
			grid[2][7] = EMPTY;
			grid[3][7] = EMPTY;
			grid[4][7] = WALL;
			grid[5][7] = WALL;
			grid[6][7] = WALL;
			grid[7][7] = WALL;
			grid[8][7] = EMPTY;

			player_x = 4;
			player_y = 6;

		} else if (level == 25) {
			// HARD MAP 5
			this.grid = new int[15][9];
			for (int x = 0; x < 15; x++) {
				grid[x][0] = WALL;
				grid[x][8] = WALL;
			}
			for (int y = 0; y < 9; y++) {
				grid[0][y] = WALL;
				grid[14][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;
			grid[5][1] = EMPTY;
			grid[6][1] = EMPTY;
			grid[7][1] = EMPTY;
			grid[8][1] = WALL;
			grid[9][1] = WALL;
			grid[10][1] = WALL;
			grid[11][1] = WALL;
			grid[12][1] = WALL;
			grid[13][1] = WALL;

			grid[1][2] = EMPTY;
			grid[2][2] = BOX;
			grid[3][2] = BOX;
			grid[4][2] = BOX;
			grid[5][2] = BOX;
			grid[6][2] = BOX;
			grid[7][2] = EMPTY;
			grid[8][2] = WALL;
			grid[9][2] = GOAL;
			grid[10][2] = GOAL;
			grid[11][2] = GOAL;
			grid[12][2] = GOAL;
			grid[13][2] = GOAL;

			grid[1][3] = EMPTY;
			grid[2][3] = BOX;
			grid[3][3] = GOAL;
			grid[4][3] = GOAL;
			grid[5][3] = GOAL;
			grid[6][3] = BOX;
			grid[7][3] = EMPTY;
			grid[8][3] = WALL;
			grid[9][3] = GOAL;
			grid[10][3] = BOX;
			grid[11][3] = BOX;
			grid[12][3] = BOX;
			grid[13][3] = GOAL;

			grid[1][4] = EMPTY;
			grid[2][4] = BOX;
			grid[3][4] = GOAL;
			grid[4][4] = BOX;
			grid[5][4] = GOAL;
			grid[6][4] = BOX;
			grid[7][4] = EMPTY;
			grid[8][4] = EMPTY;
			grid[9][4] = GOAL;
			grid[10][4] = BOX;
			grid[11][4] = PLAYER;
			grid[12][4] = BOX;
			grid[13][4] = GOAL;

			grid[1][5] = EMPTY;
			grid[2][5] = BOX;
			grid[3][5] = GOAL;
			grid[4][5] = GOAL;
			grid[5][5] = GOAL;
			grid[6][5] = BOX;
			grid[7][5] = EMPTY;
			grid[8][5] = WALL;
			grid[9][5] = GOAL;
			grid[10][5] = BOX;
			grid[11][5] = BOX;
			grid[12][5] = BOX;
			grid[13][5] = GOAL;

			grid[1][6] = EMPTY;
			grid[2][6] = BOX;
			grid[3][6] = BOX;
			grid[4][6] = BOX;
			grid[5][6] = BOX;
			grid[6][6] = BOX;
			grid[7][6] = EMPTY;
			grid[8][6] = WALL;
			grid[9][6] = GOAL;
			grid[10][6] = GOAL;
			grid[11][6] = GOAL;
			grid[12][6] = GOAL;
			grid[13][6] = GOAL;

			grid[1][7] = EMPTY;
			grid[2][7] = EMPTY;
			grid[3][7] = EMPTY;
			grid[4][7] = EMPTY;
			grid[5][7] = EMPTY;
			grid[6][7] = EMPTY;
			grid[7][7] = EMPTY;
			grid[8][7] = WALL;
			grid[9][7] = WALL;
			grid[10][7] = WALL;
			grid[11][7] = WALL;
			grid[12][7] = WALL;
			grid[13][7] = WALL;

			player_x = 11;
			player_y = 4;

		} else if (level == 26) {
			// HARD MAP 6
			this.grid = new int[20][8];
			for (int x = 0; x < 20; x++) {
				grid[x][0] = WALL;
				grid[x][7] = WALL;
			}
			for (int y = 0; y < 8; y++) {
				grid[0][y] = WALL;
				grid[19][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = EMPTY;
			grid[5][1] = EMPTY;
			grid[6][1] = EMPTY;
			grid[7][1] = EMPTY;
			grid[8][1] = EMPTY;
			grid[9][1] = EMPTY;
			grid[10][1] = EMPTY;
			grid[11][1] = WALL;
			grid[12][1] = WALL;
			grid[13][1] = WALL;
			grid[14][1] = WALL;
			grid[15][1] = WALL;
			grid[16][1] = EMPTY;
			grid[17][1] = EMPTY;
			grid[18][1] = PLAYER;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = BOX;
			grid[4][2] = EMPTY;
			grid[5][2] = BOX;
			grid[6][2] = BOX;
			grid[7][2] = BOX;
			grid[8][2] = WALL;
			grid[9][2] = WALL;
			grid[10][2] = GOAL;
			grid[11][2] = EMPTY;
			grid[12][2] = GOALBOX;
			grid[13][2] = EMPTY;
			grid[14][2] = WALL;
			grid[15][2] = WALL;
			grid[16][2] = BOX;
			grid[17][2] = BOX;
			grid[18][2] = EMPTY;

			grid[1][3] = WALL;
			grid[2][3] = WALL;
			grid[3][3] = WALL;
			grid[4][3] = WALL;
			grid[5][3] = WALL;
			grid[6][3] = EMPTY;
			grid[7][3] = GOAL;
			grid[8][3] = EMPTY;
			grid[9][3] = WALL;
			grid[10][3] = GOAL;
			grid[11][3] = EMPTY;
			grid[12][3] = GOAL;
			grid[13][3] = GOAL;
			grid[14][3] = EMPTY;
			grid[15][3] = EMPTY;
			grid[16][3] = EMPTY;
			grid[17][3] = EMPTY;
			grid[18][3] = WALL;

			grid[1][4] = WALL;
			grid[2][4] = WALL;
			grid[3][4] = WALL;
			grid[4][4] = WALL;
			grid[5][4] = EMPTY;
			grid[6][4] = GOALBOX;
			grid[7][4] = GOALBOX;
			grid[8][4] = GOAL;
			grid[9][4] = GOAL;
			grid[10][4] = GOALBOX;
			grid[11][4] = GOAL;
			grid[12][4] = GOALBOX;
			grid[13][4] = GOALBOX;
			grid[14][4] = BOX;
			grid[15][4] = EMPTY;
			grid[16][4] = WALL;
			grid[17][4] = WALL;
			grid[18][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = EMPTY;
			grid[3][5] = BOX;
			grid[4][5] = EMPTY;
			grid[5][5] = EMPTY;
			grid[6][5] = EMPTY;
			grid[7][5] = EMPTY;
			grid[8][5] = EMPTY;
			grid[9][5] = BOX;
			grid[10][5] = EMPTY;
			grid[11][5] = WALL;
			grid[12][5] = GOAL;
			grid[13][5] = EMPTY;
			grid[14][5] = WALL;
			grid[15][5] = WALL;
			grid[16][5] = WALL;
			grid[17][5] = WALL;
			grid[18][5] = WALL;

			grid[1][6] = WALL;
			grid[2][6] = EMPTY;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = EMPTY;
			grid[7][6] = WALL;
			grid[8][6] = WALL;
			grid[9][6] = WALL;
			grid[10][6] = WALL;
			grid[11][6] = WALL;
			grid[12][6] = EMPTY;
			grid[13][6] = EMPTY;
			grid[14][6] = WALL;
			grid[15][6] = WALL;
			grid[16][6] = WALL;
			grid[17][6] = WALL;
			grid[18][6] = WALL;

			player_x = 18;
			player_y = 1;
		} else if (level == 27) {
			// HARD MAP 7
			this.grid = new int[14][10];
			for (int x = 0; x < 14; x++) {
				grid[x][0] = WALL;
				grid[x][9] = WALL;
			}
			for (int y = 0; y < 10; y++) {
				grid[0][y] = WALL;
				grid[13][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = EMPTY;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = WALL;
			grid[7][1] = GOAL;
			grid[8][1] = WALL;
			grid[9][1] = WALL;
			grid[10][1] = EMPTY;
			grid[11][1] = EMPTY;
			grid[12][1] = EMPTY;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = EMPTY;
			grid[4][2] = WALL;
			grid[5][2] = WALL;
			grid[6][2] = EMPTY;
			grid[7][2] = GOAL;
			grid[8][2] = GOAL;
			grid[9][2] = WALL;
			grid[10][2] = EMPTY;
			grid[11][2] = BOX;
			grid[12][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = BOX;
			grid[3][3] = EMPTY;
			grid[4][3] = WALL;
			grid[5][3] = WALL;
			grid[6][3] = BOX;
			grid[7][3] = GOAL;
			grid[8][3] = BOX;
			grid[9][3] = WALL;
			grid[10][3] = WALL;
			grid[11][3] = EMPTY;
			grid[12][3] = EMPTY;

			grid[1][4] = EMPTY;
			grid[2][4] = EMPTY;
			grid[3][4] = BOX;
			grid[4][4] = EMPTY;
			grid[5][4] = WALL;
			grid[6][4] = GOAL;
			grid[7][4] = GOAL;
			grid[8][4] = GOAL;
			grid[9][4] = GOAL;
			grid[10][4] = EMPTY;
			grid[11][4] = EMPTY;
			grid[12][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = BOX;
			grid[3][5] = EMPTY;
			grid[4][5] = BOX;
			grid[5][5] = EMPTY;
			grid[6][5] = WALL;
			grid[7][5] = GOAL;
			grid[8][5] = GOALBOX;
			grid[9][5] = WALL;
			grid[10][5] = WALL;
			grid[11][5] = WALL;
			grid[12][5] = WALL;

			grid[1][6] = EMPTY;
			grid[2][6] = WALL;
			grid[3][6] = EMPTY;
			grid[4][6] = EMPTY;
			grid[5][6] = EMPTY;
			grid[6][6] = BOX;
			grid[7][6] = GOALBOX;
			grid[8][6] = GOAL;
			grid[9][6] = GOAL;
			grid[10][6] = EMPTY;
			grid[11][6] = BOX;
			grid[12][6] = PLAYER;

			grid[1][7] = EMPTY;
			grid[2][7] = BOX;
			grid[3][7] = BOX;
			grid[4][7] = EMPTY;
			grid[5][7] = WALL;
			grid[6][7] = EMPTY;
			grid[7][7] = GOAL;
			grid[8][7] = BOX;
			grid[9][7] = EMPTY;
			grid[10][7] = WALL;
			grid[11][7] = WALL;
			grid[12][7] = WALL;

			grid[1][8] = EMPTY;
			grid[2][8] = EMPTY;
			grid[3][8] = EMPTY;
			grid[4][8] = EMPTY;
			grid[5][8] = WALL;
			grid[6][8] = EMPTY;
			grid[7][8] = EMPTY;
			grid[8][8] = WALL;
			grid[9][8] = WALL;
			grid[10][8] = WALL;
			grid[11][8] = WALL;
			grid[12][8] = WALL;

			player_x = 12;
			player_y = 6;

		} else if (level == 28) {
			// HARD MAP 8
			this.grid = new int[7][10];
			for (int x = 0; x < 7; x++) {
				grid[x][0] = WALL;
				grid[x][9] = WALL;
			}
			for (int y = 0; y < 10; y++) {
				grid[0][y] = WALL;
				grid[6][y] = WALL;
			}
			grid[1][1] = WALL;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = WALL;
			grid[5][1] = WALL;

			grid[1][2] = EMPTY;
			grid[2][2] = BOX;
			grid[3][2] = EMPTY;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;

			grid[1][3] = EMPTY;
			grid[2][3] = WALL;
			grid[3][3] = EMPTY;
			grid[4][3] = EMPTY;
			grid[5][3] = BOX;

			grid[1][4] = EMPTY;
			grid[2][4] = GOAL;
			grid[3][4] = GOAL;
			grid[4][4] = GOAL;
			grid[5][4] = GOAL;

			grid[1][5] = EMPTY;
			grid[2][5] = WALL;
			grid[3][5] = EMPTY;
			grid[4][5] = BOX;
			grid[5][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = WALL;
			grid[3][6] = GOAL;
			grid[4][6] = WALL;
			grid[5][6] = EMPTY;

			grid[1][7] = EMPTY;
			grid[2][7] = BOX;
			grid[3][7] = GOALBOX;
			grid[4][7] = BOX;
			grid[5][7] = EMPTY;

			grid[1][8] = WALL;
			grid[2][8] = EMPTY;
			grid[3][8] = PLAYER;
			grid[4][8] = EMPTY;
			grid[5][8] = WALL;

			player_x = 3;
			player_y = 8;
		} else if (level == 29) {
			// HARD MAP 9
			this.grid = new int[12][12];
			for (int x = 0; x < 12; x++) {
				grid[x][0] = WALL;
				grid[x][11] = WALL;
			}
			for (int y = 0; y < 12; y++) {
				grid[0][y] = WALL;
				grid[11][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = EMPTY;
			grid[3][1] = EMPTY;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = WALL;
			grid[7][1] = EMPTY;
			grid[8][1] = EMPTY;
			grid[9][1] = EMPTY;
			grid[10][1] = WALL;

			grid[1][2] = EMPTY;
			grid[2][2] = GOAL;
			grid[3][2] = BOX;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;
			grid[6][2] = EMPTY;
			grid[7][2] = EMPTY;
			grid[8][2] = EMPTY;
			grid[9][2] = EMPTY;
			grid[10][2] = WALL;

			grid[1][3] = WALL;
			grid[2][3] = GOAL;
			grid[3][3] = WALL;
			grid[4][3] = WALL;
			grid[5][3] = WALL;
			grid[6][3] = WALL;
			grid[7][3] = WALL;
			grid[8][3] = BOX;
			grid[9][3] = WALL;
			grid[10][3] = WALL;

			grid[1][4] = EMPTY;
			grid[2][4] = EMPTY;
			grid[3][4] = WALL;
			grid[4][4] = EMPTY;
			grid[5][4] = EMPTY;
			grid[6][4] = EMPTY;
			grid[7][4] = WALL;
			grid[8][4] = EMPTY;
			grid[9][4] = BOX;
			grid[10][4] = EMPTY;

			grid[1][5] = EMPTY;
			grid[2][5] = GOAL;
			grid[3][5] = BOX;
			grid[4][5] = EMPTY;
			grid[5][5] = GOAL;
			grid[6][5] = EMPTY;
			grid[7][5] = BOX;
			grid[8][5] = EMPTY;
			grid[9][5] = EMPTY;
			grid[10][5] = EMPTY;

			grid[1][6] = EMPTY;
			grid[2][6] = EMPTY;
			grid[3][6] = WALL;
			grid[4][6] = WALL;
			grid[5][6] = GOAL;
			grid[6][6] = WALL;
			grid[7][6] = WALL;
			grid[8][6] = EMPTY;
			grid[9][6] = WALL;
			grid[10][6] = WALL;

			grid[1][7] = WALL;
			grid[2][7] = BOX;
			grid[3][7] = WALL;
			grid[4][7] = EMPTY;
			grid[5][7] = GOAL;
			grid[6][7] = EMPTY;
			grid[7][7] = EMPTY;
			grid[8][7] = EMPTY;
			grid[9][7] = WALL;
			grid[10][7] = EMPTY;

			grid[1][8] = WALL;
			grid[2][8] = PLAYER;
			grid[3][8] = WALL;
			grid[4][8] = EMPTY;
			grid[5][8] = EMPTY;
			grid[6][8] = EMPTY;
			grid[7][8] = WALL;
			grid[8][8] = WALL;
			grid[9][8] = WALL;
			grid[10][8] = EMPTY;

			grid[1][9] = WALL;
			grid[2][9] = BOX;
			grid[3][9] = WALL;
			grid[4][9] = WALL;
			grid[5][9] = WALL;
			grid[6][9] = EMPTY;
			grid[7][9] = WALL;
			grid[8][9] = EMPTY;
			grid[9][9] = EMPTY;
			grid[10][9] = EMPTY;

			grid[1][10] = WALL;
			grid[2][10] = EMPTY;
			grid[3][10] = EMPTY;
			grid[4][10] = EMPTY;
			grid[5][10] = EMPTY;
			grid[6][10] = EMPTY;
			grid[7][10] = WALL;
			grid[8][10] = EMPTY;
			grid[9][10] = EMPTY;
			grid[10][10] = EMPTY;

			player_x = 3;
			player_y = 8;

		} else if (level == 30) {
			// HARD MAP 10
			this.grid = new int[13][13];
			for (int x = 0; x < 12; x++) {
				grid[x][0] = WALL;
				grid[x][12] = WALL;
			}
			for (int y = 0; y < 13; y++) {
				grid[0][y] = WALL;
				grid[12][y] = WALL;
			}
			grid[1][1] = EMPTY;
			grid[2][1] = EMPTY;
			grid[3][1] = WALL;
			grid[4][1] = WALL;
			grid[5][1] = WALL;
			grid[6][1] = EMPTY;
			grid[7][1] = EMPTY;
			grid[8][1] = EMPTY;
			grid[9][1] = EMPTY;
			grid[10][1] = WALL;
			grid[11][1] = WALL;

			grid[1][2] = EMPTY;
			grid[2][2] = EMPTY;
			grid[3][2] = WALL;
			grid[4][2] = EMPTY;
			grid[5][2] = EMPTY;
			grid[6][2] = EMPTY;
			grid[7][2] = GOAL;
			grid[8][2] = GOAL;
			grid[9][2] = GOAL;
			grid[10][2] = WALL;
			grid[11][2] = WALL;

			grid[1][3] = EMPTY;
			grid[2][3] = EMPTY;
			grid[3][3] = WALL;
			grid[4][3] = EMPTY;
			grid[5][3] = EMPTY;
			grid[6][3] = WALL;
			grid[7][3] = GOAL;
			grid[8][3] = GOAL;
			grid[9][3] = GOAL;
			grid[10][3] = WALL;
			grid[11][3] = WALL;

			grid[1][4] = WALL;
			grid[2][4] = EMPTY;
			grid[3][4] = WALL;
			grid[4][4] = WALL;
			grid[5][4] = BOX;
			grid[6][4] = EMPTY;
			grid[7][4] = WALL;
			grid[8][4] = EMPTY;
			grid[9][4] = EMPTY;
			grid[10][4] = WALL;
			grid[11][4] = WALL;

			grid[1][5] = WALL;
			grid[2][5] = EMPTY;
			grid[3][5] = BOX;
			grid[4][5] = EMPTY;
			grid[5][5] = EMPTY;
			grid[6][5] = EMPTY;
			grid[7][5] = WALL;
			grid[8][5] = EMPTY;
			grid[9][5] = EMPTY;
			grid[10][5] = WALL;
			grid[11][5] = WALL;

			grid[1][6] = WALL;
			grid[2][6] = PLAYER;
			grid[3][6] = BOX;
			grid[4][6] = WALL;
			grid[5][6] = WALL;
			grid[6][6] = EMPTY;
			grid[7][6] = WALL;
			grid[8][6] = EMPTY;
			grid[9][6] = WALL;
			grid[10][6] = WALL;
			grid[11][6] = WALL;

			grid[1][7] = EMPTY;
			grid[2][7] = BOX;
			grid[3][7] = BOX;
			grid[4][7] = EMPTY;
			grid[5][7] = EMPTY;
			grid[6][7] = EMPTY;
			grid[7][7] = EMPTY;
			grid[8][7] = EMPTY;
			grid[9][7] = EMPTY;
			grid[10][7] = WALL;
			grid[11][7] = WALL;

			grid[1][8] = EMPTY;
			grid[2][8] = EMPTY;
			grid[3][8] = BOX;
			grid[4][8] = EMPTY;
			grid[5][8] = WALL;
			grid[6][8] = WALL;
			grid[7][8] = EMPTY;
			grid[8][8] = BOX;
			grid[9][8] = EMPTY;
			grid[10][8] = WALL;
			grid[11][8] = WALL;

			grid[1][9] = WALL;
			grid[2][9] = EMPTY;
			grid[3][9] = WALL;
			grid[4][9] = BOX;
			grid[5][9] = WALL;
			grid[6][9] = WALL;
			grid[7][9] = WALL;
			grid[8][9] = WALL;
			grid[9][9] = BOX;
			grid[10][9] = WALL;
			grid[11][9] = WALL;

			grid[1][10] = GOAL;
			grid[2][10] = GOAL;
			grid[3][10] = GOAL;
			grid[4][10] = GOALBOX;
			grid[5][10] = GOAL;
			grid[6][10] = EMPTY;
			grid[7][10] = EMPTY;
			grid[8][10] = BOX;
			grid[9][10] = EMPTY;
			grid[10][10] = BOX;
			grid[11][10] = EMPTY;

			grid[1][11] = EMPTY;
			grid[2][11] = EMPTY;
			grid[3][11] = GOAL;
			grid[4][11] = EMPTY;
			grid[5][11] = EMPTY;
			grid[6][11] = WALL;
			grid[7][11] = EMPTY;
			grid[8][11] = EMPTY;
			grid[9][11] = EMPTY;
			grid[10][11] = EMPTY;
			grid[11][11] = EMPTY;

			player_x = 2;
			player_y = 6;

		}

	}

    /**
     * Constructor of a randomly generated map
     * 
     * @param difficulty as letter 'e','m' or 'h', the difficulty of the game
     */
    public MapModel(char difficulty) {
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
            objectives = 7;
            break;
        default:
            length = 10;
            height = 10;
            objectives = 10;
        }
        
        this.mapLevel = RANDOM_LEVEL;
        Random rGen = new Random(System.currentTimeMillis());

        // Continuously loops until it finds a map it likes
        boolean success = false;
        while (!success) {
            ArrayList<int[][]> gridList = new ArrayList<int[][]>();
            for (int i = 0; i < length * height; i++) {
                rGen.nextInt(10);
                int[][] part = createMapMatrix(rGen);
                gridList.add(part);
            }
            this.grid = mergeTemplates(gridList, length, height);
            success = addMapElements(GOAL, objectives, rGen);
            if (!success)
                continue;
            success = addMapElements(BOX, objectives, rGen);
            if (!success)
                continue;
            success = addMapElements(PLAYER, 1, rGen);
            if (!success)
                continue;
            success = connectedMap(objectives);
        }
    }

    /**
     * Empty constructor to be used in the clone method
     */
    private MapModel() {
    }

    /**
     * Deep clones the current Map
     * 
     * @return the new clone Map object
     */
    public MapModel clone() {
        MapModel clonedMap = new MapModel();
        clonedMap.grid = new int[getLength()][getHeight()];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getLength(); x++) {
                clonedMap.grid[x][y] = this.grid[x][y];
            }
        }
        clonedMap.player_x = this.player_x;
        clonedMap.player_y = this.player_y;
        clonedMap.mapLevel = this.mapLevel;
        return clonedMap;
    }

    /**
     * Gets the value of a grid space given its x and y coordinates
     * 
     * @param x as the x coord
     * @param y as the y coord
     * @return the element at the tile
     */
    public int getTile(int x, int y) {
        return grid[x][y];
    }

    /**
     * Helper function to change a grid tile to a value where the box or player
     * is no longer there
     * 
     * @param x as the x coord
     * @param y as the y coord
     * @return the to change the coordinate into after moving
     */
    private int removeObject(int x, int y) {
        if (grid[x][y] == PLAYER) {
            return EMPTY;
        } else if (grid[x][y] == BOX) {
            return EMPTY;
        } else if (grid[x][y] == GOALBOX) {
            return GOAL;
        } else if (grid[x][y] == GOALPLAYER) {
            return GOAL;
        } else {
            System.out
                    .println("This should never occur, trying to remove an object from a grid position with no object");
            return -1;
        }
    }

    /**
     * Moves the player to the left
     */
    public void moveLeft() {
        if (moveTo(player_x, player_y, player_x - 1, player_y)) {
            player_x--;
        }
    }

    /**
     * Moves the player to the right
     */
    public void moveRight() {
        if (moveTo(player_x, player_y, player_x + 1, player_y)) {
            player_x++;
        }
    }

    /**
     * Moves the player upwards
     */
    public void moveUp() {
        if (moveTo(player_x, player_y, player_x, player_y - 1)) {
            player_y--;
        }
    }

    /**
     * Moves the player downwards
     */
    public void moveDown() {
        if (moveTo(player_x, player_y, player_x, player_y + 1)) {
            player_y++;
        }
    }

    /**
     * Prints the map on console
     */
    public void printMap() {
        //System.out.println("printing internal map");
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getLength(); x++) {
                //System.out.print(getTile(x, y));
            }
            //System.out.print("\n");
        }
    }

    /**
     * Attempts to move the player from one spot to another
     * 
     * @param from_x
     * @param from_y
     * @param to_x
     * @param to_y
     * @pre (|from_x-to_x|<=1 XOR |from_y-to_y|<=1) AND
     *      grid[from_x][from_y]=player
     * @post the grid is a valid state
     * @return true if movement was successful, false otherwise (e.g. if there
     *         was a wall in the way)
     */
    private boolean moveTo(int from_x, int from_y, int to_x, int to_y) {
        if (grid[to_x][to_y] == WALL) {// wall
            return false;// can never move into a wall
        } else if (grid[to_x][to_y] == EMPTY) {// empty
            //System.out.println("target is an empty");
            grid[to_x][to_y] = PLAYER;// set the to tile to be a player
            grid[from_x][from_y] = removeObject(from_x, from_y);
            return true;// can always move into an empty space
        } else if (grid[to_x][to_y] == PLAYER) {// player, this should never
                                                // happen
            System.out.println("error, attempted to move into a space that is occupied by the player");
            return false;
        } else if (grid[to_x][to_y] == BOX) {// box
            // calculate the next grid space over
            int new_x = 2 * to_x - from_x;
            int new_y = 2 * to_y - from_y;
            // if the next grid space is not blocked then move everything
            if (grid[new_x][new_y] == EMPTY || grid[new_x][new_y] == GOAL) {
                // remove object from 'from'
                grid[from_x][from_y] = removeObject(from_x, from_y);
                // move the box out and the player in
                if (grid[to_x][to_y] == BOX) {// box -> player
                    grid[to_x][to_y] = PLAYER;
                } else {// grid[to_x][to_y] == 5
                    grid[to_x][to_y] = GOALPLAYER;// box + goal -> player + goal
                }
                // change third space
                if (grid[new_x][new_y] == EMPTY) {
                    grid[new_x][new_y] = BOX;// empty->box
                } else {// grid[to_x][to_y] == 4
                    grid[new_x][new_y] = GOALBOX;// goal -> goal+box
                }
                return true;
            } else {// otherwise return false;
                return false;
            }
        } else if (grid[to_x][to_y] == GOAL) {// goal space
            grid[to_x][to_y] = GOALPLAYER;// set the to tile to be a player+goal
            grid[from_x][from_y] = removeObject(from_x, from_y);
            return true;
        } else if (grid[to_x][to_y] == GOALBOX) {// goal + box
            int new_x = 2 * to_x - from_x;
            int new_y = 2 * to_y - from_y;
            if (grid[new_x][new_y] == EMPTY) {// if the next grid space is not
                                              // blocked
                grid[new_x][new_y] = BOX;// move box to next space
            } else if (grid[new_x][new_y] == GOAL) {
                grid[new_x][new_y] = GOALBOX;// move box to next space
            } else {
                return false;// if the next space is blocked then the move fails
            }
            grid[to_x][to_y] = GOALPLAYER;// move player to this space
            // move player off previous space
            grid[from_x][from_y] = removeObject(from_x, from_y);
            return true;
        } else if (grid[to_x][to_y] == GOALPLAYER) {// player + goal, this should never happen
            //System.out.println("error, attempted to move into a space that is occupied by the player");
            return false;
        }
        return false;
    }

    /**
     * Determines if the game has been won
     * 
     * @return true if the game is won
     */
    public boolean winState() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == BOX) {// if box not on goal then it is not a win state
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the length of the map.
     * 
     * @return length
     */
    public int getLength() {
        if (grid != null)
            return grid.length;
        return 0;
    }

    /**
     * Gets the height of the map
     * 
     * @return height
     */
    public int getHeight() {
        if (grid != null && grid[0] != null)
            return grid[0].length;
        return 0;
    }
    
    /**
     * Gets the level of the map
     * 
     * @return mapLevel
     */
    public int getMapLevel() {
    	return mapLevel;
    }
    
    /**
     * Gets a 3x3 template string
     * 
     * @param seed as the seed to choose from
     * @return a 9 character string
     */
    private String createTemplate(int seed) {
        switch (seed) {
        case 0:
            return "111" + "111" + "111";
        case 1:
            return "011" + "111" + "111";
        case 2:
            return "001" + "111" + "111";
        case 3:
            return "000" + "111" + "111";
        case 4:
            return "010" + "111" + "111";
        case 5:
            return "111" + "101" + "111";
        case 6:
            return "011" + "101" + "111";
        case 7:
            return "011" + "101" + "110";
        case 8:
            return "011" + "110" + "110";
        case 9:
            return "010" + "111" + "101";
        default: // For debugging purposes ONLY
            return "123" + "456" + "789";
        }
    }

    /**
     * Creates a 3x3 grid based on modular template string
     * 
     * @param rGen
     *            as the random number generator
     * @return the map as a map matrix
     */
    private int[][] createMapMatrix(Random rGen) {
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
     * 
     * @param maps
     * @param length
     * @param height
     * @return the map as an int matrix
     */
    private int[][] mergeTemplates(ArrayList<int[][]> maps, int length, int height) {
        int xOffset = 1;
        int yOffset = 1;
        Iterator<int[][]> iMaps = maps.iterator();

        int[][] joined = createOuterWall(3 * length + 2, 3 * height + 2);
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
     * 
     * @param template as the String template to rotate
     * @return the 3x3 grid
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
     * 
     * @param template as the String template to rotate
     * @return the 3x3 grid
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
     * 
     * @param template as the String template to rotate
     * @return the 3x3 grid
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
     * 
     * @param template as the String template to rotate
     * @return the 3x3 grid
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
     * 
     * @param xPos as the x coord
     * @param yPos as the y coord
     * @param objective
     *            as the element to with
     * @return
     */
    private boolean checkSides(int xPos, int yPos, int objective) {
        int i = 0;
        // Check self is empty
        if (isClear(xPos, yPos)) {
            // Box has an additional restriction cannot be placed on edge
            if (objective == MapModel.BOX) {
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
     * 
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

    /**
     * Creates a grid that add a wall on the outer edge so player cannot go out
     * of bounds
     * 
     * @param length of the grid
     * @param height of the grid
     * @return a grid with all the outer walls initialised
     */
    private int[][] createOuterWall(int length, int height) {
        int[][] grid = new int[length][height];
        for (int i = 0; i < length; i++) {
            grid[i][0] = WALL;
            grid[i][height - 1] = WALL;
        }
        for (int i = 1; i < height - 1; i++) {
            grid[0][i] = WALL;
            grid[length - 1][i] = WALL;
        }
        return grid;
    }

    /**
     * Adds the map element specified
     * 
     * @param element as the map element
     * @param count how many to add in
     * @param rGen as the random number generator
     * @return true if all elements were successfully added
     */
    private boolean addMapElements(int element, int count, Random rGen) {
        int size = grid.length * grid[0].length;
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

    /**
     * A search to determine that all map elements are connected to the player
     * 
     * @param objectives as number of objects on the map
     * @return true if all objects can be reached by the player
     */
    private boolean connectedMap(int objectives) {
        Queue<Coordinates> frontier = new LinkedList<Coordinates>();
        int[][] wallMap = copyWalls();
        int numGoals = 0;
        int numBoxes = 0;
        frontier.add(new Coordinates(player_x, player_y));
        wallMap[player_x][player_y] = 1;
        while (!frontier.isEmpty()) {
            Coordinates curr = frontier.poll();
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
                frontier.add(new Coordinates(currX - 1, currY));
                wallMap[currX - 1][currY] = 1;
            }
            if (wallMap[currX + 1][currY] == 0) {
                frontier.add(new Coordinates(currX + 1, currY));
                wallMap[currX + 1][currY] = 1;
            }
            if (wallMap[currX][currY - 1] == 0) {
                frontier.add(new Coordinates(currX, currY - 1));
                wallMap[currX][currY - 1] = 1;
            }
            if (wallMap[currX][currY + 1] == 0) {
                frontier.add(new Coordinates(currX, currY + 1));
                wallMap[currX][currY + 1] = 1;
            }
        }
        return false;
    }

    /**
     * Copies the walls. Used for the connectedMap search
     * 
     * @return the grid with all walls drawn.
     */
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

    /**
     * Private auxiliary class to assist with map connected search 
     * 
     * @author Group 1 Tutorial H14A
     *
     */
    private class Coordinates {
        private int x;
        private int y;

        /**
         * Constructor of a Coordinate pair
         * 
         * @param x as x coord
         * @param y as y coord
         */
        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        /**
         * Gets the x coordinate
         * 
         * @return x coord
         */
        public int getX() {
            return x;
        }

        /**
         * Gets the y coordinate
         * 
         * @return y coord
         */
        public int getY() {
            return y;
        }
    }
}
