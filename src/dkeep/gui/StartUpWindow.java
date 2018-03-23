package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import dkeep.logic.GameState;
import dkeep.logic.Guard;
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
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import dkeep.cli.u_input;
import dkeep.gui.GameScreen;


public class StartUpWindow extends JFrame 
{

	private JPanel mainCP;
	private JTextField textField;
	private JComboBox personalityCB;
	private GameScreen gameScreen;
	private JPanel midCP;
	private JPanel midRightCP;
	private JButton btnNewGame;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnDown;
	private JButton buttonRight;
    private JButton btnExit;
	private JLabel lblStatus;
	private static GameState gs;

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
		gs = new GameState(1);
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
								.addComponent(midCP, GroupLayout.PREFERRED_SIZE, 647, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_mainCP.createSequentialGroup()
							.addGroup(gl_mainCP.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNumberOfOgres, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGuardPersonality, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_mainCP.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(personalityCB, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))))
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
		
		midRightCP = new JPanel();
		midRightCP.setBackground(Color.DARK_GRAY);
		
		btnNewGame = new JButton("New Game");
		
		btnNewGame.setBackground(Color.LIGHT_GRAY);
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnExit = new JButton("Exit");
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
					.addContainerGap(67, Short.MAX_VALUE)
					.addComponent(btnLeft)
					.addGap(69)
					.addComponent(buttonRight)
					.addGap(67))
				.addGroup(gl_midRightCP.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(gl_midRightCP.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_midRightCP.createSequentialGroup()
							.addGap(109)
							.addGroup(gl_midRightCP.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnExit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewGame, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_midRightCP.createSequentialGroup()
							.addGap(127)
							.addComponent(btnDown)))
					.addGap(117))
				.addGroup(gl_midRightCP.createSequentialGroup()
					.addContainerGap(135, Short.MAX_VALUE)
					.addComponent(btnUp)
					.addGap(139))
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
					.addContainerGap(19, Short.MAX_VALUE))
		);
		midRightCP.setLayout(gl_midRightCP);
		
		gameScreen = new GameScreen();
		gameScreen.setPreferredSize(new Dimension(32, 32));
		
		GroupLayout gl_midCP = new GroupLayout(midCP);
		gl_midCP.setHorizontalGroup(
			gl_midCP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_midCP.createSequentialGroup()
					.addContainerGap()
					.addComponent(gameScreen, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(midRightCP, GroupLayout.PREFERRED_SIZE, 281, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_midCP.setVerticalGroup(
			gl_midCP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_midCP.createSequentialGroup()
					.addGroup(gl_midCP.createParallelGroup(Alignment.LEADING)
						.addComponent(midRightCP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gameScreen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		midCP.setLayout(gl_midCP);
		
		
		
		personalityCB.addItem("Rookie");
		personalityCB.addItem("Drunken");
		personalityCB.addItem("Suspicious");
		mainCP.setLayout(gl_mainCP);
		setBackground(Color.DARK_GRAY);
		setTitle("DungeonKeep");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartUpWindow.class.getResource("/resources/knight32.png")));
		
	}
	
	public boolean status()
	{
		if(textField.getText() == null)
		{
			lblStatus.setText("Must have at least one Ogre");
			return false;
		}
		
		if(Integer.parseInt(textField.getText()) <= 0 || textField.getText() == null)
		{
			lblStatus.setText("Must have at least one Ogre");
			return false;
		}
		
		if(Integer.parseInt(textField.getText()) >= 9)
		{
			lblStatus.setText("Number of Ogres too high");
			return false;
		}
	
		lblStatus.setText("You can play now.");
		
		return true;
	}
	
	

	private void createEvents() 
	{
		btnNewGame.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				gs = new GameState(1);
				gameScreen.setGameState(gs);
				
				if(!status())
					return;
				
				gs.addOgres(Integer.parseInt(textField.getText()));
				
				gs.getGuard().setType((String) personalityCB.getSelectedItem());
				gameScreen.paint();
				
			}
		});
		
		
		textField.addInputMethodListener(new InputMethodListener() 
		{
			public void caretPositionChanged(InputMethodEvent arg0) 
			{
				status();
			}
			
			public void inputMethodTextChanged(InputMethodEvent arg0) 
			{
				status();
			}
		});
		
		
		btnExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(gs.isGame_over())
					lblStatus.setText("Game Over\n");

				System.exit(0);
			}
		});
		
		btnUp.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				gs.getHero().heroMove("w", gs);
				gs.updateMap();
				gameScreen.paint();
				
				if(gs.test_game_over())
					lblStatus.setText("Game Over\n");
			}
		});
		

		btnDown.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				gs.getHero().heroMove("s", gs);
				gs.updateMap();
				gameScreen.paint();
				
				if(gs.test_game_over())
					lblStatus.setText("Game Over\n");
			}
		});
		
		buttonRight.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				gs.getHero().heroMove("d", gs);
				gs.updateMap();
				gameScreen.paint();
				
				if(gs.test_game_over())
					lblStatus.setText("Game Over\n");
			}
		});
		
		btnLeft.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				gs.getHero().heroMove("a", gs);
				gs.updateMap();
				gameScreen.paint();
				
				if(gs.test_game_over())
					lblStatus.setText("Game Over\n");
			}
		});
		
		
		
	}
}
