
/**
 * Main model handler for the game for sounds and skins 
 * 
 * @author Group 1 Tutorial H14A
 *
 */
public class Models {
    private MapModel map;
    private MapModel undoMap;
    private MapModel resetMap;
    private SettingsModel settings;
    GamePanel gamePanel;

    /**
     * Constructor of the Models object
     * 
     * @param settings as the game settings
     */
    public Models(SettingsModel settings) {
        this.settings = settings;
        this.undoMap = null;
        this.resetMap = null;
    }

    /**
     * Gets the current settings
     * 
     * @return the settings
     */
    public SettingsModel getSettings() {
        return settings;
    }

    /**
     * Sets the current game panel
     * 
     * @param gamePanel as the current game panel to add
     */
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Sets the new map to be played
     * 
     * @param map as the new map object
     */
    public void setMap(MapModel map) {
        this.map = map;
        this.resetMap = map.clone();
    }

    /**
     * Intermediate handler to determine win state
     * 
     * @return boolean whether game is won
     */
    public boolean winState(){
    	return map.winState();
    }
    
    /**
     * Intermediate handler to move character left
     * 
     * @return true if character moved successfully
     */
    public boolean moveLeft() {
        undoMap = map.clone();
        return map.moveLeft();
    }

    /**
     * Intermediate handler to move character right
     * 
     * @return true if character moved successfully
     */
    public boolean moveRight() {
        undoMap = map.clone();
        return map.moveRight();
    }

    /**
     * Intermediate handler to move character up
     * 
     * @return true if character moved successfully
     */
    public boolean moveUp() {
        undoMap = map.clone();
        return map.moveUp();
    }

    /**
     * Intermediate handler to move character down
     * 
     * @return true if character moved successfully
     */
    public boolean moveDown() {
        undoMap = map.clone();
        return map.moveDown();
    }

    /**
     * Intermediate handler to get object at tile
     * 
     * @param x as x coord
     * @param y as y coord
     * @return the element at the tile
     */
    public int getTile(int x, int y) {
        return map.getTile(x, y);
    }
    
    /**
     * Gets the current map being played
     * 
     * @return the map
     */
    public MapModel getMap(){
    	return map;
    }
    
    /**
     * Gets the length of the map
     * 
     * @return length of map
     */
    public int getLength() {
        return map.getLength();
    }

    /**
     * Gets the height of the map
     * 
     * @return height of map
     */
    public int getHeight() {
        return map.getHeight();
    }
    
    /**
     * Resets the current map to the original map
     * where it initially started from
     */
    public void reset() {
    	if (resetMap != null){
    		map = resetMap.clone();
    	}
    }

    /**
     * Undos the last move made
     */
    public void undo() {
    	if (undoMap != null){
    		map = undoMap;
    	}
    }

    /**
     * Gets enableMusic setting
     * 
     * @return enableMusic
     */
    public boolean isEnableMusic() {
        return settings.isEnableMusic();
    }

    /**
     * Sets enableMusic setting
     * 
     * @param b as the new setting
     */
    public void setEnableMusic(boolean b) {
        settings.setEnableMusic(b);
    }

    /**
     * Gets enableSFX setting
     * 
     * @return enableSFX
     */
    public boolean isEnableSFX() {
        return settings.isEnableSFX();
    }

    /**
     * Sets enableSFX setting
     * 
     * @param b as the new setting
     */
    public void setEnableSFX(boolean b) {
        settings.setEnableSFX(b);
    }

    /**
     * Gets the current sprite set
     * 
     * @return spriteSet
     */
    public String getSpriteSet() {
        return settings.getSpriteSet();
    }

    /**
     * Sets the new spiteSet
     * 
     * @param spriteSet
     *            as the new spriteSet
     */
    public void setSpriteSet(String spriteSet) {
        settings.setSpriteSet(spriteSet);
    }

    /**
     * Gets whether skin1 is being used
     * 
     * @return isSkin1
     */
    public boolean isSkin1() {
        return settings.isSkin1();
    }

    /**
     * Sets isSkin1 to new setting
     * 
     * @param b as new setting
     */
    public void setSkin(boolean b) {
        settings.setSkin(b);
    }

    /**
     * Gets the number of levels cleared so far
     * 
     * @return number of completed levels
     */
    public int getNumLevelsCleared() {
        return settings.getNumLevelsCleared();
    }

    /**
     * Sets the number of levels cleared in story mode
     */
    public void setNumLevelsCleared() {
        settings.setNumLevelsCleared();
    }

}
