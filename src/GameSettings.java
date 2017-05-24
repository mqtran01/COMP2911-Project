import java.io.Serializable;

/**
 * Game Settings class containing layout and controllers
 * Used to control game skins and sounds
 * @author Group 1 Tutorial H14A
 *
 */
public class GameSettings implements Serializable {
	private boolean enableMusic;
	private boolean enableSFX;
	private boolean isSkin1; //Set Skin skin: True for 1; False for 2
	private String spriteSet; //the location of the sprite set being used
	private int numLevelsCleared;

	/**
	 * Constructor of the Game Settings object
	 */
	public GameSettings() {
		enableMusic = true;
		enableSFX = true;
		isSkin1 = true;
		spriteSet = "Star Warehouse/";
		numLevelsCleared = 0;
	}

	/**
	 * Gets enableMusic setting
	 * @return enableMusic
	 */
	public boolean isEnableMusic() {
		return enableMusic;
	}

	/**
	 * Sets enableMusic setting
	 * @param b as the new setting
	 */
	public void setEnableMusic(boolean b) {
		this.enableMusic = b;
	}
	
	/**
	 * Gets enableSFX setting
	 * @return enableSFX
	 */
	public boolean isEnableSFX() {
		return enableSFX;
	}

	/**
	 * Sets enableSFX setting
	 * @param b as the new setting
	 */
	public void setEnableSFX(boolean b) {
		this.enableSFX = b;
	}

	/**
	 * Gets the current sprite set
	 * @return spriteSet
	 */
	public String getSpriteSet() {
		return spriteSet;
	}

	/**
	 * Sets the new spiteSet
	 * @param spriteSet as the new spriteSet
	 */
	public void setSpriteSet(String spriteSet) {
		this.spriteSet = spriteSet;
	}

	/**
	 * Gets whether skin1 is being used
	 * @return isSkin1
	 */
	public boolean isSkin1(){
		return isSkin1;
	}

	/**
	 * Sets isSkin1 to new setting
	 * @param b as new setting
	 */
	public void setSkin(boolean b){
		isSkin1 = b;
	}
	
	public int getNumLevelsCleared() {
		return numLevelsCleared;
	}
	
	public void setNumLevelsCleared() {
		numLevelsCleared++;
	}

}
