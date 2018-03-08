package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartUpWindow extends JFrame 
{

	private JPanel mainCP;
	private JTextField textField;
	private JComboBox personalityCB;
	private JPanel midCP;
	private JTextArea textArea;
	private JPanel midRightCP;
	private JButton btnNewGame;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnDown;
	private JButton buttonRight;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					StartUpWindow frame = new StartUpWindow();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartUpWindow() 
	{
		initComponents();
		createEvents();
	}
	
	private void initComponents() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 472);
		mainCP = new JPanel();
		mainCP.setToolTipText("");
		mainCP.setBackground(Color.DARK_GRAY);
		mainCP.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainCP);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumberOfOgres.setForeground(Color.WHITE);
		lblNumberOfOgres.setBackground(Color.WHITE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setForeground(Color.WHITE);
		lblGuardPersonality.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		personalityCB = new JComboBox();
		
		midCP = new JPanel();
		midCP.setBackground(Color.DARK_GRAY);
		
		lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_mainCP = new GroupLayout(mainCP);
		gl_mainCP.setHorizontalGroup(
			gl_mainCP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainCP.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainCP.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainCP.createSequentialGroup()
							.addGroup(gl_mainCP.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNumberOfOgres, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGuardPersonality, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_mainCP.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(personalityCB, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
						.addComponent(midCP, GroupLayout.PREFERRED_SIZE, 647, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_mainCP.setVerticalGroup(
			gl_mainCP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainCP.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainCP.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfOgres, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_mainCP.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGuardPersonality, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(personalityCB, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(midCP, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		midCP.setLayout(new GridLayout(0, 2, 0, 0));
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setEditable(false);
		midCP.add(textArea);
		
		midRightCP = new JPanel();
		midRightCP.setBackground(Color.DARK_GRAY);
		midCP.add(midRightCP);
		
		btnNewGame = new JButton("New Game");
		
		btnNewGame.setBackground(Color.LIGHT_GRAY);
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnLeft = new JButton("Left");
		btnLeft.setBackground(Color.LIGHT_GRAY);
		btnLeft.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnUp = new JButton("Up");
		btnUp.setBackground(Color.LIGHT_GRAY);
		btnUp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnDown = new JButton("Down");
		btnDown.setBackground(Color.LIGHT_GRAY);
		btnDown.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		buttonRight = new JButton("Right");
		buttonRight.setBackground(Color.LIGHT_GRAY);
		buttonRight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_midRightCP = new GroupLayout(midRightCP);
		gl_midRightCP.setHorizontalGroup(
			gl_midRightCP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_midRightCP.createSequentialGroup()
					.addGap(67)
					.addComponent(btnLeft)
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addComponent(buttonRight)
					.addGap(67))
				.addGroup(gl_midRightCP.createSequentialGroup()
					.addGroup(gl_midRightCP.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_midRightCP.createSequentialGroup()
							.addGap(109)
							.addGroup(gl_midRightCP.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnExit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewGame, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_midRightCP.createSequentialGroup()
							.addGap(127)
							.addComponent(btnDown)))
					.addContainerGap(117, Short.MAX_VALUE))
				.addGroup(gl_midRightCP.createSequentialGroup()
					.addGap(135)
					.addComponent(btnUp)
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_midRightCP.setVerticalGroup(
			gl_midRightCP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_midRightCP.createSequentialGroup()
					.addComponent(btnNewGame, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(btnUp)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_midRightCP.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLeft)
						.addComponent(buttonRight, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addComponent(btnDown)
					.addGap(62)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		midRightCP.setLayout(gl_midRightCP);
		
		personalityCB.addItem("Rookie");
		personalityCB.addItem("Drunken");
		personalityCB.addItem("Suspicious");
		mainCP.setLayout(gl_mainCP);
		setBackground(Color.DARK_GRAY);
		setTitle("DungeonKeep");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartUpWindow.class.getResource("/resources/knight32.png")));
		
	}

	private void createEvents() 
	{
		btnNewGame.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
	}
}
