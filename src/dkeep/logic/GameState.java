package dkeep.logic;
import dkeep.logic.Hero;


public class GameState 
{
	private int level_no;
	private boolean victory, game_over;
	
	public GameState(int level)
	{
		level_no = level;
		victory = false;
		game_over = false;
	}
	
	public int getLevel_no() 
	{
		return level_no;
	}

	public void setLevel_no(int level_no) 
	{
		this.level_no = level_no;
	}

	public boolean isVictory() 
	{
		return victory;
	}

	public void setVictory(boolean victory) 
	{
		this.victory = victory;
	}

	public boolean isGame_over() 
	{
		return game_over;
	}

	public void setGame_over(boolean game_over) 
	{
		this.game_over = game_over;
	}

	public boolean test_collision(String map[][], String enemy, Hero hero)
	{
		if((map[hero.getY()][hero.getX() + 1] == enemy) 
				|| (map[hero.getY()][hero.getX() - 1] == enemy)
				|| (map[hero.getY() - 1][hero.getX()] == enemy) 
				|| (map[hero.getY() + 1][hero.getX()] == enemy))
		{
			if(enemy == "G" || (enemy == "O" && !hero.isArmed()))
			{
				map[hero.getY()][hero.getX()] = "_";
				this.game_over = true;
			}
			
			return true;
		}
		else
			return false;
	}	
}
