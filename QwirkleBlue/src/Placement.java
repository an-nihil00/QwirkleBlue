public class Placement {
	private int xCo;
	private int yCo;
	private Tile theRealslimTile;
	private int[] XY= new int[2];
	Placement(int x, int y, Tile theTile){
		xCo=x;
		yCo=y;
		theRealslimTile=theTile;
		XY[0]=x;
		XY[1]=y;
	}
	public int getX(){
		return xCo;
	}
	public int getY(){
		return yCo;
	}
	public int[] getXY(){//Array of Coordinates [0]=x [1]=y
		return XY;
	}
	public Tile getTile(){
		return theRealslimTile;
	}
	public void updateCoordinate(int x,int y){
		xCo=x;
		yCo=y;
		XY[0]=x;
		XY[1]=y;
	}
}



