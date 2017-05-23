import java.util.LinkedList;

public class aStarNode {
	private int h;
	private int f;
	private Map map;
	
	
	public aStarNode(int h, int f, Map map){
		this.h=h;
		this.f=f;
		this.map=map;
	}
	
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
	public int compareTo(aStarNode n) {
		return Integer.compare(f, n.f);
	}
	
	
	/*public static void main(String[] args){
		Map firstMap = new Map('a');
		Map secondMap = new Map('a');
		
		LinkedList<Map> visited = new LinkedList<Map>();
		visited.add(firstMap);
		System.out.println("first map in visited " + visited.contains(secondMap));
		
		for (Map map: visited){
			if (map.equals(secondMap)){
				System.out.println("found a copy");
			}
		}
		
		System.out.println("first map equals second: " + firstMap.equals(secondMap));
	}*/
}
