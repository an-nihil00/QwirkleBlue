import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;

public class MasterScreen {
	private JFrame frame;
	Game g;
	BoardScreen bs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterScreen window = new MasterScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MasterScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setResizable(false);
		frame.setBounds(140, 140, 0, 0);
		StartupScreen start = new StartupScreen(this);
		frame.getContentPane().add(start, "start");
		frame.pack();
		
		bs = new BoardScreen(new Board(),this);
		frame.getContentPane().add(bs, "game");
		
		PauseScreen pause = new PauseScreen(this);
		frame.getContentPane().add(pause, "pause");
		
		JPanel end = new JPanel();
		frame.getContentPane().add(end, "end");
	}
	public void startGame(int numPlayers, int[] playerTypes, String[] names){
		frame.pack();
		bs.setBoard(new Board());
		g = new Game(numPlayers, names, playerTypes, bs);
		bs.getBoard().setGame(g);
		bs.updateScores();
		bs.updateTiles();
		try {
			g.play();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		((CardLayout) frame.getContentPane().getLayout()).next(frame.getContentPane());
	}
	public void pause() {
		((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"pause");
	}
	public void unpause() {
		((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"game");
	}
	public void toMenu() {
		((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"start");
		frame.setBounds(140, 140, 330, 234);
	}
	public void exit() {
		frame.dispose();
	}
}



