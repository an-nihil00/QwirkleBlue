import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
@SuppressWarnings("unused")
public class Board implements Cloneable{
	HashMap <Tuple, Boolean> confirmedState = new HashMap<Tuple, Boolean>();
	HashMap<Tuple,Tile> returnTile = new HashMap<Tuple,Tile>();
	
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
	    	for(Tuple x : s) {
	   		 if(x.x > maxX)
	   			 maxX = x.x;
	    	}
	    	return maxX;
		}
		public int getMaxY(){
	   	 int maxY = Integer.MIN_VALUE;
	    	Set<Tuple> s = returnTile.keySet();
	    	for(Tuple x : s) {
	   		 if(x.y > maxY)
	   			 maxY = x.y;
	    	}
	    	return maxY;
		}
		public int getMinX(){
	   	 int minX = Integer.MAX_VALUE;
	    	Set<Tuple> s = returnTile.keySet();
	    	for(Tuple x : s) {
	   		 if(x.x < minX)
	   			 minX = x.x;
	    	}
	    	return minX;
		}
		public int getMinY(){
	   	 int minY = Integer.MAX_VALUE;
	    	Set<Tuple> s = returnTile.keySet();
	    	for(Tuple x : s) {
	   		 if(x.y < minY)
	   			 minY = x.y;
	    	}
	    	return minY;
		}

	public int getScore(Move m){
		int score=0;
		
		if (checkingLegalityOfTheMove(m)==false || m.getListSize()==0){//no points, not legal
			return 0;
		} else if (m.getListSize()==1){
			score=scoreOfOneTile(m.getAPlacement(0),0);
		} else {
			if (m.getAPlacement(0).getX()==m.getAPlacement(1).getX()){
				ArrayList<Tile> tilesInRow=tilesInRow(m.getAPlacement(0));
				score=score+tilesInRow.size()+1;//automatically counts all the placements in the move that are placed together(plus one is for the placement is called on)
				for (int i=0; i<m.getListSize(); i++){
					score=score+scoreOfOneTile(m.getAPlacement(i), 1);
				}
			} else {
				ArrayList<Tile> tilesInColumn=tilesInColumn(m.getAPlacement(0));
				score=score+tilesInColumn.size()+1;//automatically counts all the placements in the move that are placed together
				for (int i=0; i<m.getListSize(); i++){
					score=score+scoreOfOneTile(m.getAPlacement(i), 2);
				}
			}
		} return score;
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

	public boolean checkingLegalityOfTheMove(Move m){
		boolean isLegal=true;
		
		//maybe dont want to pass in a full move
		Placement currentPlacement=m.getAPlacement(0); 
		int firstPlacementX=currentPlacement.getX();
		int firstPlacementY=currentPlacement.getY();
		int currentPlacementX=currentPlacement.getX();
		int currentPlacementY=currentPlacement.getY();
		ArrayList<Tile> tilesInRow=tilesInRow(currentPlacement);//does not return current tile being passed in 
		ArrayList<Tile> tilesInColumn=tilesInColumn(currentPlacement);
		int colorOfTile=currentPlacement.getTile().getColour();
		int patternOfTile=currentPlacement.getTile().getPattern();
		final int COLOR=0;
		final int PATTERN=1;
		int whatWeAreCheckingForInRow=-1;
		int whatWeAreCheckingForInColumn=-1;
		boolean allInSameRow=false;
		
		for (int nthMove=0; nthMove<m.getListSize(); nthMove++){
			currentPlacement=m.getAPlacement(nthMove);
			currentPlacementX=currentPlacement.getX();
			currentPlacementY=currentPlacement.getY();
			tilesInRow=tilesInRow(currentPlacement);
			tilesInColumn=tilesInColumn(currentPlacement);
			colorOfTile=currentPlacement.getTile().getColour();
			patternOfTile=currentPlacement.getTile().getPattern();
			if (nthMove==1){
				if (firstPlacementX==currentPlacementX){
					allInSameRow=true;
				} else if (firstPlacementY==currentPlacementY){
					allInSameRow=false;
				} else {
					return false;
				}
			}
			if (nthMove>1){
				if (allInSameRow==true){
					if (firstPlacementX!=currentPlacementX){
						return false;
					}
				} if (allInSameRow==false){
					if (firstPlacementY!=currentPlacementY){
						return false;
					}
				}
			}
			for (int placeInRow=0; placeInRow<tilesInRow.size(); placeInRow++){
				if (tilesInRow.get(placeInRow).getColour()==colorOfTile && tilesInRow.get(placeInRow).getPattern()==patternOfTile){//both are the same! exact same tile, not allowed
					return false;
				} else if (tilesInRow.get(placeInRow).getColour()==colorOfTile && whatWeAreCheckingForInRow!=1){
					if (whatWeAreCheckingForInRow==-1){
						whatWeAreCheckingForInRow=COLOR;
					}
				} else if (tilesInRow.get(placeInRow).getPattern()==patternOfTile && whatWeAreCheckingForInColumn!=0){
					if (whatWeAreCheckingForInRow==-1){
						whatWeAreCheckingForInRow=PATTERN;
					}
				} else { //neither color nor pattern the same
					return false;
				}
			}
			
			for (int placeInColumn=0; placeInColumn<tilesInColumn.size(); placeInColumn++){
				if (tilesInColumn.get(placeInColumn).getColour()==colorOfTile && tilesInColumn.get(placeInColumn).getPattern()==patternOfTile){//both are the same! exact same tile, not allowed
					return false;
				} else if (tilesInColumn.get(placeInColumn).getColour()==colorOfTile && whatWeAreCheckingForInColumn!=1){
					if (whatWeAreCheckingForInColumn==-1){
						whatWeAreCheckingForInColumn=COLOR;
					}
				} else if (tilesInColumn.get(placeInColumn).getPattern()==patternOfTile && whatWeAreCheckingForInColumn!=0){
					if (whatWeAreCheckingForInColumn==-1){
						whatWeAreCheckingForInColumn=PATTERN;
					}
				} else { //neither color nor pattern the same
					return false;
				}
			}
		}
		return true;
	} 
		public void displayMove(Move m){
    	if (checkingLegalityOfTheMove(m)){
        	for(int i=0; i<m.getListSize(); i++){
            	Placement p=m.getAPlacement(i);
            	Set<Tuple> s = confirmedState.keySet(); //Remove all unconfirmed tiles
            	for(Tuple t : s) {
           		 if(!confirmedState.get(t)) {
           			 confirmedState.remove(t);
           			 returnTile.remove(t);
           		 }
            	}
           	 
            	confirmedState.put(new Tuple(p.getX(),p.getY()), false);//not officially confirmed
            	returnTile.put(new Tuple(p.getX(),p.getY()), p.getTile());
        	}
    	}
	}
	public void confirmMove(Move m) throws Exception{
		if(checkingLegalityOfTheMove(m)){
			for(int i=0; i<m.getListSize(); i++){
				Placement p=m.getAPlacement(i);
				confirmedState.put(new Tuple(p.getX(),p.getY()), true);
			}
		}else {
			throw new Exception();
		}
	}
	
	public void placeMove(Move m){
		if(this.checkingLegalityOfTheMove(m)){
			for(Placement p : m.getList()){
				this.placeTile(p);
			}
			try {
				confirmMove(m);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//FIXME return statements break out of method before checking other conditions  
	public boolean isLegalP(Placement p) {
		if(returnTile.get(new Tuple(p.getX(),p.getY())) != null){
			return false;
		}
		boolean sameColRow = true;
		boolean samePatRow = true;
		boolean sameColCol = true;
		boolean samePatCol = true;
		for(Tile t : this.tilesInColumn(p)) {
			if(t.equals(p.getTile())){
				return false;
			}
			sameColCol &= p.getTile().getColour() == t.getColour();
			samePatCol &= p.getTile().getPattern() == t.getPattern();
		}
		for(Tile t : this.tilesInRow(p)) {
			if(t.equals(p.getTile())){
				return false;
			}
			sameColRow &= p.getTile().getColour() == t.getColour();
			samePatRow &= p.getTile().getPattern() == t.getPattern();
		}
		return (sameColRow || samePatRow) && (sameColCol || samePatCol);
	}
	
	//TODO don't delete this maybe?
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


