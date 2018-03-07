package dkeep.logic;
import dkeep.logic.Map;
import dkeep.logic.GameState;


public class Hero extends Character 
{
	private boolean armed;
	
	public Hero(int x, int y)
	{
		super(x,y);
		armed = false;
	}
	
	public boolean isArmed() 
	{
		return armed;
	}

	public void setArmed(boolean armed) 
	{
		this.armed = armed;
	}

	public void heroMove(String move, GameState gs)
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
		
		action(gs);
	}
	
	public void action(GameState gs)
	{
		switch(gs.getLevel_no())
		{
			case 0:
				actionLevel1(gs);
				break;
				
			case 1:
				actionLevel1(gs);
				break;
				
			case 2:
				actionLevel2(gs);
				break;
				
			default:
				break;
		}
		
	}
	
	
	public void actionLevel1(GameState gs)
	{
		switch(gs.getCurrent_map().getLevel()[ny][nx])
		{
		 case "X":
			 break;
			 
		 case "I":
			 break;
				 
		 case "_":
				 
			 if(gs.getCurrent_map().isLever())
			 {
				gs.getCurrent_map().getLevel()[y][x] = "k";
				gs.getCurrent_map().setLever(false);
			}
			else
				gs.getCurrent_map().getLevel()[y][x] = "_";
			 
			 x = nx;
			 y = ny;
			 
			 if(gs.getLevel_no() == 1)
				 gs.getGuard().guard_move(gs);
			
			 break;
			 
		 case "k":
			gs.getCurrent_map().setLever(true);
			gs.getCurrent_map().getLevel()[y][x] = "_";
			x = nx;
			y = ny;
			gs.getCurrent_map().setLever(true);
			
			if(gs.getLevel_no() == 1)
			{
				gs.getCurrent_map().getLevel()[Map.door1_1.y][Map.door1_1.x] = "S";
				gs.getCurrent_map().getLevel()[Map.door1_2.y][Map.door1_2.x] = "S";
				gs.getCurrent_map().getLevel()[Map.door1_3.y][Map.door1_3.x] = "S";
				gs.getCurrent_map().getLevel()[Map.door1_4.y][Map.door1_4.x] = "S";
				gs.getCurrent_map().getLevel()[Map.door1_5.y][Map.door1_5.x] = "S";
				gs.getCurrent_map().getLevel()[Map.door1_6.y][Map.door1_6.x] = "S";
				gs.getCurrent_map().getLevel()[Map.door1_7.y][Map.door1_7.x] = "S";
				gs.getGuard().guard_move(gs);
			}
			
			if(gs.getLevel_no() == 0)
			{
				gs.getCurrent_map().getLevel()[Map.door_t1.y][Map.door_t1.x] = "S";
				gs.getCurrent_map().getLevel()[Map.door_t2.y][Map.door_t2.x] = "S";
			}
			
			break;
			 
		 case "S":
			 gs.setLevel_no(2);
			break;
			 
		 default:
			break;		 
		}
	}
	
	public void actionLevel2(GameState gs)
	{
		switch(gs.getCurrent_map().getLevel()[ny][nx])
		{
			case "X":
				break;
				
			case "I":
				if(gs.getCurrent_map().isKey())
					gs.getCurrent_map().getLevel()[Map.door2_1.y][Map.door2_1.x] = "S";
					
				break;
				
			case "S":
				
				if(gs.getCurrent_map().isKey())
					gs.setVictory(true);
					
				break;
				
			case "k":
				gs.getCurrent_map().getLevel()[y][x] = "_";
				y = ny;
				x = nx;
				gs.getCurrent_map().setKey(true);
				break;
				
			case "_":
				gs.getCurrent_map().getLevel()[y][x] = "_";
				y = ny;
				x = nx;
				break;
				
			default:
				break;
			}
	}
	
}
