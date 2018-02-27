package dkeep.logic;
import dkeep.logic.Map;
import dkeep.logic.Guard;
import dkeep.logic.GameState;


public class Hero extends Character 
{
	public Hero(int x, int y)
	{
		super(x,y);
	}
	
	public void hero_move(String move)
	{
		switch(move)
		{
			case "w":
				ny = y - 1;
				nx = x;
				break;
				
			case "s":
				ny = y + 1;
				nx = x;
				break;
				
			case "a":
				ny = y;
				nx = x - 1;
				break;
				
			case "d":
				ny = y;
				nx = x + 1;
				break;
		}
	}
	
	public void action(Map map, GameState gs, Guard guard)
	{
		switch(map.level[ny][nx])
		{
		 case "X":
			 break;
			 
		 case "I":
			 break;
				 
		 case "_":
				 
			 if(map.lever)
			 {
				map.level[y][x] = "K";
				map.lever = false;
			}
			else
				map.level[y][x] = "_";
			 
			 x = nx;
			 y = ny;
			 guard.guard_move(map);
			 break;
			 
		 case "K":
			map.lever = true;
			map.level[y][x] = "_";
			x = nx;
			y = ny;
			guard.guard_move(map);
			break;
			 
		 case "S":
			gs.level_no = 2;
			break;
			 
		 default:
			break;		 
		}
	}
	
	public void action(Map map, GameState gs)
	{
		switch(map.level[ny][nx])
		{
			case "X":
				break;
				
			case "I":
				if(map.key)
					map.level[Map.door2_1.y][Map.door2_1.x] = "S";
					
				break;
				
			case "S":
				
				if(map.key)
					gs.victory = true;
					
				break;
				
			case "k":
				map.level[y][x] = "_";
				y = ny;
				x = nx;
				map.key = true;
				break;
				
			case "_":
				map.level[y][x] = "_";
				y = ny;
				x = nx;
				break;
				
			default:
				break;
			}
	}
}
