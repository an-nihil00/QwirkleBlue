import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PauseScreen extends JPanel implements ActionListener{
	private MasterScreen parent;

	public PauseScreen(MasterScreen parent) {
		this.parent = parent;

		this.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel lblTitle = new JLabel("Qwirkle");
		this.add(lblTitle);

		JButton btnResume = new JButton("Resume");
		this.add(btnResume);
		btnResume.addActionListener(this);

		JButton btnMenu = new JButton("Start Menu");
		this.add(btnMenu);
		btnMenu.addActionListener(this);

		JButton btnExit = new JButton("Exit Game");
		this.add(btnExit);
		btnExit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Resume")) {
			parent.unpause();
		}
		else if(e.getActionCommand().equals("Start Menu")) {
			parent.toMenu();
		}
	}
}


