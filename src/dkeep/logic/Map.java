package dkeep.logic;
import java.lang.String;
import dkeep.logic.Character;

public class Map 
{
	
	public static Character door1_1, door1_2, door1_3, door1_4, door1_5, door1_6, 
	door1_7, door2_1, door_t1, door_t2;
	private boolean lever, key;
	private String level[][];
	
	public void print_map()
	{
		for(int i = 0; i < this.level.length; i++)
		{
			for(int j = 0; j < this.level[i].length; j++)
				System.out.print(this.level[i][j]);
				
			System.out.print("\n");
		}
	}
					
	public Map(int no)
	{
		switch(no)
		{
			case 1:
				lever = false;
				
				level = new String[][]{ 
						{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}, 
						{"X", "H", "_", "_", "I", "_", "X", "_", "G", "X"},
						{"X", "X", "X", "_", "X", "X", "X", "_", "_", "X"},
						{"X","_","I","_","I","_","X","_","_","X"},
						{"X", "X", "X", "_", "X", "X", "X", "_", "_", "X"},
						{"I", "_", "_", "_", "_", "_", "_", "_", "_", "X"},
						{"I", "_", "_", "_", "_", "_", "_", "_", "_", "X"},
						{"X", "X", "X", "_", "X", "X", "X", "X", "_", "X"},
						{"X","_","I","_","I","_","X","k","_","X"},
						{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}};
						
				door1_1 = new Character(4,1);
				door1_2 = new Character(2,3);
				door1_3 = new Character(4,3);
				door1_4 = new Character(0,5);
				door1_5 = new Character(0,6);
				door1_6 = new Character(2,8);
				door1_7 = new Character(4,8);
				door2_1 = new Character(0,1);
				break;
				
			case 2:
				level = new String[][]{{"X", "X", "X", "X", "X", "X", "X", "X", "X"},
					{"I", "_", "_", "_", "_", "_", "_", "k", "X"},
					{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
					{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
					{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
					{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
					{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
					{"X", "H", "_", "_", "_", "_", "_", "_", "X"},
					{"X", "X", "X", "X", "X", "X", "X", "X", "X"}};
					
					door2_1 = new Character(0,1);
					
			key = false;
			break;
			
			case 0:
				level = new String[][]{
						{"X", "X", "X", "X", "X"},
						{"X", "H", "_", "G", "X"},
						{"I", "_", "_", "_", "X"},
						{"I", "k", "_", "_", "X"},
						{"X", "X", "X", "X", "X"}};
						
				door_t1 = new Character(0,2);
				door_t2 = new Character(0,3);
				break;
				
			default:
				break;
			}
											
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

	public String[][] getLevel() 
	{
		return level;
	}

	public void setLevel(String[][] level) 
	{
		this.level = level;
	}
	
	public String map_to_string()
	{
		String map = "";
		
		for(int i = 0; i < getLevel().length; i++)
			{
				for(int j = 0; j < getLevel()[i].length; j++)
					map += getLevel()[i][j];
				
				map += "\n";
			}
			
				
		return map;	
	}

}
