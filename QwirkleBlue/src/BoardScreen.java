import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
public class BoardScreen extends JPanel implements MouseListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1595951L;
	JPanel score,buttonArea;
	Screen board;
	Hand hand;
	Container container;
	JLabel[] scores = new JLabel[4];
	JLabel tilesLeft;
	Board tiles;
	Tile activeTile;
	Player activePlayer;
	Boolean thinking;
	Game g;
	JButton undoButton,confirmButton,swapButton;
	MasterScreen parent; 
	BoardScreen(Board b,MasterScreen p){
		parent = p;
		tiles = b;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{384, 256, 128, 0};
		gridBagLayout.rowHeights = new int[]{640, 64, 0};
		gridBagLayout.columnWeights = new double[]{0.0,0.0,0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gridBagLayout);
		
		board = new Screen(640,640,640,b,this);
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.fill = GridBagConstraints.BOTH;
		gbc_board.gridx = 0;
		gbc_board.gridy = 0;
		gbc_board.gridwidth = 2;
		this.add(board, gbc_board);
		
		score = new JPanel();
		score.setLayout(new GridLayout(5,0));
		GridBagConstraints gbc_score = new GridBagConstraints();
		gbc_score.fill = GridBagConstraints.BOTH;
		gbc_score.gridx = 2;
		gbc_score.gridy = 0;
		this.add(score, gbc_score);
		score.setBackground(Color.GREEN);
		
		tilesLeft = new JLabel("");
		score.add(tilesLeft);
		for(int i=0;i<4;i++) {
			scores[i] = new JLabel("");
			score.add(scores[i]);
		}
		
		hand = new Hand(this);
		GridBagConstraints gbc_hand = new GridBagConstraints();
		gbc_hand.fill = GridBagConstraints.BOTH;
		gbc_hand.gridx = 0;
		gbc_hand.gridy = 1;
		this.add(hand, gbc_hand);
		
		buttonArea = new JPanel();
		buttonArea.setBackground(Color.RED);
		buttonArea.setLayout(new GridLayout(0,4));
		GridBagConstraints gbc_buttonArea = new GridBagConstraints();
		gbc_buttonArea.gridwidth = 2;
		gbc_buttonArea.gridx = 1;
		gbc_buttonArea.gridy = 1;
		gbc_buttonArea.fill = GridBagConstraints.BOTH;
		this.add(buttonArea, gbc_buttonArea);
		
		confirmButton = new JButton("Confirm");
		buttonArea.add(confirmButton);
		confirmButton.addActionListener(this);
		
		swapButton = new JButton("Swap");
		buttonArea.add(swapButton);
		swapButton.addActionListener(this);
		
		undoButton = new JButton("Undo");
		buttonArea.add(undoButton);
		undoButton.addActionListener(this);	
		
		JButton pauseButton = new JButton("Pause");
		buttonArea.add(pauseButton);
		pauseButton.addActionListener(this);
	}
	public void updateScores() {
		for(int i=0;i<g.numPlayers();i++) {
			scores[i].setText(g.playerName(i) + ":" + g.playerScore(i));
		}
	}
	public void updateTiles() {
		tilesLeft.setText("Tiles Left:"+g.tilesLeft());
	}
	public void setBoard(Board b) {
		tiles = b;
		board.setBoard(tiles);
		repaint();
	}
	public void setGame(Game g) {
		this.g = g;
	}
	public boolean getThinking(){
		return thinking;
	}
	public void setThinking(boolean t) {
		thinking = t;
	}
	public Board getBoard() {
		return tiles;
	}
	public void clearActiveTile() {
		setActiveTile(null);
		setCursor(Cursor.getDefaultCursor());
		swapButton.setEnabled(tiles.canSwap());
	}
	public Tile getActiveTile() {
		return activeTile;
	}
	public void setActiveTile(Tile tile) {
		activeTile = tile;
		swapButton.setEnabled(tiles.canSwap());
	}
	public void updateHand(Player p){
		hand.updateHand(p);
		undoButton.setEnabled(false);
		confirmButton.setEnabled(false);
		swapButton.setEnabled(tiles.canSwap());
		updateTiles();
		updateScores();
	}
	public class Screen extends JPanel {
		private static final long serialVersionUID = 420L;
		Image tileSet;
		final int tSetW = 12; final int tSetH = 7;
		int tSize, boardWidth, boardHeight;
		Board tiles;
		BoardScreen bs;
		public Screen(int w, int h, int tSize,Board b, BoardScreen bs){
			this.addMouseListener(bs);
			this.bs = bs;
			this.tiles = b;
			setPreferredSize(new Dimension(w,h));
			this.tSize = tSize;
			loadTileSet("Ayodeji \"Doki-doki bungaku kurabu\" Motorola");		
		}
		public void setBoard(Board b) {
			tiles = b;
		}
		public void loadTileSet(String path){
			 tileSet = Toolkit.getDefaultToolkit().getImage(("Images/RealTileSet.png")); // set it to the tileset's name
		}
		void drawTile(Graphics g,int x,int y,Tile t,boolean c){
			int tx, ty;
			if(t != null) {
				if(c) {
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
		public void mouseClicked(MouseEvent e) {
			if(bs.getActiveTile()!=null) {
				try {
					int x = e.getX()/tSize + tiles.getMinX()-1;
					int y = e.getY()/tSize + tiles.getMinY()-1;
					Tile t = bs.getActiveTile();
					tiles.playTile(new Placement(x,y,t));
					bs.clearActiveTile();
					repaint();
					undoButton.setEnabled(true);
					confirmButton.setEnabled(true);
				}
				catch(Exception ex) {
					System.out.println("fuck you");
				}
			}
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			boardWidth = tiles.getMaxX()-tiles.getMinX();
			boardHeight = tiles.getMaxY()-tiles.getMinY();
			int maxDim = Math.max(boardWidth,boardHeight);
			while((maxDim+3)*tSize < 640) {
				tSize++; // pixel by pixel?
			}
			while((maxDim+3)*tSize > 640) {
				tSize--; // pixel by pixel?
			}
			g.setColor(Color.lightGray); // the heathens spell "grey" incorrectly
		    g.fillRect(0, 0, getWidth(), getHeight());
			for(int i = 0;i < maxDim + 3;i++){
				for(int j = 0;j < maxDim + 3;j++){
					try {
						drawTile(g, i, j, tiles.getTile(i+tiles.getMinX()-1, j+tiles.getMinY()-1), tiles.getConfirmed(i+tiles.getMinX()-1, j+tiles.getMinY()-1));
					}
					catch(Exception ex) {
						drawTile(g, i, j, tiles.getTile(i+tiles.getMinX()-1, j+tiles.getMinY()-1), false);
					}
				}
			}
		}
	}

	public class Hand extends JPanel{
		Tile[] hand;
		BoardScreen bs;
		private static final long serialVersionUID = 696969L;
		Image tileSet;
		Hand(BoardScreen bs){
			this.addMouseListener(bs);
			this.bs = bs;
			activeTile = null;
			hand = new Tile[6];
			loadTileSet("BBBBBEeEeeB");
		}
		
		public void loadTileSet(String path){
			 tileSet = Toolkit.getDefaultToolkit().getImage(("Images/RealTileSet.png")); // set it to the tileset's name
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			for(int i=0;i<6;i++) {
				drawTile(g,i,hand[i]);
			}
		}
		
		public Tile[] getHand() {
			return hand;
		}
		void drawTile(Graphics g,int p, Tile t){
			int position = p;
			int tx, ty;
			if(t != null){
				tx = t.getColour();
				ty = t.getPattern();
			} else {
				tx = 0;
				ty = 6;
			}	
				g.drawImage(tileSet, position*64, 0, (position+1)*64, 64, tx*64, ty*64, (tx+1)*64, (ty+1)*64, this);
			}
		
		public void updateHand(Player p){
			hand = p.getHand();
		}

		public void mouseClicked(MouseEvent e) {
			int p = e.getX()/64;
			if(p<6) {
				int active = p;
				if(hand[active] != null) {
					Tile prev = activeTile;
					bs.setActiveTile(hand[active]);
					hand[active] = prev;
					int tx, ty;
				
					Toolkit toolkit = Toolkit.getDefaultToolkit();
				
					tx = activeTile.getColour();
					ty = activeTile.getPattern();
					BufferedImage buff = toBufferedImage(tileSet);
					BufferedImage curse = buff.getSubimage(tx*64, ty*64, 64, 64);
					Cursor c = toolkit.createCustomCursor(curse,new Point(0,0),"Tile");
					bs.setCursor(c);
					
					
				} else {
					hand[active] = bs.getActiveTile();
					bs.clearActiveTile();
				}
				this.repaint();
			}
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getComponent() == hand)
			hand.mouseClicked(e);
		else if(e.getComponent() == board)
			board.mouseClicked(e);
		swapButton.setEnabled(tiles.canSwap());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Confirm")) {
			if(thinking) {
				thinking = false;
				synchronized(g.getThread()) {
					g.getThread().notify();
				}
				confirmButton.setEnabled(false);
				swapButton.setText("Swap");
			}
		}
		else if(arg0.getSource().equals(swapButton)) {
			if(activeTile != null) {
				tiles.addSwap(activeTile);
				clearActiveTile();
				swapButton.setText("Swap "+tiles.getCurrentMove().size());
				confirmButton.setEnabled(true);
				undoButton.setEnabled(true);
			}
		}
		else if(arg0.getActionCommand().equals("Undo")) {
			Tile t = tiles.undo();
			boolean waiting = true;
			int i = 0;
			while(waiting) {
				if(hand.getHand()[i] == null) {
					hand.getHand()[i] = t;
					waiting = false;
				}
				i++;
			}
			if(tiles.canSwap()) {
				swapButton.setText("Swap "+tiles.getCurrentMove().size());
			}
			if(tiles.getCurrentMove().size() == 0) {
				undoButton.setEnabled(false);
				confirmButton.setEnabled(false);
				swapButton.setText("Swap");
			}
			repaint();
		}
		else if(arg0.getActionCommand().equals("Pause")) {
			parent.pause();
		}
		swapButton.setEnabled(tiles.canSwap());
	}
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}




