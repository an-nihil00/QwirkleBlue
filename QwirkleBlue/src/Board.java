import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
@SuppressWarnings("unused")
public class Board {
	
	//uses a 2 element integer array{x,y} as keys that maps to a resulting tile
	HashMap<int[], Tile> board;
	int[] coordinate = new int[2];
	Board(){
		board = new HashMap<int[], Tile>();
	}
	 
	public String toString(){
		return board.toString();
	}
	  
	public Tile getTile(int x, int y) {
		//x= column, y = row
		coordinate = new int[]{x,y};
		if(board.containsKey(coordinate))
			return board.get(coordinate);
		return null;
	}
	 
	public void setTile(int x, int y, Tile tile) {
		//coordinate = new int[] {x,y};
		board.put(new int[] {x,y}, tile);
	}
	
	public int hashFunction(int x, int y) {
		int radius= Math.max(Math.abs(x), Math.abs(y));
		ArrayList<Integer> range = new ArrayList<Integer>();
		for(int i = (int) (Math.pow((2*(radius-1)+1),2)-1); i < Math.pow((2*radius+1),2);i++) {
			range.add(i);
		}
		
		int sideLength = (2*radius -1);
		
		return 0;
	}
	
	public static void main(String[] args) {
		Board bo = new Board();
		//bo.setTile(2, 3, new Tile(3,2));
		bo.board.put(new int[] {0,0}, new Tile(3,2));
		System.out.println(bo.board.keySet());
		System.out.println(bo.getTile(0, 0));
		System.out.println(bo);
	}
}
