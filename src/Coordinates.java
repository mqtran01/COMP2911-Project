
public class Coordinates {
	int x;
	int y;
	
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o){//TODO:complete this
		if (this == o) return true;
		if (o == null) return false;
		if (!(o instanceof Coordinates)) return false;
		Coordinates other = (Coordinates) o;
		if (x != other.x) return false;
		if (y != other.y) return false;
		return false;
	}
}
