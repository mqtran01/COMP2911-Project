/**
 * Helper class to store a coordinate datum.
 * @author Group 1 Tutorial H14A
 *
 */
public class Coordinate {
    private int x;
    private int y;
    
    /**
     * Constructor of a coordinate
     * @param x as the x coord
     * @param y as the y coord
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Gets the x coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }
    
}
