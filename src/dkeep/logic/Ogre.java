package dkeep.logic;


public class Ogre extends Character
{
	public Character club;
	public boolean club_on_key, on_top_of_key;
	public int turns_stunned;
	
	public Ogre(int x, int y)
	{
		super(x,y);
		club = new Character(5,1);
		club_on_key = false;
		on_top_of_key = false;
		turns_stunned = 0;
		nx = x;
		ny = y;
	}
	
	public void move(int on)
	{
		switch(on)
		{
			case 1:
				ny = y -1;
				break;
				
			case 2:
				nx = x + 1;
				break;
				
			case 3:
				ny = y + 1;
				break;
				
			case 4:
				nx = x - 1;
				break;
				
			default:
				break;	
		}
	}
	
	public void action(GameState gs)
	{
		switch(gs.getCurrent_map().getLevel()[ny][nx])
		{
			case "X":
				gs.getCurrent_map().getLevel()[y][x] = "O";
				break;
				
			case "I":
				gs.getCurrent_map().getLevel()[y][x] = "O";
				break;
				
			case "_":
				
				if(on_top_of_key)
				{
					gs.getCurrent_map().getLevel()[y][x] = "k";
					on_top_of_key = false;	
				}
				else
					gs.getCurrent_map().getLevel()[y][x] = "_";
				
				x = nx;
				y = ny;
				gs.getCurrent_map().getLevel()[y][x] = "O";
				break;
				
			case "k":
				gs.getCurrent_map().getLevel()[y][x] = "_";
				x = nx;
				y = ny;
				gs.getCurrent_map().getLevel()[y][x] = "$";
				on_top_of_key = true;
				
			case "S":
				gs.getCurrent_map().getLevel()[y][x] = "O";
				break;
				
			case "O":
				gs.getCurrent_map().getLevel()[y][x] = "_";
				x = nx;
				y = ny;
				gs.getCurrent_map().getLevel()[y][x] = "O";
				break;
				
			default:
				break;
		}
	}
	
	public void swing_club(int cn)
	{
		switch(cn)
		{
			case 1:
				club.nx = x;
				club.ny = y - 1;
				break;
				
			case 2:
				club.nx = x + 1;
				club.ny = y;
				break;
				
			case 3:
				club.nx = x;
				club.ny = y + 1;
				break;
				
			case 4:
				club.nx = x - 1;
				club.ny = y;
				break;
				
			default:
				break;
		}
	}
	
	public void smash(GameState gs)
	{
		switch(gs.getCurrent_map().getLevel()[club.ny][club.nx])
		{		
			case "_":
				
				if(club_on_key)
				{
					gs.getCurrent_map().getLevel()[club.y][club.x] = "k";
					club_on_key = false;
				}
				else
					gs.getCurrent_map().getLevel()[club.y][club.x] = "_";
				
				club.x = club.nx;
				club.y = club.ny;
				gs.getCurrent_map().getLevel()[club.y][club.x] = "*";
				break;
				
			case "k":
				gs.getCurrent_map().getLevel()[club.y][club.x] = "_";
				club.y = club.ny;
				club.x = club.nx;
				gs.getCurrent_map().getLevel()[club.y][club.x] = "$";
				club_on_key = true;
				break;
				
			default:
				break;
				
		}
	}
}
