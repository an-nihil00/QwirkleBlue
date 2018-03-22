import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
@SuppressWarnings("unused")
public class Board implements Cloneable{
	HashMap <Tuple, Boolean> confirmedState;
	HashMap<Tuple,Tile> returnTile;
	Stack<Placement> currentMove;
	Game game;
	public Board() {
		confirmedState = new HashMap<Tuple, Boolean>();
		returnTile = new HashMap<Tuple,Tile>();
		currentMove = new Stack<Placement>();
	}
	public void setGame (Game g) {
		game = g;
	}
	public ArrayList<Tile> tilesInRow(Placement p){ // Contiguous tiles, it should be pointed out
		int x = p.getX();
		int y = p.getY();

		ArrayList<Tile> out = new ArrayList<Tile>();
		int l = 1;
		int r = 1;
		Tile left = getTile(x-l,y);
		Tile right = getTile(x+r,y);

		while(left != null){
			out.add(left);
			l++;
			left = getTile(x-l,y);
		}

		while(right != null){
			out.add(right);
			r++;
			right = getTile(x+r,y);
		}
		return out;
	}

	public ArrayList<Tile> tilesInColumn(Placement p){
		int x = p.getX();
		int y = p.getY();

		ArrayList<Tile> out = new ArrayList<Tile>();
		int u = 1;
		int d = 1;
		Tile up = getTile(x,y-u);
		Tile down = getTile(x,y+d);

		while(up != null){
			out.add(up);
			u++;
			up = getTile(x,y-u);
		}

		while(down != null){
			out.add(down);
			d++;
			down = getTile(x,y+d);
		}

		return out;
	}

	public Tile getTile(int x, int y){
		return returnTile.get(new Tuple(x,y));
	}

	public int getMaxX(){
		int maxX = Integer.MIN_VALUE;
		Set<Tuple> s = returnTile.keySet();
		if(s.size() == 0) {
			return 0;
		}
		for(Tuple x : s) {
			if(x.x > maxX)
				maxX = x.x;
		}
		return maxX;
	}
	public int getMaxY(){
		int maxY = Integer.MIN_VALUE;
		Set<Tuple> s = returnTile.keySet();
		if(s.size() == 0) {
			return 0;
		}
		for(Tuple x : s) {
			if(x.y > maxY)
				maxY = x.y;
		}
		return maxY;
	}
	public int getMinX(){
		int minX = Integer.MAX_VALUE;
		Set<Tuple> s = returnTile.keySet();
		if(s.size() == 0) {
			return 0;
		}
		for(Tuple x : s) {
			if(x.x < minX)
				minX = x.x;
		}
		return minX;
	}
	public int getMinY(){
		int minY = Integer.MAX_VALUE;
		Set<Tuple> s = returnTile.keySet();
		if(s.size() == 0) {
			return 0;
		}
		for(Tuple x : s) {
			if(x.y < minY)
				minY = x.y;
		}
		return minY;
	}

	public int getScore(int i){
		return 0;
	}

	public int scoreOfOneTile(Placement p, int exclude){//what about the first placement?? 
		int score=0;

		ArrayList<Tile> tilesInRow=tilesInRow(p);
		ArrayList<Tile> tilesInColumn=tilesInColumn(p);
		if (exclude==0){
			score=tilesInRow.size()+tilesInColumn.size();
			if (tilesInRow.size()>0 && tilesInColumn.size()>0){//get two points if the tile is both in a row and a column
				score=score+2;
			} else if (tilesInRow.size()>0 || tilesInColumn.size()>0){//gets one if just in one
				score++;
			}
		} else if (exclude==1){//excludes the row
			score=tilesInColumn.size();
			if (tilesInColumn.size()>0){
				score++;
			} 
		} else {//excludes the column
			score=tilesInRow.size();
			if (tilesInRow.size()>0){
				score++;
			}
		}
		return score;
	}
	public boolean canSwap() {
		boolean ans = true;
		for(Placement p:currentMove) {
			ans &= p.getX() == -255;
		}
		return ans;
	}
	public void addSwap(Tile t) {
		currentMove.add(new Placement(-255,-255,t));
	}
	public void playMove(Move m) {
		if(m.getAPlacement(0).getX()==-255) {
			ArrayList<Tile> swapTiles = new ArrayList<Tile>();
			for(Placement p: m.getList()) {
				swapTiles.add(p.getTile());
			}
			game.fixHand();
			for(Tile t:swapTiles) {
				game.returnTile(t);
			}
		}
		else {
			try {
				for(Placement p: m.getList()) {
					playTile(p);
				}
			}
			catch(Exception ex){
				while(currentMove.size()>0) {
					currentMove.pop();
				}
			}
		}
	}
	public void confirmMove() {
		if(currentMove.peek().getX() == -255) {
			ArrayList<Tile> swapTiles = new ArrayList<Tile>();
			for(Placement p: currentMove) {
				swapTiles.add(p.getTile());
			}
			game.fixHand();
			for(Tile t:swapTiles) {
				game.returnTile(t);
			}
		}
		else {
			for(Placement p:currentMove) {
				Tuple xy = new Tuple(p.getX(),p.getY());
				this.confirmedState.put(xy, true);
			}
		}
		currentMove = new Stack<Placement>();
	}
	public boolean isLegal(Placement p) {
		if(returnTile.keySet().size() == 0  && currentMove.size() == 0) {
			return true;
		}
		if(returnTile.get(new Tuple(p.getX(),p.getY())) != null){
			return false;
		}
		boolean sameColRow = true;
		boolean samePatRow = true;
		boolean sameColCol = true;
		boolean samePatCol = true;
		boolean sameRowCur = true;
		boolean sameColCur = true;
		ArrayList<Tile> inRow = tilesInRow(p);
		ArrayList<Tile> inCol = tilesInColumn(p);
		if(inRow.size() == 0 && inCol.size() == 0) {
			return false;
		}
		for(Tile t : inCol) {
			if(t.equals(p.getTile())){
				return false;
			}
			sameColCol &= p.getTile().getColour() == t.getColour();
			samePatCol &= p.getTile().getPattern() == t.getPattern();
		}
		for(Tile t : inRow) {
			if(t.equals(p.getTile())){
				return false;
			}
			sameColRow &= p.getTile().getColour() == t.getColour();
			samePatRow &= p.getTile().getPattern() == t.getPattern();
		}

		for(Placement place : currentMove) {
			sameRowCur &= inRow.contains(place.getTile());
			sameColCur &= inCol.contains(place.getTile());
		}
		return (sameColRow || samePatRow) && (sameColCol || samePatCol) && (sameRowCur || sameColCur);
	}
	public Tile undo() {
		Placement p = currentMove.pop();
		returnTile.remove(new Tuple(p.getX(),p.getY()));
		return p.getTile();
	}
	public Stack<Placement> getCurrentMove(){
		return currentMove;
	}
	public void playTile(Placement place) throws Exception {
		if(isLegal(place)) {
			returnTile.put(new Tuple(place.getX(),place.getY()), place.getTile());
			confirmedState.put(new Tuple(place.getX(),place.getY()), false);
			currentMove.push(place);
		}
		else {
			throw(new Exception());
		}
	}
	public void placeTile(Placement place){
		this.returnTile.put(new Tuple(place.getX(),place.getY()), place.getTile());
	}
	public Board clone() throws CloneNotSupportedException{
		return (Board) super.clone();
	}

	public boolean getConfirmed(int i, int j) {
		return confirmedState.get(new Tuple(i,j));
	}
}




