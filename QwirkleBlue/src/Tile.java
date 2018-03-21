import java.util.Arrays;

public class Tile {
	private int col;
	private int pat;
	final String[] colours  = {"Red","Orange","Yellow","Green","Blue","Purple"};
	final String[] patterns = {"Circle","Square","Clover","Diamond","Cross","Star"};
	Tile(int colour, int pattern){
		//Tile made via numbers 0-5 for color and shape respectively
		col=colour;
		pat=pattern;
	}
	public int getColour(){
		return col;
	}
	public int getPattern(){
		return pat;
	}
	public String getColourString(){
		return colours[col];
	}
	public String getPatternString(){
		return patterns[pat];
	}
	
	public String toString(){
		return getColourString() +" "+getPatternString();
	}
	
	public boolean equals(Tile in){
		return(in.getColour() == getColour() && in.getPattern() == getPattern());
	}
	
}




