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
	boolean waiting = false;
	Thread threadObject;
	
	/**
	 * @param numPlayers -number of Players
	 * @param names - name of the players
	 * @param playerType - type of players [0 = human, 1 = greedy, 2 = hard]
	 */
	/*Game(int[] playerType, boolean isSlow){
		for(int i = 0; i < numPlayers; i++){
			if (playerType[i] == 1){
				players[i] = new HardAI(names[i]);
			}else if(playerType[i] == 2){
				players[i] = new HardAI(names[i]);
			}
		}
		board=new Board();
		if (isSlow){
			BoardScreen bs=new BoardScreen(board);
			bs.setGame(this);
		}
		for (int i=0; i<108; i++){
			deck.push(new Tile(i%6, (i/6)%6));
		}
		shuffleDeck();
	}*/
	
	Game(int numPlayers, String[] names, int[] playerType, BoardScreen bs){
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
		board = bs.getBoard();
		this.bs = bs;
		bs.setGame(this);
		for (int i=0; i<108; i++){
			deck.push(new Tile(i%6, (i/6)%6));
		}
		shuffleDeck();
	}
	public void returnTile(Tile t) {
		deck.push(t);
		shuffleDeck();
	}
	public void setBoard(Board b) {
		board = b;
	}
	
	public Thread getThread() {
		return threadObject;
	}
	public int tilesLeft() {
		return deck.size();
	}
	public int numPlayers() {
		return numPlayers;
	}
	public String playerName(int i) {
		return players[i].getName();
	}
	public int playerScore(int i) {
		return players[i].getScore();
	}
	public Player[] play() throws InterruptedException{
		Runnable play = new Runnable() {
			@Override
			public void run() {
				for(Player p:players) {
					Tile[] newHand = new Tile[6];
					for(int i=0;i<6;i++) {
						newHand[i] = draw();
					}
					p.updateHand(newHand);
				}
				boolean stop=false;
				while (!stop){
					if(players[activePlayer] instanceof HumanPlayer){
						bs.setThinking(true);
						bs.updateHand(players[activePlayer]);
						while(bs.getThinking()){
							synchronized(threadObject) {
								try {
									threadObject.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
					board.confirmMove();
					fixHand();
					if (players[activePlayer].getHand()==null){//no more hands left in the tile 
						stop=true;
					}
					activePlayer +=1;
					activePlayer %= numPlayers;
					bs.repaint();
				}
			}
		};
		threadObject = new Thread(play);
		threadObject.start();
		/*Move move = players[activePlayer].getMove(board);
			if(move.isSwap()){
				swap(move);
			}
			board.playMove(move);
			players[activePlayer].updateScore(board.getScore(move));
			//iterate over move, any tiles that were in the move and in the hand should be replaced with a new tile from the deck//not yet done
			int handLength = players[activePlayer].getHand().length;
			Tile[] tempHand = new Tile[handLength];
			tempHand = players[activePlayer].getHand().clone();
			for(Placement p : move.getList()) {
				Tile t = p.getTile();
				for(int i = 0 ; i < handLength; i++) {
					if(t.equals(players[activePlayer].getHand()[i])) {
						tempHand[i] = deck.peek();
						deck.pop();
					}
				}
			}
			if (deck.size()>0){//game needs to get rid of the tiles placed on the board and draw new tiles, add that to a hand, and update the hand
				deck.pop();
			}*/	
		return players;
	}
	
	public Tile draw(){
		return deck.pop();
	}
	
	public void fixHand() {
		hand = players[activePlayer].getHand();
		for(int i=0;i<6;i++) {
			if(hand[i]==null) {
				hand[i] = draw();
			}
		}
		players[activePlayer].updateHand(hand);
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




