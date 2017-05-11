
public class GameSettings {
	private boolean enableMusic;
	private boolean enableSFX;
	private boolean isCharacterRed; //Set character color: True for Red; False for Blue
	private String spriteSet;//the location of the sprite set being used
	
	public GameSettings() {
		enableMusic = true;
		enableSFX = true;
		isCharacterRed = true;
		spriteSet = "images/";
	}
	
	public boolean isEnableMusic() {
		return enableMusic;
	}

	public void setEnableMusic(boolean b) {
		this.enableMusic = b;
	}

	public boolean isEnableSFX() {
		return enableSFX;
	}

	public void setEnableSFX(boolean b) {
		this.enableSFX = b;
	}

	public String getSpriteSet() {
		return spriteSet;
	}

	public void setSpriteSet(String spriteSet) {
		this.spriteSet = spriteSet;
	}
	
	public boolean isCharacterColorRed(){
		return isCharacterRed;
	}
	
	public void setIsCharacterRed(boolean enableRed){
		isCharacterRed = enableRed;
	}
	
}
