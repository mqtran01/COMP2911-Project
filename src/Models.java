
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
     * Sets the new map to be played
     * 
     * @param map as the new map object
     */
    public void setMap(MapModel map) {
        this.map = map;
        this.resetMap = map.clone();
        this.undoMap = map.clone();
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
     */
    public void moveLeft() {
        undoMap = map.clone();
        map.moveLeft();
    }

    /**
     * Intermediate handler to move character right
     * 
     */
    public void moveRight() {
        undoMap = map.clone();
        map.moveRight();
    }

    /**
     * Intermediate handler to move character up
     * 
     */
    public void moveUp() {
        undoMap = map.clone();
        map.moveUp();
    }

    /**
     * Intermediate handler to move character down
     * 
     */
    public void moveDown() {
        undoMap = map.clone();
        map.moveDown();
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
     * Gets the level of the map
     * 
     * @return level of map
     */
    public int getMapLevel() {
    	return map.getMapLevel();
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
    public void setNumLevelsCleared(int level) {
        settings.setNumLevelsCleared(level);
    }

}
