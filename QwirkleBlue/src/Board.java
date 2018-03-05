import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
@SuppressWarnings("unused")
//TODO figure out all the functionality [Methods] of the board class
public class Board {
	
	//uses a 2 element integer array{x,y} as keys that maps to a resulting tile
	HashMap<Key, Tile> board;
	static Key coord;
	int[] coordinate = new int[2];
	Board(){
		coord = new Key(0,1);
		board = new HashMap<Key, Tile>();
	}
	 
	public String toString(){
		return board.toString();
	}
	  
	public Tile getTile(int x, int y) {
		//x= column, y = row
		if(board.containsKey(new Key(x,y)))
			return board.get(new Key(x,y));
		return null;
	}
	 
	public void setTile(int x, int y, Tile tile) {
		board.put(new Key(x,y), tile);
	}
	
	//TODO finish the keyFunction, getRow and getCol
	class Key{
		int x,  y;		
		Key(int x, int y){
			this.x = x;
			this.y = y;
		}
		int interior( Key p ){
		   int a = Math.max( Math.abs( p.x ), Math.abs( p.y ));
		   return ( 2*a - 1 )*( 2*a - 1 );
		}
		
	    Key startKey( Key p ){
	        int a = Math.max( Math.abs( p.x ), Math.abs( p.y ));
	        return new Key( a, -( a-1 ));
	    }
	    
	    int offSetRow1( Key pStart, Key p ){
	        return ( p.y - pStart.y ) + 1;
	    }
		

		public int keyFunction(Key curr){
	        int a = Math.max( Math.abs( curr.x ), Math.abs( curr.y ));
	        int off=0;
	        int interiorCnt = interior( curr );
	        Key start = startKey( curr );

	        if( ( curr.x == a ) && ( curr.y >= start.y ) ){
	            off = offSetRow1( start, curr );
	            return off+interiorCnt;
	        }

	         if( curr.y == a ){
	            Key start2 = new Key( a, a );
	            int off1 = offSetRow1( start, start2 );

	            int off2 = start2.x - curr.x;
	            off = off1 + off2;
	            return off+interiorCnt;

	        }

	        if( curr.x == -a ){
	            Key start2 = new Key( a, a );
	            int off1 = offSetRow1( start, start2 );

	            Key start3 = new Key( -a, a );
	            int off2 = start2.x - start3.x;
	            int off3 = start3.y - curr.y;

	            off = off1 + off2 + off3;
	            return off+interiorCnt;

	        }

	        else{
	            Key start2 = new Key( a, a );
	            int off1 = offSetRow1( start, start2 );

	            Key start3 = new Key( -a, a );
	            int off2 = start2.x - start3.x;
	            int off3 = start3.y - curr.y;

	            Key start4 = new Key( -a, -a );
	            int off4 = curr.x - start4.x;
	            off = off1 + off2 + off3 + off4;
	            return interiorCnt + off;
	        }
		}
		public int getRow(int key){
			return 0;
		}
		
		public int getCol(int key){
			return 0;
		}
	}
	
	public static void main(String[] args) {
		
		
		Board bo = new Board();
		//bo.setTile(2, 3, new Tile(3,2));
		System.out.println("Coordinate "+bo.coord.keyFunction(coord));
		System.out.println(bo.board.keySet());
		System.out.println(bo.getTile(0, 0));
		System.out.println(bo);
	}
}
