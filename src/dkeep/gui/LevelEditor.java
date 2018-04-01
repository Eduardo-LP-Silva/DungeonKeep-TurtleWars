package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dkeep.logic.GameState;
import dkeep.logic.Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingConstants;

public class LevelEditor extends JFrame 
{
	private JPanel contentPane;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private GameScreen gameScreen;
	private GameState gs;
	private GameState gs2;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JLabel lblStatus;
	private JLabel lblWall;
	private JLabel lblDoor;
	private JLabel lblKey;
	private JLabel lblOgre;
	private JLabel lblHero;
	private JLabel lblResStatus;
	private String[][] new_map;
	private JPanel topPanel;
	private JPanel panel;
	private JButton btnGenerate;
	private String item;
	private JButton btnExit;
	private JLabel lblHerowarning; 
	private boolean HeroPlaced;
	private boolean DoorPlaced;
	private boolean KeyPlaced;
	private JLabel lblMapStatus;

	/**
	 * Launch the application.
	 */
	public static void StartGameWindow(GameState game) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					LevelEditor frame = new LevelEditor(game);
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
	public LevelEditor(GameState game) 
	{
		initComponents();
		gs = game;
		createEvents();
	}
	
	public void initComponents()
	{
		setAutoRequestFocus(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LevelEditor.class.getResource("/resources/knight32.png")));
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 465);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.DARK_GRAY);
		
		topPanel = new JPanel();
		topPanel.setBackground(Color.DARK_GRAY);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 704, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)))
					.addGap(9))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(19, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))))
		);
		
		lblDoor = new JLabel("   Door");
		
		lblDoor.setIcon(new ImageIcon(LevelEditor.class.getResource("/resources/Door32.png")));
		lblDoor.setForeground(Color.WHITE);
		lblDoor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblKey = new JLabel("   Key");
		
		lblKey.setIcon(new ImageIcon(LevelEditor.class.getResource("/resources/Key32.png")));
		lblKey.setForeground(Color.WHITE);
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblWall = new JLabel("   Wall");
		
		lblWall.setIcon(new ImageIcon(LevelEditor.class.getResource("/resources/wall32.png")));
		lblWall.setForeground(Color.WHITE);
		lblWall.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
			
			lblStatus = new JLabel("");
			lblStatus.setForeground(Color.WHITE);
			lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			lblOgre = new JLabel("   Ogre");
			
			lblOgre.setIcon(new ImageIcon(LevelEditor.class.getResource("/resources/Ogre32option.png")));
			lblOgre.setForeground(Color.WHITE);
			lblOgre.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			lblHero = new JLabel("   Hero");
			
			lblHero.setIcon(new ImageIcon(LevelEditor.class.getResource("/resources/knight32.png")));
			lblHero.setForeground(Color.WHITE);
			lblHero.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			btnExit = new JButton("Save and exit");
			
			lblMapStatus = new JLabel("");
			lblMapStatus.setHorizontalAlignment(SwingConstants.CENTER);
			lblMapStatus.setForeground(Color.WHITE);
			lblMapStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			lblHerowarning = new JLabel("");
			lblHerowarning.setForeground(Color.WHITE);
			lblHerowarning.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblOgre)
							.addComponent(lblDoor)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblKey)
									.addComponent(lblWall))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblMapStatus, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblHero, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblHerowarning, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(16)
								.addComponent(lblWall)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblKey)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblOgre)
								.addGap(18)
								.addComponent(lblDoor))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblMapStatus, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
						.addGap(15)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblHerowarning, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
							.addComponent(lblHero, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
						.addGap(18)
						.addComponent(btnExit)
						.addGap(12))
			);
			panel.setLayout(gl_panel);
		
		lblHeight = new JLabel("Heigth");
		lblHeight.setForeground(Color.WHITE);
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblWidth = new JLabel("Width");
		lblWidth.setForeground(Color.WHITE);
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		lblResStatus = new JLabel("");
		lblResStatus.setForeground(Color.WHITE);
		lblResStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnGenerate = new JButton("Generate");
		
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHeight)
						.addComponent(lblWidth))
					.addGap(18)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblResStatus, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_topPanel.createSequentialGroup()
							.addComponent(lblResStatus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_topPanel.createSequentialGroup()
							.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHeight)
								.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblWidth)
								.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(gl_topPanel);
		
		gameScreen = new GameScreen();
		
		//gameScreen.setGameState(new GameState(2));
		gameScreen.setBackground(Color.BLACK);
		gameScreen.setFocusable(true);
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(gameScreen, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(gameScreen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
		);
		leftPanel.setLayout(gl_leftPanel);
		contentPane.setLayout(gl_contentPane);	
		
		item = "_";
		HeroPlaced = false;
		KeyPlaced = false;
		DoorPlaced = false;
	}
	
	public Boolean checkRes()
	{
		if(txtHeight.getText().equals("") || txtWidth.getText().equals("") ||
				Integer.parseInt(txtHeight.getText()) * 32 > 320 || Integer.parseInt(txtWidth.getText()) * 32 > 320 
				|| Integer.parseInt(txtHeight.getText()) * 32 < 160 || Integer.parseInt(txtWidth.getText()) * 32 < 160
				|| Integer.parseInt(txtHeight.getText()) != Integer.parseInt(txtWidth.getText()))
		{
			lblResStatus.setText("Map size must be between 5x5 and 10x10 (and even)");
			return false;
		}
		else
		{
			lblResStatus.setText("");
			return true;
		}
			
	}
	
	public boolean checkMap()
	{
		if(!HeroPlaced || !DoorPlaced || !KeyPlaced)
		{
			lblMapStatus.setText("Item(s) Missing!");
			return false;
		}
		else
			lblMapStatus.setText("");
		
		return true;
	}
	
	public void close()
	{
		this.dispose();
	}
	
	public void createEvents()
	{
		txtHeight.getDocument().addDocumentListener(new DocumentListener() 
		{
			  public void changedUpdate(DocumentEvent e) 
			  {  
				  checkRes();
			  }
			  
			  public void removeUpdate(DocumentEvent e) 
			  {
				  checkRes();
			  }
				  
			  public void insertUpdate(DocumentEvent e) 
			  {
				  checkRes();
			  } 
		});
		
		txtWidth.getDocument().addDocumentListener(new DocumentListener() 
		{
			  public void changedUpdate(DocumentEvent e) 
			  {
				    checkRes();
			  }
			  
			  public void removeUpdate(DocumentEvent e) 
			  {
				  checkRes();
			  }
				  
			  public void insertUpdate(DocumentEvent e) 
			  {
				    checkRes();
			  } 
		});
		
		btnGenerate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(checkRes())
				{
					int i;
					
					new_map = new String[Integer.parseInt(txtHeight.getText())]
							[Integer.parseInt(txtWidth.getText())];
					
					for(i = 0; i < new_map.length; i++)
						for(int j = 0; j < new_map[i].length; j++)
						{
							if(i == 0 || i == new_map.length - 1 || j == 0 || j == new_map[i].length - 1)
								new_map[i][j] = "X";
							else
								new_map[i][j] = "_";
						}
					
					Map.setLevel(2, new_map);
					gs2 = new GameState(2);
					gameScreen.setGameState(gs2);
					gameScreen.paint();
					gameScreen.adjustSize();
					HeroPlaced = false;
					KeyPlaced = false;
					DoorPlaced = false;
				}
			}
		});
		
		lblWall.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				item = "X";
			}
		});
		
		lblKey.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				item = "k";
			}
		});
		
		lblOgre.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				item = "O";
			}
		});
		
		lblDoor.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				item = "I";
			}
		});
		
		lblHero.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				item = "A";
			}
		});
		
		gameScreen.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				if(gameScreen.getGameState() == null)
					{
						lblResStatus.setText("Must generate a map");
						return;
					}
				
				String[][] map = Map.getLevel2();
				
				if(item.equals("A") && HeroPlaced)
				{
					lblHerowarning.setText("You can only place one hero.");
					return;
				}
				
				if(!item.equals("A") && map[arg0.getY() / 32][arg0.getX() / 32].equals("A") && HeroPlaced)
					HeroPlaced = false;
				
				lblHerowarning.setText("");
				
				if(item != "I")
					if(arg0.getY() / 32 == 0 || arg0.getY() / 32 == map.length - 1 
							|| arg0.getX() / 32 == 0 || arg0.getX() / 32 == map.length - 1)
					{
						lblResStatus.setText("Cannont place items on the edge");
						return;
					}
					
				lblResStatus.setText("");
				
				map[arg0.getY() / 32][arg0.getX() / 32] = item;
				
				switch(item)
				{
					case "A":
						HeroPlaced = true;
						break;
						
					case "k":
						KeyPlaced = true;
						break;
						
					case "I":
						DoorPlaced = true;
						break;
						
					default:
						break;
				}
				
				Map.setLevel(2, map);
				
				gameScreen.updateMap();
				gameScreen.paint();
				gameScreen.adjustSize();
			}
		});
		
		btnExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!checkMap())
					return;
				
				close();
			}
		});
	}
}
