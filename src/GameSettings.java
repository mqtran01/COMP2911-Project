import java.io.Serializable;

public class GameSettings implements Serializable{
	private boolean enableMusic;
	private boolean enableSFX;
	private boolean isSkin1; //Set Skin skin: True for 1; False for 2
	private String spriteSet; //the location of the sprite set being used

	public GameSettings() {
		enableMusic = true;
		enableSFX = true;
		isSkin1 = true;
		spriteSet = "Star Warehouse/";
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

	public boolean isSkin1(){
		return isSkin1;
	}

	public void setSkin(boolean enable){
		isSkin1 = enable;
	}

}
