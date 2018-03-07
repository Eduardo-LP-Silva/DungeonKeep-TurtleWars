package dkeep.logic;
import java.util.Random;

public class Guard extends Character
{
	public enum Guard_Type {Rookie, Drunken, Suspicious};
	private String movement;
	private Guard_Type type;
	private boolean asleep;
	private int turns_asleep;
	
	public Guard(int x, int y, Guard_Type t)
	{
		super(x,y);
		
		turns_asleep = 0;
		asleep = false;
		movement = "forwards";
		type = t;
	}
	
	public boolean isAsleep() 
	{
		return asleep;
	}

	
	public void setType(Guard_Type type) 
	{
		this.type = type;
	}

	
	public String getMovement() 
	{
		return movement;
	}

	public void guard_move_forward(GameState gs)
	{
		 if((y == 1) && (x == 8))
		 {
			 gs.getCurrent_map().getLevel()[y][x] = "_";
			 x -= 1;
		 }
		 else
			 if(gs.getCurrent_map().getLevel()[y][x - 1] == "X")
			 {
				 gs.getCurrent_map().getLevel()[y][x] = "_";
				 y += 1;
			 }
			 else
				 if((gs.getCurrent_map().getLevel()[y][x - 1] == "_") 
						 && (y == 5) 
						 && (gs.getCurrent_map().getLevel()[y][x + 1] != "X"))
				 {
					 gs.getCurrent_map().getLevel()[y][x] = "_";
					 x -= 1;
				 }
				 else
					 if(((gs.getCurrent_map().getLevel()[y][x - 1] == "I") 
							 || (gs.getCurrent_map().getLevel()[y][x - 1] == "S"))
							 && (gs.getCurrent_map().getLevel()[y + 1][x] == "_"))
					 {
						 gs.getCurrent_map().getLevel()[y][x] = "_";
						 y += 1;
					 }
					 else
						 if(gs.getCurrent_map().getLevel()[y][x + 1] == "_")
						 {
							 gs.getCurrent_map().getLevel()[y][x] = "_";
							 x += 1;
						 }
						 else
						 {
							 gs.getCurrent_map().getLevel()[y][x] = "_";
							 y -= 1;
						 }
	}
	
	public void guard_move_backwards(GameState gs)
	{
		if(gs.getCurrent_map().getLevel()[y][x + 1] == "X" && y < 6)
		{
			gs.getCurrent_map().getLevel()[y][x] = "_";
			y++;
		}
		else
			if(y == 6 && gs.getCurrent_map().getLevel()[y][x - 1] == "_")
			{
				gs.getCurrent_map().getLevel()[y][x] = "_";
				x--;
			}
			else
				if(y == 6 && (gs.getCurrent_map().getLevel()[y][x - 1] == "I" 
				|| gs.getCurrent_map().getLevel()[y][x - 1] == "S"))
				{
					gs.getCurrent_map().getLevel()[y][x] = "_";
					y--;
				}
				else
					if(gs.getCurrent_map().getLevel()[y][x + 2] == "_")
					{
						gs.getCurrent_map().getLevel()[y][x] = "_";
						x++;
					}
					else
						if(gs.getCurrent_map().getLevel()[y - 1][x] == "_")
						{
							gs.getCurrent_map().getLevel()[y][x] = "_";
							y--;
						}
						else
						{
							gs.getCurrent_map().getLevel()[y][x] = "_";
							x++;
						}
			
	}
	
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
					 
					 action = rand.nextInt(2) + 1;
					 
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
					 action = rand.nextInt(2) + 1;
					 
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
