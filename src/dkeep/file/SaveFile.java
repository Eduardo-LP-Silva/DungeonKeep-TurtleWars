package dkeep.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dkeep.logic.GameState;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Ogre;

public abstract class SaveFile 
{
	private static BufferedWriter map_file; 
	private static BufferedWriter level_file;
	private static BufferedWriter hero_file;
	private static BufferedWriter guard_file;
	private static BufferedWriter ogre_file;
	
	public static void saveMap(GameState gs) throws IOException
	{
		map_file = new BufferedWriter( new FileWriter("src/resources/map.txt"));
		String s = "";
		
		for(int j = 0; j < gs.getCurrent_map().length; j++)
		{
			for(int i = 0; i < gs.getCurrent_map()[j].length; i++)
				
			{
				s += gs.getCurrent_map()[j][i];

			}
			s += '\n';
		}
		
		s = s.substring(0, s.length() - 1);
		
		map_file.write(s);
		
		map_file.close();
	}
	
	public static void saveToFile(GameState gs) throws FileNotFoundException
	{
		try 
		{
			saveGameState(gs);
			saveMap(gs);
			saveHero(gs.getHero());
			saveGuard(gs.getGuard());
			saveOgres(gs.getOgres());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void loadGame(GameState gs)
	{
		try 
		{
			loadGameState(gs);
			gs.setCurrent_map(SaveFile.stringToStringArray(SaveFile.getMapFromFile()));
			loadHero(gs);
			loadGuard(gs);
			loadOgre(gs);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void saveGameState(GameState gs) throws IOException
	{
		level_file = new BufferedWriter( new FileWriter("src/resources/level.txt"));
		level_file.write(Integer.toString(gs.getLevel_no()));
		level_file.write("\n");
		level_file.write(Integer.toString(gs.isVictory() ? 1 : 0));
		level_file.write("\n");
		level_file.write(Integer.toString(gs.isGame_over() ? 1 : 0));
		level_file.write("\n");
		level_file.write(Integer.toString(gs.isLever() ? 1 : 0));
		level_file.write("\n");
		level_file.write(Integer.toString(gs.isKey() ? 1 : 0));
		level_file.write("\n");
		level_file.write(Integer.toString(gs.isTest() ? 1 : 0));
		
		level_file.close();
	}
		
	public static void saveHero(Hero hero) throws IOException
	{
		hero_file = new BufferedWriter( new FileWriter("src/resources/hero.txt"));
		
		hero_file.write(Integer.toString(hero.getX()));
		hero_file.write("\n");
		hero_file.write(Integer.toString(hero.getY()));
		hero_file.write("\n");
		hero_file.write(Integer.toString(hero.isArmed() ? 1 : 0));
		
		hero_file.close();
	}
	
	public static void saveGuard(Guard guard) throws IOException
	{
		guard_file = new BufferedWriter( new FileWriter("src/resources/guard.txt"));
		
		guard_file.write(Integer.toString(guard.getX()));
		guard_file.write("\n");
		guard_file.write(Integer.toString(guard.getY()));
		guard_file.write("\n");
		guard_file.write(guard.getType() + "\n");
		guard_file.write(guard.getMovement() + "\n");
		guard_file.write(Integer.toString(guard.isAsleep() ? 1 : 0));
		guard_file.write("\n");
		guard_file.write(Integer.toString(guard.getTurns_asleep()));
		
		guard_file.close();
	}
	
	public static void saveOgres(ArrayList<Ogre> ogres) throws IOException
	{
		ogre_file =  new BufferedWriter( new FileWriter("src/resources/ogre.txt"));
		int i;
		
		for(i = 0; i < ogres.size(); i++)
		{
			ogre_file.write(Integer.toString(ogres.get(i).getX()));
			ogre_file.write("\n");
			ogre_file.write(Integer.toString(ogres.get(i).getY()));
			ogre_file.write("\n");
			ogre_file.write(Integer.toString(ogres.get(i).getClub().getX()));
			ogre_file.write("\n");
			ogre_file.write(Integer.toString(ogres.get(i).getClub().getY()));
			ogre_file.write("\n");
			ogre_file.write(Integer.toString(ogres.get(i).isClub_on_key() ? 1 : 0));
			ogre_file.write("\n");
			ogre_file.write(Integer.toString(ogres.get(i).isOn_top_of_key() ? 1 : 0));
			ogre_file.write("\n");
			ogre_file.write(Integer.toString(ogres.get(i).getTurns_stunned()));
			
			if(i != ogres.size() - 1)
				ogre_file.write("\n");
		}
		
		ogre_file.close();
	}
	
	public static void eraseFromFile()
	{
		PrintWriter writer = new PrintWriter(map_file);
		writer.print("");
		writer.close();
	}
	
	public static void loadGameState(GameState gs) throws FileNotFoundException, IOException
	{ 
		BufferedReader br = new BufferedReader(new FileReader("src/resources/level.txt"));
		
		if(gs == null)
			gs = new GameState(1);
		
		gs.setLevel_no(Integer.parseInt(br.readLine()));
		gs.setVictory(Integer.parseInt(br.readLine()) == 1);
		gs.setGame_over(Integer.parseInt(br.readLine()) == 1);
		gs.setLever(Integer.parseInt(br.readLine()) == 1);
		gs.setKey(Integer.parseInt(br.readLine()) == 1);
		gs.setTest(Integer.parseInt(br.readLine()) == 1);
		  
		br.close();
	}
	
	public static void loadHero(GameState gs) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("src/resources/hero.txt"));
		Hero h = new Hero(1,1);
		
		h.setX(Integer.parseInt(br.readLine()));
		h.setY(Integer.parseInt(br.readLine()));
		h.setArmed(Integer.parseInt(br.readLine()) == 1);
		
		gs.setHero(h);
		
		br.close();
	}
	
	public static void loadGuard(GameState gs) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("src/resources/guard.txt"));
		Guard g = new Guard(8,1, Guard.Guard_Type.Drunken);
	
		g.setX(Integer.parseInt(br.readLine()));
		g.setY(Integer.parseInt(br.readLine()));
		g.setType(br.readLine());
		g.setMovement(br.readLine());
		g.setAsleep(Integer.parseInt(br.readLine()) == 1);
		g.setTurns_asleep(Integer.parseInt(br.readLine()));
		
		gs.setGuard(g);
		
		br.close();
	}
	
	public static void loadOgre(GameState gs) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("src/resources/ogre.txt"));
		int i = 0;
		String x;
		ArrayList<Ogre> l = new ArrayList<Ogre>();
		
		while((x = (br.readLine())) != null)
		{
			l.add(new Ogre(Integer.parseInt(x), Integer.parseInt(br.readLine())));
			l.get(i).addClub(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
			l.get(i).setClub_on_key(Integer.parseInt(br.readLine()) == 1);
			l.get(i).setOn_top_of_key(Integer.parseInt(br.readLine()) == 1);
			l.get(i).setTurns_stunned(Integer.parseInt(br.readLine()));
			
			i++;
		}
		
		gs.setOgres(l);
		
		br.close();
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
