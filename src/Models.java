
public class Models {
    private MapModel map;
    private MapModel undoMap;
    private MapModel resetMap;
    private SettingsModel settings;
    GamePanel gamePanel;

    /**
     * Main model handler for the game for sounds and skins
     * 
     * @param settings as the game settings
     */
    public Models(SettingsModel settings) {
        this.settings = settings;
        this.undoMap = null;
        this.resetMap = null;

    }

    public SettingsModel getSettings() {
        return settings;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setMap(MapModel map) {
        this.map = map;
        this.resetMap = map.clone();
    }

    public boolean winState(){
    	return map.winState();
    }
    
    public boolean moveLeft() {
        undoMap = map.clone();
        return map.moveLeft();
    }

    public boolean moveRight() {
        undoMap = map.clone();
        return map.moveRight();
    }

    public boolean moveUp() {
        undoMap = map.clone();
        return map.moveUp();
    }

    public boolean moveDown() {
        undoMap = map.clone();
        return map.moveDown();
    }

    public int getTile(int x, int y) {
        return map.getTile(x, y);
    }
    
    public MapModel getMap(){
    	return map;
    }
    
    public int getLength() {
        return map.getLength();
    }

    public int getHeight() {
        return map.getHeight();
    }
    
    public int getMapLevel() {
    	return map.getMapLevel();
    }

    public void reset() {
    	if (resetMap != null){
    		map = resetMap.clone();
    	}
    }

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
     * @param b
     *            as new setting
     */
    public void setSkin(boolean b) {
        settings.setSkin(b);
    }

    public int getNumLevelsCleared() {
        return settings.getNumLevelsCleared();
    }

    public void setNumLevelsCleared(int level) {
        settings.setNumLevelsCleared(level);
    }

}