package dkeep.logic;

import dkeep.logic.Hero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	private File loader =  new File("/resources/save.txt");
	private BufferedWriter file; // map_file
	private BufferedWriter level_file; // map_file
	private boolean load_activated = false;
	
	public GameState(int level)
	{
		victory = false;
		test = false;
		game_over = false;
		setLevel_no(level);
		ogres = new ArrayList<Ogre>();
	}
	
	public int getLevel_no() 
	{
		return level_no;
	}
	
	public boolean isLever() 
	{
		return lever;
	}

	public void setLever(boolean lever) 
	{
		this.lever = lever;
	}

	public boolean isKey() 
	{
		return key;
	}
	
	public void setKey(boolean key) 
	{
		this.key = key;
	}
	
	
	public void setOgres(ArrayList<Ogre> ogres) 
	{
		this.ogres = ogres;
	}
	
	
	public void setTest(boolean test) 
	{
		this.test = test;
	}

	public void setLevel_no(int level) 
	{
		boolean found = false;
		
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
	
	public String[][] getCurrent_map() 
	{
		return current_map;
	}
	
	public boolean getGameOver()
	{
		return this.game_over;
	}
	
	public void setGameOver()
	{
		this.game_over = true;
	}
	

	public void setCurrent_map(String[][] current_map) 
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
	
	public void print_map()
	{
		for(int i = 0; i < this.current_map.length; i++)
		{
			for(int j = 0; j < this.current_map[i].length; j++)
				System.out.print(this.current_map[i][j]);
				
			System.out.print("\n");
		}
	}
	
	
	//file part
	
	public String save_map()
	{
		String s = "";
		
		for(int j = 0; j < this.getCurrent_map().length; j++)
		{
			for(int i = 0; i < this.getCurrent_map()[j].length; i++)
				
			{
				s += this.current_map[j][i];

			}
			s += '\n';

		}
		
		String final_s = s.substring(0, s.length() - 1);
		return final_s;
	}

	public void saveToFile()
	{
		try 
		{
		
			file = new BufferedWriter( new FileWriter("/home/tomas/git/LPOO1718_T4G4/src/resources/save.txt"));
			level_file = new BufferedWriter( new FileWriter("/home/tomas/git/LPOO1718_T4G4/src/resources/level.txt")); 
			
		    file.write(save_map());
		    file.close();
		    
		    String s = "";
		    s += this.getLevel_no();
		    s +='\n';
		    s += this.getGuard().getType();
		    
		    level_file.write(s);
		    level_file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void eraseFromFile()
	{
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}

	

	public String[] getLevelAndGuardFromFile() throws FileNotFoundException, IOException
	{ 
		String s[] = new String[2];
		try(BufferedReader br = new BufferedReader(new FileReader("/home/tomas/git/LPOO1718_T4G4/src/resources/level.txt")))
		{
		  String line;

		  int i = 0;
		  while( (line = br.readLine()) != null)
		  {
			  s[i] = line;  

			  i++;	
		 
		  }
		}
		
		for (int i = 0; i < s.length; i++)
		{
			System.out.print(s[i]);
		}
		return s;
	}
	
	
	
	public String getMapFromFile() throws FileNotFoundException, IOException
	{

		String lineAndEnter = "";
		try(BufferedReader br = new BufferedReader(new FileReader("/home/tomas/git/LPOO1718_T4G4/src/resources/save.txt")))
		{
		  String line;
		  while( (line = br.readLine()) != null)
		  {
				lineAndEnter = lineAndEnter + line + '\n';
		  }
		}
		return lineAndEnter;
		
	}

	
	public String[][] stringToStringArray(String s) throws FileNotFoundException, IOException
	{
		s = this.getMapFromFile();
		
        int index_line = s.indexOf("\n");
		
		String[][] map = new String[1][index_line];
		
		String[] array = new String[index_line];
		
		
		int begin = 0;
		int end = 0;
		for (int j = 0; j < index_line; j++)
		{
			
		     end = s.indexOf('\n', begin);
		     
		     if (end == -1)
		    	 break;
		     
		     array[j] = s.substring(begin, end+1);
		     begin = end + 1;
		}
		
		
		map[0] = array;
		
	   return map;	
	}
	
	public boolean getLoadActivated()
	{
		return this.load_activated;
	}
	
	public void setLoadActivated()
	{
		this.load_activated = true;
	}
	
}