import java.util.ArrayList;
import java.util.Iterator;

public class MapGenerator {
    final int WALL = 0;
    final int EMPTY = 1;
    final int PLAYER = 2;
    final int BOX = 3;
    final int GOAL = 4;
    final int GOALBOX = 5;
    final int GOALPLAYER = 6;
    
    /*grid internal representation
     * 0: wall
     * 1: empty space
     * 2: player (and hence empty space once it has moved)
     * 3: box (and hence empty space once it is moved)
     * 4: goal (and hence space that can be moved into)
     * 5: goal+box
     * 6: player+goal
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
                return "011" + 
                       "111" + 
                       "111";
            case 3:
                return "011" + 
                       "111" + 
                       "111";
            case 4:
                return "011" + 
                       "111" + 
                       "111";
            case 5:
                return "011" + 
                       "111" + 
                       "111";
            case 6:
                return "011" + 
                       "111" + 
                       "111";
            case 7:
                return "011" + 
                       "111" + 
                       "111";
            case 8:
                return "011" + 
                       "111" + 
                       "111";
            case 9:
                return "011" + 
                       "111" + 
                       "111";
            default:
                return "011" + 
                       "111" + 
                       "111";
        }
    }
    
    public int[][] createMapMatrix(int seed) {
        int[][] grid = new int[3][3];
        String mapString = createTemplate(seed);
        int i = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                grid[x][y] = Character.getNumericValue(mapString.charAt(i));
                i++;
            }
        }
        return grid;
    }
    
    public int[][] mergeTemplates(ArrayList<int[][]> maps, int length, int height) {
        int xOffset = 0;
        int yOffset = 0;
        Iterator<int[][]> iMaps = maps.iterator();
        
        int[][] joined = new int[3*length][3*height];
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
            yOffset = 0;
            
        }
        return joined;
    }
}
