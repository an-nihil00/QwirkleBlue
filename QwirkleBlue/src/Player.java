import java.util.ArrayList;

public abstract class Player {
	private String name;
	private int score;
	private Tile[] hand;
	Player(String name){
		this.name=name;
		score=0;
		hand = new Tile[6];
	}
	public void updateHand(Tile[] hand){
		this.hand=hand;
	}
	//Allows other classes to change the hand based on new draws
	public void updateScore(int move){
		score+=move;
	}
	//Points from the last move are added to the player’s score
	public Tile[] getHand(){
		return hand;
	}
	//Will return an array tiles in the player’s hand
	public int getScore(){
		return score;
	}
	//Will return the players’ current score 
	public String getName(){
		return name;
	}
	//Returns the name the human entered at the beginning of the game
	public abstract Move getMove(Board board);
	//Gets placement of the next move
}


