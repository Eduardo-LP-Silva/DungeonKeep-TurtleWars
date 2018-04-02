package dkeep.logic;

import dkeep.logic.Hero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;

public class GameState 
{
	private int level_no;
	private boolean victory;
	private boolean game_over;
	private String[][] current_map;
	private Hero hero;
	private Guard guard;
	private ArrayList<Ogre> ogres;
	private boolean lever, key, test;
	private boolean load_activated = false;
	
	
	/* 
	 * Constructor of the class.
	 * 
	 * @param level The level of the gamestate created.
	 * 
	 */
	public GameState(int level)
	{
		victory = false;
		test = false;
		game_over = false;
		setLevel_no(level);
		ogres = new ArrayList<Ogre>();
	}
	
	/*
	 * Returns the number of the level.
	 * 
	 * @return The number of the level.
	 * 
	 */	
	public int getLevel_no() 
	{
		return level_no;
	}
	
	/*
	 * Checks if is lever.
	 * 
	 * @return True if is lever.
	 * 
	 */	
	public boolean isLever() 
	{
		return lever;
	}

	/*
	 * Modifies the boolean value of lever variable.
	 * 
	 * @param lever The value.
	 * 
	 */
	public void setLever(boolean lever) 
	{
		this.lever = lever;
	}

	/*
	 * Checks if is key.
	 * 
	 * @return True if is key.
	 * 
	 */	
	public boolean isKey() 
	{
		return key;
	}
	
	/*
	 * Modifies the boolean value of key variable.
	 * 
	 * @param key The value.
	 * 
	 */
	public void setKey(boolean key) 
	{
		this.key = key;
	}
	
	/*
	 * Modifies array containing the ogres.
	 * 
	 * @param ogres The array with the ogres.
	 * 
	 */
	public void setOgres(ArrayList<Ogre> ogres) 
	{
		this.ogres = ogres;
	}
	
	/*
	 * Modifies the boolean value of test.
	 * 
	 * @param test The boolean value.
	 * 
	 */
	public void setTest(boolean test) 
	{
		this.test = test;
	}
	
	/*
	 * Checks if it's the test level.
	 * 
	 * @return True if it's the test level.
	 * 
	 */
	public boolean isTest() 
	{
		return test;
	}

	/*
	 * Sets the level of the gamestate.
	 * 
	 * @param level The level.
	 * 
	 */
	public void setLevel_no(int level) 
	{
		boolean found = false;
		int[] coords;
		
		switch(level)
		{
			case 0:
				current_map = Map.copyLevel(Map.getTestLevel());
				hero = new Hero(1,1);
				guard = new Guard(3,1, Guard.Guard_Type.Rookie);
				break;
		
			case 1:
				current_map = Map.copyLevel(Map.getLevel1());
				hero = new Hero(1,1);
				guard = new Guard(8,1, Guard.Guard_Type.Drunken);
				
				break;
				
			case 2:
				current_map = Map.copyLevel(Map.getLevel2());
				
				for(int i = 0; i < current_map.length; i++)
					for(int j = 0; j < current_map[i].length; j++)
						if(current_map[i][j].equals("H") || current_map[i][j].equals("A"))
							{
								hero = new Hero(j, i);
								found = true;
							}
				
				if(!found)
					hero = new Hero(1,7);
					
				hero.setArmed(true);
				break;
				
			default:
				break;
				
		}
		
		level_no = level;
	}
	
	/*
	 * Sets the number of ogres to add to level.
	 * 
	 * @param n The number of ogres.
	 * 
	 */
	public void addOgres(int n)
	{
		Random rand = new Random();
		int x, y, i;
		
		if(n == 0)
			n = 2;
			
		for(i = 0; i < Map.getLevel2().length; i++)
			for(int j = 0; j < Map.getLevel2()[i].length; j++)
				if(Map.getLevel2()[i][j].equals("O"))
				{
					n--;
					Ogre o = new Ogre(j, i);
					
					if(Map.getLevel2()[i][j].equals("k"))
						o.setOn_top_of_key(true);
					
					ogres.add(o);
				}
		
		for(i = 0; i < n; i++)
		{
			do
			{
				x = rand.nextInt(Map.getLevel2()[0].length - 2) + 1;
				y = rand.nextInt(Map.getLevel2().length - 2) + 1;
			}
			while(Map.getLevel2()[y][x].equals("X") || Map.getLevel2()[y][x].equals("H")
					|| Map.getLevel2()[y][x].equals("A"));
			
			ogres.add(new Ogre(x,y));
		}
	}
	
