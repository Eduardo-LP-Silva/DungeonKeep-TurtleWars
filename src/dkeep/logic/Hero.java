package dkeep.logic;
import dkeep.logic.Map;
import dkeep.logic.Guard;
import dkeep.logic.GameState;


public class Hero extends Character 
{
	public boolean armed;
	
	public Hero(int x, int y)
	{
		super(x,y);
		armed = false;
	}
	
	public void heroMove(String move)
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
			 
			 if(gs.level_no == 1)
					guard.guard_move(map);
			 
			 break;
			 
		 case "k":
			map.lever = true;
			map.level[y][x] = "_";
			x = nx;
			y = ny;
			map.lever = true;
			
			if(gs.level_no == 1)
			{
				map.level[Map.door1_1.y][Map.door1_1.x] = "S";
				map.level[Map.door1_2.y][Map.door1_2.x] = "S";
				map.level[Map.door1_3.y][Map.door1_3.x] = "S";
				map.level[Map.door1_4.y][Map.door1_4.x] = "S";
				map.level[Map.door1_5.y][Map.door1_5.x] = "S";
				map.level[Map.door1_6.y][Map.door1_6.x] = "S";
				map.level[Map.door1_7.y][Map.door1_7.x] = "S";
				guard.guard_move(map);
			}
			
			if(gs.level_no == 3)
			{
				map.level[Map.door_t1.y][Map.door_t1.x] = "S";
				map.level[Map.door_t2.y][Map.door_t2.x] = "S";
			}
			
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
