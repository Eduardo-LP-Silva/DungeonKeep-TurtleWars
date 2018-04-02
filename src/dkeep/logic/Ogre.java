package dkeep.logic;

import java.util.Random;

public class Ogre extends Character
{
	private Character club;
	private boolean club_on_key, on_top_of_key;
	private int turns_stunned;
	
	/** 
	 * Constructor of the class. The respective club is initiated with the same coordinates as the 
	 * ogre.
	 * It is assumed the ogre is not of top of the key neither is its club.
	 * The ogre starts unstunned.
	 * 
	 * @param x The x coordinate of the new ogre.
	 * @param y The y coordinate of the new ogre.
	 * 
	 */
	public Ogre(int x, int y)
	{
		super(x,y);
		club = new Character(x,y);
		club_on_key = false;
		on_top_of_key = false;
		turns_stunned = 0;
	}
	

	/**
	 * Changes the value of the on_top_of_key variable.
	 * 
	 * @param on_top_of_key New boolean value for on_top_of_key variable.
	 * 
	 */
	public void setOn_top_of_key(boolean on_top_of_key) 
	{
		this.on_top_of_key = on_top_of_key;
	}
	
	/**
	 * Changes the value of the club_on_key variable.
	 * 
	 * @param club_on_key New boolean value for club_on_key variable.
	 * 
	 */
	public void setClub_on_key(boolean club_on_key) 
	{
		this.club_on_key = club_on_key;
	}

	/**
	 * Returns the ogre club.
	 * 
	 * @return The ogre club.
	 * 
	 */	
	public Character getClub() 
	{
		return club;
	}

	/**
	 * Changes the ogre's club.
	 * 
	 * @param club New ogre's club .
	 * 
	 */
	public void setClub(Character club) 
	{
		this.club = club;
	}

	/**
	 * Returns the number of turns the ogre has been stunned consecutively since last time.
	 * 
	 * @return The value of the turns_stunned variable.
	 * 
	 */	
	public int getTurns_stunned() 
	{
		return turns_stunned;
	}

	/**
	 * Modifies the number of turns the ogre has been stunned consecutively since last time.
	 * 
	 * @param turns_stunned New value for turns_stunned variable.
	 * 
	 */
	public void setTurns_stunned(int turns_stunned) 
	{
		this.turns_stunned = turns_stunned;
	}
	
	/**
	 * Returns true if the ogre is on top of the key.
	 * 
	 * @return The boolean value of the on_top_of_key variable.
	 * 
	 */
	public boolean isOn_top_of_key() 
	{
		return on_top_of_key;
	}
	
	/**
	 * Returns true if the club is on top of the key.
	 * 
	 * @return The boolean value of club_on_key variable.
	 * 
	 */
	public boolean isClub_on_key() 
	{
		return club_on_key;
	}

	/**
	 * Adds a new club to the ogre. This function serves the same purpose as setClub(), however, due to
	 * the name of the Character class, it was needed to create another function that constructed the
	 * club itself. 
	 * 
	 * @param x The x coordinate of the new club.
	 * @param y The y coordinate of the new club.
	 * 
	 */
	public void addClub(int x, int y)
	{
		club = new Character(x,y);
	}

	/**
	 * Sets the value of the (possible) next X and Y coordinates in a random fashion.
	 * 
	 * @param gs The current gamestate.
	 * 
	 */
	public void move(GameState gs)
	{
		Random rand = new Random();
		int on = rand.nextInt(4) + 1;
		
		switch(on)
		{
			case 1:
				ny = y -1;
				break;
				
			case 2:
				nx = x + 1;
				break;
				
			case 3:
				ny = y + 1;
				break;
				
			case 4:
				nx = x - 1;
				break;
				
			default:
				break;	
		}
		
		action(gs);
	}
	
	/**
	 * Defines what the ogre will do according to its possible coordinates.
	 * The Ogre will only move if the nx and ny coordinates point to an empty space, the key or
	 * another ogre.
	 * After the ogre has moved (or not!) he will swing its club and perform the correspondent action
	 * with it.
	 * 
	 * @param gs The current gamestate.
	 */
	public void action(GameState gs)
	{
		Random rand = new Random();
		
		if(this.club_on_key)
			gs.getCurrent_map()[club.getY()][club.getX()] = "k";
		else
			gs.getCurrent_map()[club.getY()][club.getX()] = "_";
		
		switch(gs.getCurrent_map()[ny][nx])
		{
			case "X":
				gs.getCurrent_map()[y][x] = "O";
				break;
				
			case "I":
				gs.getCurrent_map()[y][x] = "O";
				break;
				
			case "_":
				
				if(on_top_of_key)
				{
					gs.getCurrent_map()[y][x] = "k";
					on_top_of_key = false;	
				}
				else
					gs.getCurrent_map()[y][x] = "_";
				
				x = nx;
				y = ny;
				gs.getCurrent_map()[y][x] = "O";
				break;
				
			case "k":
				gs.getCurrent_map()[y][x] = "_";
				x = nx;
				y = ny;
				gs.getCurrent_map()[y][x] = "$";
				setOn_top_of_key(true);
				
			case "S":
				gs.getCurrent_map()[y][x] = "O";
				break;
				
			case "O":
				gs.getCurrent_map()[y][x] = "_";
				x = nx;
				y = ny;
				gs.getCurrent_map()[y][x] = "O";
				break;
				
			default:
				break;
		}
		
		swing_club(rand.nextInt(4) + 1);
		smash(gs);
	}
	
	/**
	 * Defines the club's new possible coordinates randomly.
	 * 
	 * @param cn A random int from 1 to 4: 1 - Above; 2 - To the right; 3 - Below; 4 - To the left.
	 */
	public void swing_club(int cn)
	{
		switch(cn)
		{
			case 1:
				club.nx = x;
				club.ny = y - 1;
				break;
				
			case 2:
				club.nx = x + 1;
				club.ny = y;
				break;
				
			case 3:
				club.nx = x;
				club.ny = y + 1;
				break;
				
			case 4:
				club.nx = x - 1;
				club.ny = y;
				break;
				
			default:
				break;
		}
	}
	
	/**
	 * Checks the club's action according to the next possible coordinates.
	 * The club will only appear on the map if the value of its nx and ny coordinates point to an
	 * empty space or to the key.
	 * 
	 * @param gs The current gamestate.
	 */
	public void smash(GameState gs)
	{
		switch(gs.getCurrent_map()[club.ny][club.nx])
		{		
			case "_":
				
				if(club_on_key)
				{
					gs.getCurrent_map()[club.y][club.x] = "k";
					club_on_key = false;
				}
				else
					gs.getCurrent_map()[club.y][club.x] = "_";
				
				club.x = club.nx;
				club.y = club.ny;
				gs.getCurrent_map()[club.y][club.x] = "*";
				break;
				
			case "k":
				gs.getCurrent_map()[club.y][club.x] = "_";
				club.y = club.ny;
				club.x = club.nx;
				gs.getCurrent_map()[club.y][club.x] = "$";
				setClub_on_key(true);
				break;
				
			default:
				break;
				
		}
	}
}
