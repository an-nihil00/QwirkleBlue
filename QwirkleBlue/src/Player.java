import java.util.ArrayList;

public abstract class Player {
	private String name;
	private int score;
	private int[][] hand;
	Player(String name){
		this.name=name;
		score=0;
		hand = new int[2][6];
	}
	public void updateHand(int[][] hand){
		this.hand=hand;
	}
	//Allows other classes to change the hand based on new draws
	public void updateScore(int move){
		score+=move;
	}
	//Points from the last move are added to the player’s score
	public int[][] getHand(){
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
	public abstract int[][] getMove(ArrayList<int []> boardState);
	//Gets placement of the next move
}