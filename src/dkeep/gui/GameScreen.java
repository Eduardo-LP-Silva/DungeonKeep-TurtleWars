package dkeep.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import dkeep.logic.GameState;
import dkeep.logic.Map;

public class GameScreen extends JPanel
{
	private GameState gs;
	private BufferedImage knight;
	private BufferedImage guard;
	private BufferedImage wall;
	private BufferedImage key;
	private BufferedImage door_open;
	private BufferedImage door_closed;
	private BufferedImage floor;
	private BufferedImage ogre;
	private BufferedImage guard_sleeping;
	private BufferedImage stick;
	private BufferedImage lever;
	private BufferedImage clubOnKey;
	private BufferedImage ogreOnKey;
	private BufferedImage ogreStunned;
	
	
	public GameScreen() 
	{
		try
		{
			wall =  ImageIO.read(GameScreen.class.getResource("/resources/wall32.png"));
			knight = ImageIO.read(GameScreen.class.getResource("/resources/knight32.png"));
			floor =  ImageIO.read(GameScreen.class.getResource("/resources/Floor32.png"));
			key =  ImageIO.read(GameScreen.class.getResource("/resources/Key32.png"));
			door_closed =  ImageIO.read(GameScreen.class.getResource("/resources/Door32.png"));
			door_open =  ImageIO.read(GameScreen.class.getResource("/resources/DoorO32.png"));
			guard  =  ImageIO.read(GameScreen.class.getResource("/resources/Bad32.png"));
			ogre  =  ImageIO.read(GameScreen.class.getResource("/resources/Ogre32option.png"));
			guard_sleeping  =  ImageIO.read(GameScreen.class.getResource("/resources/Sleep32.png"));
			stick  =  ImageIO.read(GameScreen.class.getResource("/resources/Stick32.png"));
			lever =  ImageIO.read(GameScreen.class.getResource("/resources/lever32.png"));
			clubOnKey = ImageIO.read(GameScreen.class.getResource("/resources/clubOnKey32.png"));
			ogreOnKey = ImageIO.read(GameScreen.class.getResource("/resources/ogreOnKey32.png"));
			ogreStunned = ImageIO.read(GameScreen.class.getResource("/resources/ogreStunned32.png"));
		}
		catch (IOException ex)
		{
			System.out.println("Erro loading image");
			return;
		}
	}
	
	public void setGameState(GameState g)
	{
		gs = g;
	}
	
	public GameState getGameState()
	{
		return gs;
	}
	
	public void drawMap(Graphics g)
	{
		int n_images_x = 0, n_images_y = 0;
		
		if(gs == null)
			return;
		
		for(int j = 0; j < gs.getCurrent_map().length; j++)
		{
			for(int i = 0; i < gs.getCurrent_map()[j].length; i++)
			{	
					
				switch(gs.getCurrent_map()[i][j])
				{
					case "X":
						g.drawImage(wall, n_images_y, n_images_x, this);
						break;
						
					case "H":
						g.drawImage(knight, n_images_y, n_images_x, this);
						break;
						
					case "_":
						g.drawImage(floor, n_images_y, n_images_x, this);
						break;
						
					case "K":
						g.drawImage(knight, n_images_y, n_images_x, this);
						break;
						
					case "k":
						
						if(gs.getLevel_no() == 1)
							g.drawImage(lever, n_images_y, n_images_x, this);
						else
							g.drawImage(key, n_images_y, n_images_x, this);
							
						break;
						
					case "":
						g.drawImage(floor, n_images_y, n_images_x, this);
						break;
						
					case "I": 
						g.drawImage(door_closed, n_images_y, n_images_x, this);
						break;
						
					case "S": 
						g.drawImage(door_open, n_images_y, n_images_x, this);
						break;
						
					case "G":
						g.drawImage(guard, n_images_y, n_images_x, this);
						break;
					
					case "g":
						g.drawImage(guard_sleeping, n_images_y, n_images_x, this);
						break;
					
					case "*":
						g.drawImage(stick, n_images_y, n_images_x, this);
						break;
					
					case "O": 
						g.drawImage(ogre, n_images_y, n_images_x, this);
						break;
						
					case "A":
						g.drawImage(knight, n_images_y, n_images_x, this);
						break;
						
					case "$":
						
						for(int k = 0; k < gs.getOgres().size(); k++)
							if(gs.getOgres().get(k).isClub_on_key())
								g.drawImage(clubOnKey, n_images_y, n_images_x, this);
							else
								if(gs.getOgres().get(k).isOn_top_of_key())
									g.drawImage(ogreOnKey, n_images_y, n_images_x, this);
						
						break;
						
					case "8":
						g.drawImage(ogreStunned, n_images_y, n_images_x, this);
						break;			
							
					default:
						break;
						
				}
				
				n_images_x += 32;
			}
			
			n_images_y += 32;
			n_images_x = 0;
			

		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(gs == null)
			return;
		else
			drawMap(g);
		
	}
	
	public void paint()
	{
		repaint();
	}	
	
	public void updateMap()
	{
		gs.setCurrent_map(Map.getLevel2());
	}
	
	public void adjustSize()
	{
		if(getSize().getHeight() != gs.getCurrent_map().length * 32 
				||  getSize().getWidth() != gs.getCurrent_map()[0].length * 32)
			setSize(new Dimension(gs.getCurrent_map().length * 32, gs.getCurrent_map()[0].length * 32));
			
	}
	
}
