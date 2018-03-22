 

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;



public class StartupScreen extends JPanel implements ActionListener{
	private int players=4;
	JButton btnPlay;
	JButton btnExit;
	ButtonGroup[] groups = new ButtonGroup[4];
	JRadioButton[][] optionButtons = new JRadioButton[4][3];
	JTextField[] name = new JTextField[4];
	JComboBox<String>[] dif = new JComboBox[4];;
	String[] rdBtnLabels = {"Human","Computer","None"};
	String[] difficulties = {"Easy","Hard"};
	String passingName, passingName1, passingName2, passingName3;
	MasterScreen parent;

	public StartupScreen(MasterScreen parent) {
		initialize();
		this.parent = parent;
	}

	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 30, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gridBagLayout);

		JLabel lblQwirkle = new JLabel("Qwirkle");
		lblQwirkle.setFont(new Font("Papyrus", Font.PLAIN, 15));
		GridBagConstraints gbc_lblQwirkle = new GridBagConstraints();
		gbc_lblQwirkle.gridwidth = 4;
		gbc_lblQwirkle.gridx = 0;
		gbc_lblQwirkle.gridy = 0;
		this.add(lblQwirkle, gbc_lblQwirkle);
		
		GridBagConstraints gbc_rdbtns = new GridBagConstraints();
		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
		GridBagConstraints gbc_inputs = new GridBagConstraints();
		gbc_rdbtns.anchor = GridBagConstraints.WEST;
		gbc_lblPlayer.gridy = 1;
		gbc_lblPlayer.anchor = GridBagConstraints.WEST;
		gbc_inputs.gridy = 5;
		gbc_inputs.fill = GridBagConstraints.BOTH;
		gbc_inputs.insets = new Insets(5, 5, 0, 5);
		for (int x=0;x<4;x++) {
			gbc_lblPlayer.gridx = x;
			gbc_inputs.gridx = x;
			this.add(new JLabel("Player "+(x+1)),gbc_lblPlayer);
			dif[x] = new JComboBox<String>(difficulties);
			name[x] = new JTextField();
			name[x].setDocument(new JTextFieldLimit(20));
			name[x].setText("Name");
			this.add(dif[x], gbc_inputs);
			this.add(name[x], gbc_inputs);
			groups[x] = new ButtonGroup();
			for (int y=0;y<3;y++) {
				optionButtons[x][y] = new JRadioButton(rdBtnLabels[y]);
				optionButtons[x][y].addActionListener(this);
				optionButtons[x][y].setActionCommand(rdBtnLabels[y]);
				gbc_rdbtns.gridx = x;
				gbc_rdbtns.gridy = y+2;
				this.add(optionButtons[x][y], gbc_rdbtns);
				groups[x].add(optionButtons[x][y]);
			}
		}
		
		optionButtons[0][0].setSelected(true);
		dif[0].setVisible(false);
		optionButtons[1][1].setSelected(true);
		name[1].setVisible(false);
		optionButtons[2][1].setSelected(true);
		name[2].setVisible(false);
		optionButtons[3][1].setSelected(true);
		name[3].setVisible(false);
		
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(this);
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.gridwidth = 2;
		gbc_btnPlay.gridx = 1;
		gbc_btnPlay.gridy = 6;
		gbc_btnPlay.insets = new Insets(5, 0, 0, 0);
		gbc_btnPlay.fill = GridBagConstraints.HORIZONTAL;
		this.add(btnPlay, gbc_btnPlay);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.gridwidth = 2;
		gbc_btnExit.gridx = 1;
		gbc_btnExit.gridy = 7;
		gbc_btnExit.insets = new Insets(5, 0, 5, 0);
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		this.add(btnExit, gbc_btnExit);
	}
	public int getNumPlayers() {
		int a = 0;
		for(int i:getPlayerTypes()) {
			if(i<2) {
				a++;
			}
		}
		return a;
	}
	public int[] getPlayerTypes(){
		int[] ans = new int[4];
		for(int i=0;i<4;i++) {
			String typeString = groups[i].getSelection().getActionCommand();
			if(typeString.equals("Human")) {
				ans[i] = 0;
			}
			else if(typeString.equals("Computer")){
				ans[i] = 1;
			}
			else {
				ans[i] = 2;
			}
		}
		return ans;
	}
	public String[] getPlayerNames() {
		String[] ans = new String[4];
		int[] types = getPlayerTypes();
		for(int i=0;i<4;i++){
			if(types[i] == 0) {
				ans[i] = name[i].getText();
if (name[i].getText().equals("Name")){
					ans[i]="Player "+(i+1);
				}

			}
			else if(types[i] == 1) {
				ans[i] = "Computer "+(i+1);
			}
		}
		System.out.println(Arrays.toString(ans));
		return ans;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Play")) {
			parent.startGame(getNumPlayers(), getPlayerTypes(), getPlayerNames());
		}
		else if(e.getActionCommand().equals("Exit")){
			parent.exit();
		}
		else {
			for(int i=0;i<4;i++) {
				String command = groups[i].getSelection().getActionCommand();
				if(command.equals("Human")) {
					dif[i].setVisible(false);
					name[i].setVisible(true);
				}
				else if(command.equals("Computer")) {
					dif[i].setVisible(true);
					name[i].setVisible(false);
				}
				else {
					dif[i].setVisible(false);
					name[i].setVisible(false);
				}
			}
			if(getNumPlayers()<2) {
				btnPlay.setEnabled(false);
			}
			else {
				btnPlay.setEnabled(true);
			}
		}
	}
}



