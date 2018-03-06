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
	
	public void action(Map map)
	{
		switch(map.level[ny][nx])
		{
			case "X":
				map.level[y][x] = "O";
				break;
				
			case "I":
				map.level[y][x] = "O";
				break;
				
			case "_":
				
				if(on_top_of_key)
				{
					map.level[y][x] = "k";
					on_top_of_key = false;	
				}
				else
					map.level[y][x] = "_";
				
				x = nx;
				y = ny;
				map.level[y][x] = "O";
				break;
				
			case "k":
				map.level[y][x] = "_";
				x = nx;
				y = ny;
				map.level[y][x] = "$";
				on_top_of_key = true;
				
			case "S":
				map.level[y][x] = "O";
				break;
				
			case "O":
				x = nx;
				y = ny;
				map.level[y][x] = "O";
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
	
	public void smash(Map map)
	{
		switch(map.level[club.ny][club.nx])
		{		
			case "_":
				
				if(club_on_key)
				{
					map.level[club.y][club.x] = "k";
					club_on_key = false;
				}
				else
					map.level[club.y][club.x] = "_";
				
				club.x = club.nx;
				club.y = club.ny;
				map.level[club.y][club.x] = "*";
				break;
				
			case "k":
				map.level[club.y][club.x] = "_";
				club.y = club.ny;
				club.x = club.nx;
				map.level[club.y][club.x] = "$";
				club_on_key = true;
				break;
				
			default:
				break;
				
		}
	}
}
