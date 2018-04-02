package dkeep.logic;
import java.lang.String;
import java.util.Arrays;

import dkeep.logic.Character;

public abstract class Map 
{
	
	private static Character door1_1 = new Character(4,1), 
			door1_2 =  new Character(2,3), 
			door1_3 = new Character(4,3), 
			door1_4 = new Character(0,5), 
			door1_5 = new Character(0,6), 
			door1_6 = new Character(2,8), 
			door1_7 = new Character(4,8), 
			door_t1 = new Character(0,2), 
			door_t2 = new Character(0,3);
	
	private static String test_level[][] = new String[][]{
		{"X", "X", "X", "X", "X"},
		{"X", "H", "_", "G", "X"},
		{"I", "_", "_", "_", "X"},
		{"I", "k", "_", "_", "X"},
		{"X", "X", "X", "X", "X"}};
		
	private static String level1[][] = new String[][]{ 
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
		
	private static String level2[][] =  new String[][]{{"X", "X", "X", "X", "X", "X", "X", "X", "X"},
		{"I", "_", "_", "_", "_", "_", "_", "k", "X"},
		{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
		{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
		{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
		{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
		{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
		{"X", "A", "_", "_", "_", "_", "_", "_", "X"},
		{"X", "X", "X", "X", "X", "X", "X", "X", "X"}};
	
	
	/*
	 * Returns leve1.
	 * 
	 * @return level1.
	 */
	public static String[][] getLevel1() 
	{
		return level1;
	}
	
	/*
	 * Returns level2.
	 * 
	 * @return level2.
	 */
	public static String[][] getLevel2() 
	{
		return level2;
	}
	
	/*
	 * Returns the test leve1.
	 * 
	 * @return The test level.
	 */
	public static String[][] getTestLevel() 
	{
		return test_level;
	}
	
	/*
	 * Sets the level.
	 * 
	 * @param level_no The number of the level which be played.
	 * @param new_level The double string array containing the map.
	 */
	public static void setLevel(int level_no, String[][] new_level)
	{
		switch(level_no)
		{
			case 0:
				test_level = new_level;
				break;
				
			case 1:
				level1 = new_level;
				break;
				
			case 2:
				level2 = new_level;
				break;
				
			default:
				break;
		}
	}
	
	/*
	 * Copies the level
	 * 
	 * @param toCopy The level to be copied.
	 */
	public static String[][] copyLevel(String[][] toCopy)
	{
		String[][] copy = new String[toCopy.length][];
		
		for(int i = 0; i < copy.length; i++)
			copy[i] = Arrays.copyOf(toCopy[i], toCopy[i].length);
		
		return copy;
	}

	/*
	 * Returns the door number 1 of level 1.
	 * 
	 * @return The door number 1.
	 */
	public static Character getDoor1_1() 
	{
		return door1_1;
	}

	/*
	 * Returns the door number 2 of level 1.
	 * 
	 * @return The door number 2.
	 */
	public static Character getDoor1_2() 
	{
		return door1_2;
	}

	/*
	 * Returns the door number 3 of level 1.
	 * 
	 * @return The door number 3.
	 */
	public static Character getDoor1_3() 
	{
		return door1_3;
	}

	
	/*
	 * Returns the door number 4 of level 1.
	 * 
	 * @return The door number 4.
	 */
	public static Character getDoor1_4() 
	{
		return door1_4;
	}

	/*
	 * Returns the door number 5 of level 1.
	 * 
	 * @return The door number 5.
	 */
	public static Character getDoor1_5() 
	{
		return door1_5;
	}

	/*
	 * Returns the door number 6 of level 1.
	 * 
	 * @return The door number 6.
	 */
	public static Character getDoor1_6() 
	{
		return door1_6;
	}

	/*
	 * Returns the door number 7 of level 1.
	 * 
	 * @return The door number 7.
	 */
	public static Character getDoor1_7() 
	{
		return door1_7;
	}

	/*
	 * Returns the door number 1 of test level.
	 * 
	 * @return The door number 1.
	 */
	public static Character getDoor_t1() 
	{
		return door_t1;
	}

	/*
	 * Returns the door number 2 of test level.
	 * 
	 * @return The door number 2.
	 */
	public static Character getDoor_t2() 
	{
		return door_t2;
	}

}
