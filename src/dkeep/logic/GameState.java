package dkeep.logic;

import dkeep.logic.Hero;
import java.util.ArrayList;
import java.util.Random;

public class GameState 
{
	private int level_no;
	private boolean victory;
	private boolean game_over;
	private Map current_map;
	private Hero hero;
	private Guard guard;
	private ArrayList<Ogre> ogres;
	
	public GameState(int level)
	{
		level_no = level;
		victory = false;
		game_over = false;
		setLevel_no(level);
	}
	
	public int getLevel_no() 
	{
		return level_no;
	}

	public void setLevel_no(int level) 
	{
		switch(level)
		{
			case 0:
				current_map = new Map(level);
				hero = new Hero(1,1);
				guard = new Guard(3,1, Guard.Guard_Type.Rookie);
		
			case 1:
				current_map = new Map(level);
				hero = new Hero(1,1);
				guard = new Guard(8,1, Guard.Guard_Type.Drunken);
				break;
				
			case 2:
				current_map = new Map(level);
				hero = new Hero(1,7);
				hero.setArmed(true);
				ogres = new ArrayList<Ogre>();
				ogres.add(new Ogre(4, 1));
				ogres.add(new Ogre(7,5));
				ogres.add(new Ogre(1, 4));
				break;
				
			default:
				break;
				
		}
		
		level_no = level;
	}
	
	public Map getCurrent_map() 
	{
		return current_map;
	}

	public void setCurrent_map(Map current_map) 
	{
		this.current_map = current_map;
	}

	
	public ArrayList<Ogre> getOgres() 
	{
		return ogres;
	}

	public Hero getHero() 
	{
		return hero;
	}

	public void setHero(Hero hero) 
	{
		this.hero = hero;
	}

	public Guard getGuard() 
	{
		return guard;
	}

	public void setGuard(Guard guard) 
	{
		this.guard = guard;
	}

	public boolean isVictory() 
	{
		return victory;
	}

	public void setVictory(boolean vic) 
	{
		
		victory = vic;
		
		if(vic)
			System.out.println("Victory!");
	}

	public boolean isGame_over() 
	{
		return game_over;
	}

	public void setGame_over(boolean game_over) 
	{
		this.game_over = game_over;
	}
	
	public void updateMap()
	{
		getCurrent_map().getLevel()[hero.getY()][hero.getX()] = "H";
		
		switch(level_no)
		{
			case 0:
				break;
				
			case 1:
				
				if(guard.isAsleep())
					getCurrent_map().getLevel()[guard.getY()][guard.getX()] = "g";
				else
					getCurrent_map().getLevel()[guard.getY()][guard.getX()] = "G";
				
				break;
				
			case 2:
				
				if(current_map.isKey())
					getCurrent_map().getLevel()[hero.getY()][hero.getX()] = "K";
				else
					getCurrent_map().getLevel()[hero.getY()][hero.getX()] = "A";
				
				
				
			default:
				break;
		}
		
		current_map.print_map();
	}
	
	public void moveOgres()
	{
		Random rand = new Random();
		
		for(int i = 0; i < ogres.size(); i++)
		{
			if(ogres.get(i).turns_stunned == 0)
			{
				ogres.get(i).move(rand.nextInt(4) + 1);
				ogres.get(i).action(this);
				ogres.get(i).swing_club(rand.nextInt(4) + 1);
				ogres.get(i).smash(this);
			}
			else
			{
				if(ogres.get(i).turns_stunned == 1)
				{
					ogres.get(i).turns_stunned++;
					ogres.get(i).swing_club(rand.nextInt(4) + 1);
					ogres.get(i).smash(this);
				}
				else
				{
					ogres.get(i).turns_stunned = 0;
					ogres.get(i).swing_club(rand.nextInt(4) + 1);
					ogres.get(i).smash(this);
				}
			}
				
		}
	}

	public void stunOgres()
	{
		for(int i = 0; i < ogres.size(); i++)
		{
			if(ogres.get(i).turns_stunned == 0)
			{
				ogres.get(i).turns_stunned++;
				getCurrent_map().getLevel()[ogres.get(i).getY()][ogres.get(i).getX()] = "8";
				break;
			}
	
		}
	}
	
	public boolean test_collision(String enemy)
	{
		if((current_map.getLevel()[hero.getY()][hero.getX() + 1].equals(enemy)) 
				|| (current_map.getLevel()[hero.getY()][hero.getX() - 1].equals(enemy))
				|| (current_map.getLevel()[hero.getY() - 1][hero.getX()].equals(enemy)) 
				|| (current_map.getLevel()[hero.getY() + 1][hero.getX()].equals(enemy)))
		{
			if(enemy == "G" || (enemy == "O" && !hero.isArmed()))
			{
				current_map.getLevel()[hero.getY()][hero.getX()] = "_";
				setGame_over(true);
				System.out.print("Game Over\n");
			}
			
			return true;
		}
		else
			return false;
	}	
}