	/*
	 * Checks if the load button is pressed.
	 * 
	 * @return True if it was pressed.
	 * 
	 */
	public boolean getLoadActivated()
	{
		return this.load_activated;
	}
	
	/*
	 * Sets that the load button was pressed .
	 * 
	 */
	public void setLoadActivated()
	{
		this.load_activated = true;
	}
	
	/*
	 * Returns the current map.
	 * 
	 * @return The current map.
	 * 
	 */
	public String[][] getCurrent_map() 
	{
		return current_map;
	}
	
	/*
	 * Checks if the game is over.
	 * 
	 * @return True if the game is over..
	 * 
	 */
	public boolean getGameOver()
	{
		return this.game_over;
	}
	
	/*
	 * Sets that the game is over.
	 * 
	 */
	public void setGameOver()
	{
		this.game_over = true;
	}
	
	/*
	 * Modifies the current map.
	 * 
	 * @param current_map The new map.
	 * 
	 */
	public void setCurrent_map(String[][] current_map) 
	{
		this.current_map = current_map;
	}

	/*
	 * Returns the array containing the ogres.
	 * 
	 * @return The array with ogres.
	 * 
	 */
	public ArrayList<Ogre> getOgres() 
	{
		return ogres;
	}

	/*
	 * Returns the hero.
	 * 
	 * @return The hero.
	 * 
	 */
	public Hero getHero() 
	{
		return hero;
	}

	/*
	 * Modifies the hero.
	 * 
	 * @param hero The new hero.
	 * 
	 */
	public void setHero(Hero hero) 
	{
		this.hero = hero;
	}

	/*
	 * Returns the guard.
	 * 
	 * @return The guard.
	 * 
	 */
	public Guard getGuard() 
	{
		return guard;
	}

	/*
	 * Modifies the guard.
	 * 
	 * @param guard The new guard.
	 * 
	 */
	public void setGuard(Guard guard) 
	{
		this.guard = guard;
	}

	/*
	 * Checks if the game ended with a victory.
	 * 
	 * @return The boolean value of victory(true if the game is over with victory).
	 */
	public boolean isVictory() 
	{
		return victory;
	}

	/*
	 * Sets the game ended with a victory.
	 * 
	 * @param vic The boolean value that can be true or false.
	 *
	 */
	public void setVictory(boolean vic) 
	{
		victory = vic;
	}

	/*
	 * Checks if the game is over.
	 * 
	 * @return The boolean value of game_over(true if the game is over).
	 */
	public boolean isGame_over() 
	{
		return game_over;
	}

	/*
	 * Sets the game over.
	 * 
	 * @param game_over The boolean value that can be true or false.
	 * 
	 */	
	public void setGame_over(boolean game_over) 
	{
		this.game_over = game_over;
	}
	
	/*
	 * Updates the map.
	 * 
	 */	
	public void updateMap()
	{
		switch(level_no)
		{
			case 0:
	
				
			case 1:
				
				getCurrent_map()[hero.getY()][hero.getX()] = "H";
				
				if(guard.isAsleep())
					getCurrent_map()[guard.getY()][guard.getX()] = "g";
				else
					getCurrent_map()[guard.getY()][guard.getX()] = "G";
				
				break;
				
			case 2:
				
				if(isKey())
					getCurrent_map()[hero.getY()][hero.getX()] = "K";
				else
					if(!getHero().isArmed())
						getCurrent_map()[hero.getY()][hero.getX()] = "H";
					else
						getCurrent_map()[hero.getY()][hero.getX()] = "A";
				
				for(int i = 0; i < ogres.size(); i++)
				{
					if(ogres.get(i).getTurns_stunned() > 0)
						getCurrent_map()[ogres.get(i).getY()][ogres.get(i).getX()] = "8";
					else
						if(ogres.get(i).isOn_top_of_key())
							getCurrent_map()[ogres.get(i).getY()][ogres.get(i).getX()] = "$";
						else
							getCurrent_map()[ogres.get(i).getY()][ogres.get(i).getX()] = "O";
				}
				
			default:
				break;
		}
	}
	
