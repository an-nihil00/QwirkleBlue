//changes made 
import java.util.ArrayList;
import java.util.Stack;

public class HardAI extends Player{
	HardAI(String name) {
		super(name);
	}
	public Move getMove(Board board) {
		return null;
	}
	private ArrayList<Placement> getAllPlacements(Board b){
		ArrayList<Placement> ans = new ArrayList<Placement>();
		for(int x=b.getMinX()-1;x<=b.getMaxX();x++) {
			for(int y=b.getMinX()-1;y<=b.getMaxX();y++) {
				for(Tile t:this.getHand()) {
					if(b.isLegalP(new Placement(x,y,t))) {
						ans.add(new Placement(x,y,t));
					}
				}
			}
		}
		return ans;
	}
	
	private ArrayList<Placement> getSuccesorPlacements(Board b, Move m){
		ArrayList<Placement> ans = new ArrayList<Placement>();
		Board t = (Board) b.clone();
		t.placeMove(m);
		Stack<Placement> nm = new Stack<Placement>();
		for(Placement p:m.getList()) {
			nm.push(p);
		}
		for(Placement p:getAllPlacements(t)) {
			nm.push(p);
			if(b.checkingLegalityOfTheMove(new Move(stack.toArray(nm)))) {
				ans.add(p);
			}
			nm.pop();
		}
		return ans;
	}
	private ArrayList<Move> getMovesN(Board b,int n){
		ArrayList<Move> ans = new ArrayList<Move>();
		if(n==1) {
			for(Placement p:getAllPlacements(b)) {
				Placement[] m = {p};
				ans.add(new Move(m,false));
			}
			return ans;
		}
		else {
			for(Move m:getMovesN(b,n-1)){
				Stack<Placement> nm = new Stack<Placement>();
				for(Placement p:m.getList()) {
					nm.push(p);
				}
				for(Placement p:getSuccesorPlacements(b,m)) {
					nm.push(p);
					ans.add(new Move(stack.toArray(nm)));
					nm.pop();
				}
			}
			return ans;
		}
	}


}
