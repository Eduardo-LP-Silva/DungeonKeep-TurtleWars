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

public abstract class SaveFile 
{
	private static BufferedWriter file; // map_file
	private static BufferedWriter level_file;
	
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
			file = new BufferedWriter(new FileWriter("src/resources/save.txt"));
			level_file = new BufferedWriter( new FileWriter("src/resources/level.txt")); 
			
		    file.write(save_map(gs));
		    file.close();
		    
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
	
	public static void eraseFromFile()
	{
		PrintWriter writer = new PrintWriter(file);
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
		
		try(BufferedReader br = new BufferedReader(new FileReader("src/resources/save.txt")))
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
