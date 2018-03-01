import java.util.HashMap;
public class Board {
	
	//uses a 2d integer array{x,y} as keys that maps to a resulting tile
	HashMap<int[], Tile> board;
	Board(){
		board = new HashMap<int[], Tile>();
	}
	 
	public String toString(){
		return board.toString();
	}
	 
	public Tile getTile(int x, int y) {
		//x= column, y = row
		int[] coordinate = {x,y};
		if(board.containsKey(coordinate))
			return board.get(coordinate);
		return null;
	}
	 
	public void setTile(int x, int y, Tile tile) {
		if(!board.containsKey(new int[] {x,y}))
			board.put(new int[]{x,y},tile);
	}
	
	public static void main(String[] args) {
		Board bo = new Board();
		bo.setTile(2, 3, new Tile(3,2));
		System.out.println(bo);
	}
}
