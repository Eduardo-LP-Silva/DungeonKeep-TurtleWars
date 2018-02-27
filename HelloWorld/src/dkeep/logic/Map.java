package dkeep.logic;
import java.lang.String;
import dkeep.logic.Character;

public class Map 
{
	
	public static Character door1_1, door1_2, door1_3, door1_4, door1_5, door1_6, door1_7, door2_1;
	public boolean lever, key;
	public String level[][];
					
	public Map()
	{
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
				{"X","_","I","_","I","_","X","K","_","X"},
				{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}};
				
		door1_1 = new Character(4,1);
		door1_2 = new Character(2,3);
		door1_3 = new Character(4,3);
		door1_4 = new Character(0,5);
		door1_5 = new Character(0,6);
		door1_6 = new Character(2,8);
		door1_7 = new Character(4,8);
		door2_1 = new Character(0,1);
	}
	
	public void next_level()
	{
		level = new String[][]{{"X", "X", "X", "X", "X", "X", "X", "X", "X"},
				{"I", "_", "_", "_", "O", "_", "_", "k", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "H", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "X", "X", "X", "X", "X", "X", "X", "X"}};
				
		key = false;
	}
}
