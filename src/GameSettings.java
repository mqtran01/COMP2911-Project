
public class GameSettings {
	private boolean enableMusic;
	private boolean enableSFX;
	private String spriteSet;//the location of the sprite set being used
	
	public void GameSetings(){
		enableMusic = true;
		enableSFX = true;
		spriteSet = "images/";
	}
	
	public boolean isEnableMusic() {
		return enableMusic;
	}

	public void setEnableMusic(boolean enableMusic) {
		System.out.println("called set enable music");
		this.enableMusic = enableMusic;
	}

	public boolean isEnableSFX() {
		return enableSFX;
	}

	public void setEnableSFX(boolean enableSFX) {
		this.enableSFX = enableSFX;
	}

	public String getSpriteSet() {
		return spriteSet;
	}

	public void setSpriteSet(String spriteSet) {
		this.spriteSet = spriteSet;
	}
	
}
