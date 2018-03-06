package dkeep.logic;
import dkeep.logic.Hero;


public class GameState 
{
	public int level_no;
	public boolean victory, game_over;
	
	public GameState(int level)
	{
		level_no = level;
		victory = false;
		game_over = false;
	}
	
	public boolean test_collision(String map[][], String enemy, Hero hero)
	{
		if((map[hero.y][hero.x + 1] == enemy) || (map[hero.y][hero.x - 1] == enemy)
				|| (map[hero.y - 1][hero.x] == enemy) || (map[hero.y + 1][hero.x] == enemy))
		{
			if(enemy == "G" || (enemy == "O" && !hero.armed))
			{
				map[hero.y][hero.x] = "_";
				this.game_over = true;
			}
			
			return true;
		}
		else
			return false;
	}	
}
