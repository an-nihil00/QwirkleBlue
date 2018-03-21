import java.util.*;
public class Move {    
  	boolean asapSwappy;
	Move(boolean swap, Placement[] place){
    	this.aList = new ArrayList<Placement>(Arrays.asList(place));
      	asapSwappy=swap;
  	}
	Move(Placement[] place, boolean swap){
   	 this.aList = new ArrayList<Placement>(Arrays.asList(place));
    	asapSwappy=swap;
	}
	ArrayList<Placement> aList= new ArrayList<Placement>();
  	public void addPlacement(Placement place, boolean swap){
      	aList.add(place);
  	}
  	public Placement getAPlacement(int whichOne){
      	return aList.get(whichOne);
  	}
  	public ArrayList<Placement> getList(){
      	return aList;
  	}
  	public int getListSize(){
      	return aList.size();
  	}
  	public boolean isSwap(){
      	return asapSwappy;
  	}
 	 
  	public Move undo() { // Returns a copy of this move, with asapSwappy intact, sans the most recently added placement
      	ArrayList<Placement> bList = new ArrayList<Placement>();
      	for(Placement p : aList) {
          	bList.add(p);
      	}
      	bList.remove(bList.size()-1);
      	return new Move(asapSwappy,(Placement[]) bList.toArray());
  	}
 	 
}
 
 


 
 

 
 
 







