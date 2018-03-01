public class Tile {
	private String TheColor;
	private String ThePattern;
	private int Col;
	private int Pat;	
	Tile(int Colour, int Pattern){
		//Tile made via numbers 0-5 for color and shape respectively
		Col=Colour;
		Pat=Pattern;
		if(Colour==0){
			TheColor="Orange";
		}else if(Colour==1){
			TheColor="Green";
		}else if(Colour==2){
			TheColor="Yellow";
		}else if(Colour==3){
			TheColor="Red";
		}else if(Colour==4){
			TheColor="Blue";
		}else if(Colour==5){
			TheColor="Purple";
		}else{
			TheColor="Blank";
		}
		if(Pattern==0){
			ThePattern="Cross";
		}else if(Pattern==1){
			ThePattern="Clover";
		}else if(Pattern==2){
			ThePattern="Star";
		}else if(Pattern==3){
			ThePattern="Diamond";
		}else if(Pattern==4){
			ThePattern="Square";
		}else if(Pattern==5){
			ThePattern="Circle";
		}else{
			ThePattern="Blank";
		}
	}
	

	public int getColour(){
		return Col;
	}
	public int getPattern(){
		return Pat;
	}
	public String getColourString(){
		return TheColor;
	}
	public String getPatternString(){
		return ThePattern;
	}
	
	public String toString(){
		return TheColor +" "+ThePattern;
	}
	
}