	/*
	 * Moves the ogres.
	 * 
	 */	
	public void moveOgres()
	{
		Random rand = new Random();
		
		if(test)
			return;
		
		for(int i = 0; i < ogres.size(); i++)
		{
			if(ogres.get(i).getTurns_stunned() == 0)
				ogres.get(i).move(this);
			else
			{
				if(ogres.get(i).getTurns_stunned() == 1)
					ogres.get(i).setTurns_stunned(ogres.get(i).getTurns_stunned() + 1);
				else
					ogres.get(i).setTurns_stunned(0);
				
				ogres.get(i).swing_club(rand.nextInt(4) + 1);
				ogres.get(i).smash(this);
			}	
		}
		
		if(hero.isArmed())
			stunOgres();
	}

	/*
	 * Stuns ogre if the coordinates of the hero are adjacent to him.
	 * 
	 */	
	public void stunOgres()
	{
		for(int i = 0; i < ogres.size(); i++)
		{
			if((ogres.get(i).getY() == hero.getY() + 1 && ogres.get(i).getX() == hero.getX()) 
					|| (ogres.get(i).getY() == hero.getY() - 1 && ogres.get(i).getX() == hero.getX())
					|| (ogres.get(i).getX() == hero.getX() + 1 && ogres.get(i).getY() == hero.getY())
					|| (ogres.get(i).getX() == hero.getX() - 1 && ogres.get(i).getY() == hero.getY()))
			{
				ogres.get(i).setTurns_stunned(ogres.get(i).getTurns_stunned() + 1);
				getCurrent_map()[ogres.get(i).getY()][ogres.get(i).getX()] = "8";
			}
	
		}
	}
	/*
	 * Checks if the game is over.
	 * 
	 * @return True if the game is over.
	 * 
	 */	
	public boolean test_game_over()
	{
		switch(level_no)
		{
			case 0:
				game_over = test_collision("G");
				return game_over;
		
			case 1:
				
				game_over = test_collision("G");
				return game_over;
				
			case 2:
				
				if(!hero.isArmed())
					game_over = test_collision("O");
				else
					game_over = test_collision("*") || test_collision("$");
				
				return game_over;
				
			default:
				return false;
		}
	}
	
	/*
	 * Checks if the hero collides with an enemy.
	 * 
	 * @param enemy The object to test the collision with.
	 * .
	 * @return True if the coordinates of the hero and the enemy are adjacent.
	 * 
	 */	
	public boolean test_collision(String enemy)
	{	
		if((current_map[hero.getY()][hero.getX() + 1].equals(enemy)) 
				|| (current_map[hero.getY()][hero.getX() - 1].equals(enemy))
				|| (current_map[hero.getY() - 1][hero.getX()].equals(enemy)) 
				|| (current_map[hero.getY() + 1][hero.getX()].equals(enemy)))
			return true;
		else
			return false;
	}	
	
	/*
	 * Auxiliary function that converts the current map to a string.
	 * 
	 */	
	public String map_to_string()
	{
		String map = "";
		
		for(int i = 0; i < current_map.length; i++)
			{
				for(int j = 0; j < current_map[i].length; j++)
					map += current_map[i][j];
				
				map += "\n";
			}
			
				
		return map;	
	}
	
	/*
	 * Auxiliary function to check if current map is printing the right way.
	 * 
	 */	 
	public void print_map()
	{
		for(int i = 0; i < this.current_map.length; i++)
		{
			for(int j = 0; j < this.current_map[i].length; j++)
				System.out.print(this.current_map[i][j]);
				
			System.out.print("\n");
		}
	}
	
}