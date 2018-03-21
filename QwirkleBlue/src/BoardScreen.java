import javax.swing.*;
import java.awt.*;
 
import javax.swing.*;
import java.awt.*;
public class BoardScreen {
	JFrame frame;
	JPanel board,score,hand,buttonArea;
	Container container;
	JLabel score1,score2,score3,score4;
	Board tiles;
	
	BoardScreen(Board b){
		System.out.println(b);
		tiles = b;
		frame=new JFrame("Qwirkle Game");
		frame.setSize(800,800);
		container=frame.getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);	
		board = new Screen(10,10,64,b); // 640x640 pixel allocation for the board; initial tiles are 64 pixels on a side
		
		board.setMinimumSize(new Dimension(800,800));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(board, gbc_panel);
		score= new JPanel();
		score.setLayout(new GridLayout(4,1,0,0));
		score1=new JLabel("Player 1: 100");
		score1.setAlignmentX(SwingConstants.CENTER);
		score.add(score1);
		score2=new JLabel("COM2: 011");
		score2.setAlignmentX(SwingConstants.CENTER);
		score.add(score2);
		score3=new JLabel("COM3: 110");
		score3.setAlignmentX(SwingConstants.CENTER);
		score.add(score3);
		score4=new JLabel("COM4: 111");
		score4.setAlignmentX(SwingConstants.CENTER);
		score.add(score4);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx= 1;
		gbc_panel.gridy=0;
		frame.getContentPane().add(score, gbc_panel);
		hand=new JPanel();
		hand.setLayout(new GridLayout(1,6,0,0));
		gbc_panel.fill= GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx=0;
		gbc_panel.gridy=1;
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public class Screen extends JPanel {
		private static final long serialVersionUID = 420L;
		Image tileSet;
		final int tSetW = 12; final int tSetH = 7;
		int w, h, tSize, boardWidth, boardHeight;
		Board tiles;
		public Screen(int w, int h, int tSize,Board b){
			this.tiles = b;
			setPreferredSize(new Dimension(w*tSize,h*tSize));
			this.w = w; this.h = h; this.tSize = tSize;
			loadTileSet("Ayodeji \"Doki-doki bungaku kurabu\" Motorola");		
		}
		public void loadTileSet(String path){
			 tileSet = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("tileSet.png")); // set it to the tileset's name
		}
		void drawTile(Graphics g,int x,int y,Tile t,boolean confirmed){
			int tx, ty;
			if(t != null) {
				System.out.println(x+" "+y);
				if(confirmed) {
					tx = t.getColour();
					ty = t.getPattern(); // make sure the tileset aligns with this
				} else {
					tx = 6 + t.getColour(); // also make sure that the "unconfirmed" tiles are to the right of the standard tiles
					ty = t.getPattern();
				}
				
			}else {
				tx = 0;
				ty = 6; // put the "blank" tile at these coordinates
			}
			
			// n.b. Assumes that tiles in tileSet.png are 64x64; change as necessary
			
			g.drawImage(tileSet, x*tSize, y*tSize, (x+1)*tSize, (y+1)*tSize, tx*64, ty*64, (tx+1)*64, (ty+1)*64, this);
			
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			boardWidth = tiles.getMaxX()-tiles.getMinX();
			boardHeight = tiles.getMaxY()-tiles.getMinY();
			int maxDim = Math.max(boardWidth,boardHeight);
			while((maxDim+2)*tSize > 640) {
				tSize--; // pixel by pixel?
			}
			
			
			g.setColor(Color.lightGray); // the heathens spell "grey" incorrectly
		    g.fillRect(0, 0, getWidth(), getHeight());
			for(int i = tiles.getMinX() - 1;i < tiles.getMaxX() + 2;i++){
				for(int j = tiles.getMinY() - 1;j < tiles.getMaxY() + 2;j++){
					drawTile(g, (i - tiles.getMinX()+1), (j - tiles.getMinY()+1), tiles.getTile(i, j), true);
				}
			}
			
			// IF board size i.e. maxX - minX and/or maxY - minY is too large
			// set tSize smaller so that it fits into the alloted space in pixels
			// The tiles SHOULD REMAIN SQUARE
			
			
		}
	}

	public class Hand extends JPanel{
		Tile[] hand;
		private static final long serialVersionUID = 696969L;
		Image tileSet;
		Hand(){
			loadTileSet("BBBBBEeEeeB");
		}
		
		public void loadTileSet(String path){
			 tileSet = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("tileSet.png")); // set it to the tileset's name
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawTiles(g);
		}
		
		void drawTiles(Graphics g){
			int position = 0;
			for(Tile tile : hand){
				int tx, ty;
				if(tile != null){
					tx = tile.getColour();
					ty = tile.getPattern();
				} else {
					tx = 0;
					ty = 6;
				}
				
				g.drawImage(tileSet, 10 + position*64, 10, 74 + position*64, 74, tx*64, ty*64, (tx+1)*64, (ty+1)*64, this);
			}
		}
		
		public void updateHand(Player p){
			hand = p.getHand();
		}
		
	}

	public void updateHand(Player p){
	}


	
	public Move getCurrentMove(){
		return null;
		// gets input from the player's interaction with the UI
	}
}


















