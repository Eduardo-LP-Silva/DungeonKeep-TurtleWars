package dkeep.logic;
import java.util.Random;

public class Guard extends Character
{
	public enum Guard_Type {Rookie, Drunken, Suspicious};
	private String movement;
	private Guard_Type type;
	private boolean asleep;
	private int turns_asleep;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param x The x coordinate of the new guard.
	 * @param y The y coordinate of the new guard.
	 * @param t The type of the new guard.
	 * 
	 */
	public Guard(int x, int y, Guard_Type t)
	{
		super(x,y);
		
		turns_asleep = 0;
		asleep = false;
		movement = "forwards";
		type = t;
	}
	
	/**
	 * Modifies the movement of the guard.
	 * 
	 * @param movement New value for the guard's type of movement (forwards or backwards).
	 * 
	 */
	public void setMovement(String movement) 
	{
		this.movement = movement;
	}

	/**
	 * Returns true if the guard fell asleep.
	 * 
	 * @return The value of the asleep variable.
	 */
	public boolean isAsleep() 
	{
		return asleep;
	}

	/**
	 * Returns the number of turns the guard has been asleep for since the last time.
	 * 
	 * @return The value of of the turns_asleep variable.
	 */
	public int getTurns_asleep() 
	{
		return turns_asleep;
	}

	/**
	 * Modifies the number of turns the guard has been asleep for since the last time.
	 * 
	 * @param turns_asleep The new number of turns the guard has been asleep for since last time.
	 */
	public void setTurns_asleep(int turns_asleep) 
	{
		this.turns_asleep = turns_asleep;
	}
	
	/**
	 * Puts guard to sleep or wakes him up.
	 * 
	 * @param asleep The new value for the boolean variable asleep.
	 */
	public void setAsleep(boolean asleep) 
	{
		this.asleep = asleep;
	}
	
	/**
	 * Changes the personality of the guard.
	 * 
	 * @param tp The new personality in string format.
	 */
	public void setType(String tp) 
	{
		switch(tp)
		{
			case "Rookie":
				type = Guard.Guard_Type.Rookie;
				break;
				
			case "Drunken":
				type = Guard.Guard_Type.Drunken;
				break;
				
			case "Suspicious":
				type = Guard.Guard_Type.Suspicious;
				break;
				
			default:
				break;
		}
	}
	
	/**
	 * Returns the personality of the guard in string format.
	 * 
	 * @return The personality of the guard.
	 */
	public String getType()
	{
         switch(type)
         {
           case Rookie:
		        return "Rookie";
			case Drunken:
				return "Drunken";		
			case Suspicious:
				return "Suspicious";
			
			default:
				break;
         }
         
         return "";
	}

	
	/**
	 * Returns the guards type of movement (forwards or backwards).
	 * 
	 * @return The type of movement of the guard.
	 */
	public String getMovement() 
	{
		return movement;
	}

	/**
	 * Moves the guard forwards (counter-clock wise) through a predefined path.
	 * 
	 * @param gs The current gamestate.
	 */
	public void guard_move_forward(GameState gs)
	{
		 if((y == 1) && (x == 8))
		 {
			 gs.getCurrent_map()[y][x] = "_";
			 x -= 1;
		 }
		 else
			 if(gs.getCurrent_map()[y][x - 1].equals("X"))
			 {
				 gs.getCurrent_map()[y][x] = "_";
				 y += 1;
			 }
			 else
				 if((gs.getCurrent_map()[y][x - 1].equals("_"))
						 && (y == 5) 
						 && (!gs.getCurrent_map()[y][x + 1].equals("X")))
				 {
					 gs.getCurrent_map()[y][x] = "_";
					 x -= 1;
				 }
				 else
					 if(((gs.getCurrent_map()[y][x - 1].equals("I")) 
							 || (gs.getCurrent_map()[y][x - 1].equals("S")))
							 && (gs.getCurrent_map()[y + 1][x].equals("_")))
					 {
						 gs.getCurrent_map()[y][x] = "_";
						 y += 1;
					 }
					 else
						 if(gs.getCurrent_map()[y][x + 1].equals("_"))
						 {
							 gs.getCurrent_map()[y][x] = "_";
							 x += 1;
						 }
						 else
						 {
							 gs.getCurrent_map()[y][x] = "_";
							 y -= 1;
						 }
	}
	
	/**
	 * Moves the guard backwards (clock wise) through a predefined path.
	 * 
	 * @param gs The current gamestate.
	 */
	public void guard_move_backwards(GameState gs)
	{
		if(gs.getCurrent_map()[y][x + 1].equals("X") && y < 6)
		{
			gs.getCurrent_map()[y][x] = "_";
			y++;
		}
		else
			if(y == 6 && gs.getCurrent_map()[y][x - 1].equals("_"))
			{
				gs.getCurrent_map()[y][x] = "_";
				x--;
			}
			else
				if(y == 6 && (gs.getCurrent_map()[y][x - 1].equals("I") 
				|| gs.getCurrent_map()[y][x - 1].equals("S")))
				{
					gs.getCurrent_map()[y][x] = "_";
					y--;
				}
				else
					if(gs.getCurrent_map()[y][x + 2].equals("_"))
					{
						gs.getCurrent_map()[y][x] = "_";
						x++;
					}
					else
						if(gs.getCurrent_map()[y - 1][x].equals("_"))
						{
							gs.getCurrent_map()[y][x] = "_";
							y--;
						}
						else
						{
							gs.getCurrent_map()[y][x] = "_";
							x++;
						}
			
	}
	
	/**
	 * Decides the guard's actions depending on its personality.
	 * He can move, fall asleep or turn backwards.
	 * 
	 * @param gs The current gamestate.
	 */
	public void guard_move(GameState gs)
	{
		Random rand = new Random();
		int action;
		
		switch(type)
		{
		 case Rookie:
			 guard_move_forward(gs);
			 break;
			 
		 case Drunken:
			 
			 if(asleep && turns_asleep < 2)
				 turns_asleep++;
			 else
				 if(asleep && turns_asleep >= 2)
				 {
					 turns_asleep = 0;
					 asleep = false;
					 
					 action = rand.nextInt(4) + 1;
					 
					 if(action == 1)
						 {
						 	guard_move_forward(gs);
						 	movement = "forwards";
						 }
					 else
						 {
						 	guard_move_backwards(gs);
						 	movement = "backwards";
						 }
				 }
				 else
				 {
					 action = rand.nextInt(4) + 1;
					 
					 if(action == 1)
						 if(movement == "forwards")
							 guard_move_forward(gs);
						 else
							 guard_move_backwards(gs);
					 else
						 asleep = true;
				 }
			 
			 break;
			 
		 case Suspicious:
			 action = rand.nextInt(2) + 1;
			 
			 if(action == 1)
				 if(movement == "forwards")
					 guard_move_forward(gs);
				 else
					 guard_move_backwards(gs);
			 else
				 if(movement == "forwards")
					 guard_move_backwards(gs);
				 else
					 guard_move_forward(gs);
			 
			 break;
			 
			 default:
				 break;
		}
	}
}
