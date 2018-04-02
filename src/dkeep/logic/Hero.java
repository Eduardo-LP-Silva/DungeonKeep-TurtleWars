package dkeep.logic;
import dkeep.logic.Map;
import dkeep.logic.GameState;


public class Hero extends Character 
{
	private boolean armed;
	
	/* 
	 * Constructor of the class.
	 * 
	 * @param x The x coordinate of the new hero.
	 * @param y The y coordinate of the new hero.
	 * 
	 */
	public Hero(int x, int y)
	{
		super(x,y);
		armed = false;
	}
	
	/*
	 * Returns true if the the hero is armed.
	 * 
	 * @return The value of variable armed.
	 * 
	 */	
	public boolean isArmed() 
	{
		return armed;
	}

	
	/*
	 * Modifies the value of the variable armed.
	 * 
	 * @param armed New value for the armed variable.
	 * 
	 */
	public void setArmed(boolean armed) 
	{
		this.armed = armed;
	}

	
	/*
	 * Sets the value of the (possible) next X and Y coordinates.
	 * 
	 * @param move The string correspondent to the side which the hero should move.
	 * @param gs The current gamestate.
	 */
	public void heroMove(String move, GameState gs)
	{
		if(gs.isGame_over() || gs.isVictory())
			return;
		
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
	
	/*
	 * Define what will the hero do according to level and coordinates.
	 * 
	 * @param gs The current gamestate.
	 */
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
	
	/*
	 * Define what will the hero do if playing the first level.
	 * 
	 * @param gs The current gamestate.
	 */
	public void actionLevel1(GameState gs)
	{
		switch(gs.getCurrent_map()[ny][nx])
		{
		 case "X":
			 break;
			 
		 case "I":
			 break;
				 
		 case "_":
				 
			 if(gs.isLever())
			 {
				gs.getCurrent_map()[y][x] = "k";
				gs.setLever(false);
			}
			else
				gs.getCurrent_map()[y][x] = "_";
			 
			 x = nx;
			 y = ny;
			 
			 if(gs.getLevel_no() == 1)
				 gs.getGuard().guard_move(gs);
			
			 break;
			 
		 case "k":
			gs.setLever(true);
			gs.getCurrent_map()[y][x] = "_";
			x = nx;
			y = ny;
			
			if(gs.getLevel_no() == 1)
			{
				gs.getCurrent_map()[Map.getDoor1_1().y][Map.getDoor1_1().x] = "S";
				gs.getCurrent_map()[Map.getDoor1_2().y][Map.getDoor1_2().x] = "S";
				gs.getCurrent_map()[Map.getDoor1_3().y][Map.getDoor1_3().x] = "S";
				gs.getCurrent_map()[Map.getDoor1_4().y][Map.getDoor1_4().x] = "S";
				gs.getCurrent_map()[Map.getDoor1_5().y][Map.getDoor1_5().x] = "S";
				gs.getCurrent_map()[Map.getDoor1_6().y][Map.getDoor1_6().x] = "S";
				gs.getCurrent_map()[Map.getDoor1_7().y][Map.getDoor1_7().x] = "S";
				gs.getGuard().guard_move(gs);
			}
			
			if(gs.getLevel_no() == 0)
			{
				gs.getCurrent_map()[Map.getDoor_t1().y][Map.getDoor_t1().x] = "S";
				gs.getCurrent_map()[Map.getDoor_t2().y][Map.getDoor_t2().x] = "S";
			}
			
			break;
			 
		 case "S":
			 gs.setLevel_no(2);
			break;
			 
		 default:
			break;		 
		}
		
		
	}
	
	/*
	 * Define what will the hero do if playing the second level.
	 * 
	 * @param gs The current gamestate.
	 */
	public void actionLevel2(GameState gs)
	{
		switch(gs.getCurrent_map()[ny][nx])
		{
			case "X":
				break;
				
			case "I":
				if(gs.isKey())
					{
						gs.getCurrent_map()[ny][nx] = "S";
						gs.moveOgres();
					}
					
				break;
				
			case "S":
				
				if(gs.isKey())
					gs.setVictory(true);
					
				break;
				
			case "k":
				gs.getCurrent_map()[y][x] = "_";
				y = ny;
				x = nx;
				gs.setKey(true);
				gs.moveOgres();
				break;
				
			case "_":
				gs.getCurrent_map()[y][x] = "_";
				y = ny;
				x = nx;
				gs.moveOgres();
				break;
				
			default:
				break;
			}
	}
	
}
