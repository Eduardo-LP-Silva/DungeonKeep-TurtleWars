package dkeep.logic;
import dkeep.logic.Hero;


public class GameState 
{
	public int level_no;
	public boolean victory;
	
	public GameState(int level)
	{
		level_no = level;
		victory = false;
	}
	
	public boolean game_over(String map[][], String enemy, Hero hero)
	{
		if((map[hero.y][hero.x + 1] == enemy) || (map[hero.y][hero.x - 1] == enemy)
				|| (map[hero.y - 1][hero.x] == enemy) || (map[hero.y + 1][hero.x] == enemy))
		{
			map[hero.y][hero.x] = "_";
			return true;
		}
		else
			return false;
	}
}
