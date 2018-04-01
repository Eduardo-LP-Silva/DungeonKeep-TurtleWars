package dkeep.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dkeep.logic.GameState;
import dkeep.logic.Guard;
import dkeep.logic.Hero;

public abstract class SaveFile 
{
	private static BufferedWriter map_file; 
	private static BufferedWriter level_file;
	private static BufferedWriter hero_file;
	private static BufferedWriter guard_file;
	private static BufferedWriter ogre_file;
	
	public static String save_map(GameState gs)
	{
		String s = "";
		
		for(int j = 0; j < gs.getCurrent_map().length; j++)
		{
			for(int i = 0; i < gs.getCurrent_map()[j].length; i++)
				
			{
				s += gs.getCurrent_map()[j][i];

			}
			s += '\n';

		}
		
		return s.substring(0, s.length() - 1);
	}
	
	public static void saveToFile(GameState gs) throws FileNotFoundException
	{
		try 
		{
			map_file = new BufferedWriter(new FileWriter("src/resources/map.txt"));
			level_file = new BufferedWriter( new FileWriter("src/resources/level.txt")); 
			
		    map_file.write(save_map(gs));
		    map_file.close();
		    
		    level_file.write(Integer.toString(gs.getLevel_no()));
		    String s = "";
		    s +='\n';
		    s += gs.getGuard().getType();
		    
		    level_file.write(s);
		    level_file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void saveGameState(GameState gs) throws IOException
	{
		level_file = new BufferedWriter( new FileWriter("src/resources/level.txt"));
		level_file.write(Integer.toString(gs.getLevel_no()));
		level_file.write("\n");
		level_file.write(gs.isVictory() ? 1 : 0);
		level_file.write("\n");
		level_file.write(gs.isGame_over() ? 1 : 0);
		level_file.write("\n");
		level_file.write(gs.isLever() ? 1 : 0);
		level_file.write("\n");
		level_file.write(gs.isKey() ? 1 : 0);
		level_file.write("\n");
		level_file.write(gs.isTest() ? 1 : 0);
	}
		
	public static void saveHero(Hero hero) throws IOException
	{
		hero_file = new BufferedWriter( new FileWriter("src/resources/hero.txt"));
		hero_file.write(hero.getX());
		hero_file.write("\n");
		hero_file.write(hero.getY());
		hero_file.write("\n");
		hero_file.write(hero.isArmed() ? 1 : 0);
	}
	
	public static void sabeGuard(Guard guard) throws IOException
	{
		guard_file = new BufferedWriter( new FileWriter("src/resources/guard.txt"));
		guard_file.write(guard.getX());
		guard_file.write("\n");
		guard_file.write(guard.getY());
		guard_file.write("\n");
		guard_file.write(guard.getMovement() + "\n");
	}
	
	public static void eraseFromFile()
	{
		PrintWriter writer = new PrintWriter(map_file);
		writer.print("");
		writer.close();
	}
	
	public static String[] getLevelAndGuardFromFile() throws FileNotFoundException, IOException
	{ 
		String s[] = new String[2];
		try(BufferedReader br = new BufferedReader(new FileReader("src/resources/level.txt")))
		{
		  String line;

		  int i = 0;
		  while( (line = br.readLine()) != null)
		  {
			  s[i] = line;  

			  i++;	
		 
		  }
		}
		
		return s;
	}
	
	public static String getMapFromFile() throws FileNotFoundException, IOException
	{
		String lineAndEnter = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader("src/resources/map.txt")))
		{
		  String line;
		  
		  while( (line = br.readLine()) != null)
		  {
				lineAndEnter = lineAndEnter + line + "\n";
		  }
		}
		
		return lineAndEnter;
	}
	
	public static String[][] stringToStringArray(String s) throws FileNotFoundException, IOException
	{
		int index_line = s.indexOf("\n"), k = 0;
		
		
		String[][] level = new String[index_line][index_line];
		
		for(int i = 0; i < level.length; i++)
		{
			for(int j = 0; j < level[i].length; j++)
				{
					level[i][j] = Character.toString(s.charAt(k));
					
					if(!(k + 1 >= s.length()))
						k++;
				}
			
			if(!(k + 1 >= s.length()))
				k++;
		}
			
	   return level;	
	}
}
