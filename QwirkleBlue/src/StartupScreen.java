import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartupScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartupScreen window = new StartupScreen();
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
	public StartupScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{35, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblQwirkle = new JLabel("Qwirkle");
		GridBagConstraints gbc_lblQwirkle = new GridBagConstraints();
		gbc_lblQwirkle.gridwidth = 4;
		gbc_lblQwirkle.insets = new Insets(0, 0, 5, 0);
		gbc_lblQwirkle.gridx = 0;
		gbc_lblQwirkle.gridy = 0;
		frame.getContentPane().add(lblQwirkle, gbc_lblQwirkle);
		
		JLabel lblPlayer = new JLabel("Player 1");
		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
		gbc_lblPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer.gridx = 0;
		gbc_lblPlayer.gridy = 1;
		frame.getContentPane().add(lblPlayer, gbc_lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2");
		GridBagConstraints gbc_lblPlayer_1 = new GridBagConstraints();
		gbc_lblPlayer_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer_1.gridx = 1;
		gbc_lblPlayer_1.gridy = 1;
		frame.getContentPane().add(lblPlayer_1, gbc_lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 3");
		GridBagConstraints gbc_lblPlayer_2 = new GridBagConstraints();
		gbc_lblPlayer_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer_2.gridx = 2;
		gbc_lblPlayer_2.gridy = 1;
		frame.getContentPane().add(lblPlayer_2, gbc_lblPlayer_2);
		
		JLabel lblPlayer_3 = new JLabel("Player 4");
		GridBagConstraints gbc_lblPlayer_3 = new GridBagConstraints();
		gbc_lblPlayer_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayer_3.gridx = 3;
		gbc_lblPlayer_3.gridy = 1;
		frame.getContentPane().add(lblPlayer_3, gbc_lblPlayer_3);
		
		JRadioButton rdbtnNone = new JRadioButton("Human");
		rdbtnNone.setSelected(true);
		GridBagConstraints gbc_rdbtnNone = new GridBagConstraints();
		gbc_rdbtnNone.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNone.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNone.gridx = 0;
		gbc_rdbtnNone.gridy = 2;
		frame.getContentPane().add(rdbtnNone, gbc_rdbtnNone);
		
		JRadioButton rdbtnNone_1 = new JRadioButton("Human");
		GridBagConstraints gbc_rdbtnNone_1 = new GridBagConstraints();
		gbc_rdbtnNone_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnNone_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNone_1.gridx = 1;
		gbc_rdbtnNone_1.gridy = 2;
		frame.getContentPane().add(rdbtnNone_1, gbc_rdbtnNone_1);
		
		JRadioButton rdbtnNone_2 = new JRadioButton("Human");
		GridBagConstraints gbc_rdbtnNone_2 = new GridBagConstraints();
		gbc_rdbtnNone_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnNone_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNone_2.gridx = 2;
		gbc_rdbtnNone_2.gridy = 2;
		frame.getContentPane().add(rdbtnNone_2, gbc_rdbtnNone_2);
		
		JRadioButton rdbtnNone_3 = new JRadioButton("Human");
		GridBagConstraints gbc_rdbtnNone_3 = new GridBagConstraints();
		gbc_rdbtnNone_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnNone_3.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNone_3.gridx = 3;
		gbc_rdbtnNone_3.gridy = 2;		frame.getContentPane().add(rdbtnNone_3, gbc_rdbtnNone_3);
		
		JRadioButton rdbtnHuman = new JRadioButton("Computer");
		GridBagConstraints gbc_rdbtnHuman = new GridBagConstraints();
		gbc_rdbtnHuman.anchor = GridBagConstraints.WEST;
		gbc_rdbtnHuman.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHuman.gridx = 0;
		gbc_rdbtnHuman.gridy = 3;
		frame.getContentPane().add(rdbtnHuman, gbc_rdbtnHuman);
		
		JRadioButton rdbtnHuman_1 = new JRadioButton("Computer");
		rdbtnHuman_1.setSelected(true);
		GridBagConstraints gbc_rdbtnHuman_1 = new GridBagConstraints();
		gbc_rdbtnHuman_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnHuman_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHuman_1.gridx = 1;
		gbc_rdbtnHuman_1.gridy = 3;
		frame.getContentPane().add(rdbtnHuman_1, gbc_rdbtnHuman_1);
		
		JRadioButton rdbtnHuman_2 = new JRadioButton("Computer");
		rdbtnHuman_2.setSelected(true);
		GridBagConstraints gbc_rdbtnHuman_2 = new GridBagConstraints();
		gbc_rdbtnHuman_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnHuman_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnHuman_2.gridx = 2;
		gbc_rdbtnHuman_2.gridy = 3;
		frame.getContentPane().add(rdbtnHuman_2, gbc_rdbtnHuman_2);
		
		JRadioButton rdbtnHuman_3 = new JRadioButton("Computer");
		rdbtnHuman_3.setSelected(true);
		GridBagConstraints gbc_rdbtnHuman_3 = new GridBagConstraints();
		gbc_rdbtnHuman_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnHuman_3.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnHuman_3.gridx = 3;
		gbc_rdbtnHuman_3.gridy = 3;
		frame.getContentPane().add(rdbtnHuman_3, gbc_rdbtnHuman_3);
		
		JRadioButton rdbtnComputer = new JRadioButton("None");
		GridBagConstraints gbc_rdbtnComputer = new GridBagConstraints();
		gbc_rdbtnComputer.anchor = GridBagConstraints.WEST;
		gbc_rdbtnComputer.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnComputer.gridx = 0;
		gbc_rdbtnComputer.gridy = 4;
		frame.getContentPane().add(rdbtnComputer, gbc_rdbtnComputer);
		
		JRadioButton rdbtnComputer_1 = new JRadioButton("None");
		GridBagConstraints gbc_rdbtnComputer_1 = new GridBagConstraints();
		gbc_rdbtnComputer_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnComputer_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnComputer_1.gridx = 1;
		gbc_rdbtnComputer_1.gridy = 4;
		frame.getContentPane().add(rdbtnComputer_1, gbc_rdbtnComputer_1);
		
		JRadioButton rdbtnComputer_2 = new JRadioButton("None");
		GridBagConstraints gbc_rdbtnComputer_2 = new GridBagConstraints();
		gbc_rdbtnComputer_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnComputer_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnComputer_2.gridx = 2;
		gbc_rdbtnComputer_2.gridy = 4;
		frame.getContentPane().add(rdbtnComputer_2, gbc_rdbtnComputer_2);
		
		JRadioButton rdbtnComputer_3 = new JRadioButton("None");
		GridBagConstraints gbc_rdbtnComputer_3 = new GridBagConstraints();
		gbc_rdbtnComputer_3.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnComputer_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnComputer_3.gridx = 3;
		gbc_rdbtnComputer_3.gridy = 4;
		frame.getContentPane().add(rdbtnComputer_3, gbc_rdbtnComputer_3);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPlay.gridwidth = 2;
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 1;
		gbc_btnPlay.gridy = 5;
		frame.getContentPane().add(btnPlay, gbc_btnPlay);
		
		JButton btnNewButton = new JButton("Exit");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 6;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	}

}
