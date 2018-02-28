import java.util.HashMap;

public class Board {
	HashMap<Integer, Integer> board = new HashMap<Integer, Integer>();
	Board(){
		for(int i = 0; i < 10; i++){
			board.put(i, i+1);
		}
	}
	
	public String toString(){
		return board.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new Board());
	}
}
