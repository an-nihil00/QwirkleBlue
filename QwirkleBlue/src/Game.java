
/**
Also need to add something about ending the game and calling the end screen, right now game just sort of stops when there are no more tiles to draw
Rules of the game state: 
When there are no more tiles left, the play continues as before but players don't replenish their decks at the end of their turns.
The first player who uses all of his tiles ends the game and gets a 6 points bonus.
**/

import java.util.Collections;
import java.util.Stack;
public class Game {
	Player[] players;
	int currentTurn; //make it an int?
	Tile[] hand;
	Stack<Tile> deck=new Stack<Tile>();
	BoardScreen bs;
	Board board;
	static int activePlayer = 0;
	String[] names;
	//Number of players
	int numPlayers;
	
	/**
	 * @param numPlayers -number of Players
	 * @param names - name of the players
	 * @param playerType - type of players [0 = human, 1 = greedy, 2 = hard]
	 */
	Game(int[] playerType, boolean isSlow){
		for(int i = 0; i < numPlayers; i++){
			if (playerType[i] == 1){
				players[i] = new GreedyAI(names[i]);
			}else if(playerType[i] == 2){
				players[i] = new HardAI(names[i]);
			}
		}
		board=new Board();
		if (isSlow){
			BoardScreen bs=new BoardScreen(board);
		}
		for (int i=0; i<108; i++){
			deck.push(new Tile(i%6, (i/6)%6));
		}
		shuffleDeck();
	}
	
	Game(int numPlayers, String[] names, int[] playerType){
		this.numPlayers = numPlayers;
		players = new Player[numPlayers];
		this.names = names;
		for(int i = 0; i < numPlayers; i++){
			if(playerType[i] == 0){
				players[i] = new HumanPlayer(names[i],bs);
			}else if (playerType[i] == 1){
				players[i] = new GreedyAI(names[i]);
			}else if(playerType[i] == 2){
				players[i] = new HardAI(names[i]);
			}
		}
		board = new Board();
		bs = new BoardScreen(board);
		for (int i=0; i<108; i++){
			deck.push(new Tile(i%6, (i/6)%6));
		}
		shuffleDeck();
	}
	
	public Player[] play(){
		boolean stop=false;
		while (stop==false){
			Move move = players[activePlayer].getMove(board);
			if(move.isSwap()){
				swap(move);
			}
			board.placeMove(move);
			players[activePlayer].updateScore(board.getScore(move));
			//iterate over move, any tiles that were in the move and in the hand should be replaced with a new tile from the deck//not yet done
			if (deck.size()>0){//game needs to get rid of the tiles placed on the board and draw new tiles, add that to a hand, and update the hand
				deck.pop();
			}
		
			if (players[activePlayer].getHand()==null){//no more hands left in the tile 
				stop=true;
			}
			activePlayer +=1;
			activePlayer %= numPlayers;
			if(players[activePlayer] instanceof HumanPlayer) {//if the active player is a human tell the boardScreen to change state?
				bs.updateHand(players[activePlayer]);			
			}
			
		}
		
		return players;
	}
	
	public Tile draw(){
		return deck.pop();
	}
	
	public void swap(Move move){
		Tile[] preHand = players[activePlayer].getHand();
		for(Placement p : move.aList){
			Tile til = p.getTile();
			for(int i = 0; i < 6; i++){
				if(preHand[i].equals(til)){
					deck.push(preHand[i]);
					preHand[i] = draw();
				}
			}
		}
		players[activePlayer].updateHand(preHand);
		shuffleDeck();
	}
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}	
}

