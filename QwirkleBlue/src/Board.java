import java.util.HashMap;
import java.util.Vector;
public class Board {
	HashMap<Integer, Tile> board = new HashMap<Integer, Tile>();
	Board(){
		for(int i = 0; i < 10; i++){
			
		}
	}
	 
	public String toString(){
		return board.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(new Board());
	}
}
