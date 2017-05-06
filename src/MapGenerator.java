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
                return "111" + 
                       "010" + 
                       "111";
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
    public int[][] createMapMatrix(int seed, int rotation) {
        int[][] grid = new int[3][3];
        String mapString = createTemplate(seed);
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
}
