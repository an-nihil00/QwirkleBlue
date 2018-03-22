import java.util.Arrays;

import javax.swing.JFrame;

public class GameTester {
	//TODO alot of things
	public static void main(String[] args) throws Exception {
		/*//checks to make sure all variable are initialized properly (contructor stuff)
		Game game = new Game(4,new String[] {"Ayo","Lexi","Xander","Noah"},new int[]{2,1,0,0});
		System.out.println(game.deck);
		System.out.println(Arrays.toString(game.names));
		System.out.println(Arrays.toString(game.players));
		for(Player p : game.players){
			System.out.println(p.getClass());
		}
		//Board testing
		*/
		Board bored = new Board();
		//addTest(bored);
		//addTest(bored);
		bored.placeTile(new Placement(0,0,new Tile(0,0)));
		bored.placeTile(new Placement(0,1,new Tile(1,0)));
		bored.placeTile(new Placement(1,0,new Tile(0,1)));
		bored.placeTile(new Placement(4,1,new Tile(2,2)));
		bored.placeTile(new Placement(1,4,new Tile(2,2)));
		bored.placeTile(new Placement(-1,0,new Tile(0,5)));
		JFrame test = new JFrame();
		BoardScreen bs = new BoardScreen(bored, new MasterScreen());
		HumanPlayer hp = new HumanPlayer("Ayodeji Motorola", bs);
		Tile[] testHand = {new Tile(0,0),new Tile(0,1),new Tile(0,2),new Tile(0,3),new Tile(0,4),new Tile(0,5)};
		hp.updateHand(testHand);
		bs.updateHand(hp);
		test.setContentPane(bs);
		test.pack();
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static void addTest(Board bored){
		int randColour = (int)(Math.random() * 6);
		int randPattern = (int)(Math.random() * 6);
		int x = (int)(Math.random() * 12)-6;
		int y = (int)(Math.random() * 12)-6;
		boolean rand = Math.random() > .5;
		Tile t = new Tile(randColour, randPattern);
		bored.placeTile(new Placement(x,y,t));
		if(rand){
			bored.confirmedState.put(new Tuple(x,y), rand);
		}
	}
}




