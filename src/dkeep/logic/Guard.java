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

	public void guard_move_forward(Map map)
	{
		 if((y == 1) && (x == 8))
		 {
			 map.level[y][x] = "_";
			 x -= 1;
		 }
		 else
			 if(map.level[y][x - 1] == "X")
			 {
				 map.level[y][x] = "_";
				 y += 1;
			 }
			 else
				 if((map.level[y][x - 1] == "_") && (y == 5) && (map.level[y][x + 1] != "X"))
				 {
					 map.level[y][x] = "_";
					 x -= 1;
				 }
				 else
					 if(((map.level[y][x - 1] == "I") || (map.level[y][x - 1] == "S"))
							 && (map.level[y + 1][x] == "_"))
					 {
						 map.level[y][x] = "_";
						 y += 1;
					 }
					 else
						 if(map.level[y][x + 1] == "_")
						 {
							 map.level[y][x] = "_";
							 x += 1;
						 }
						 else
						 {
							 map.level[y][x] = "_";
							 y -= 1;
						 }
	}
	
	public void guard_move_backwards(Map map)
	{
		if(map.level[y][x + 1] == "X" && y < 6)
		{
			map.level[y][x] = "_";
			y++;
		}
		else
			if(y == 6 && map.level[y][x - 1] == "_")
			{
				map.level[y][x] = "_";
				x--;
			}
			else
				if(y == 6 && (map.level[y][x - 1] == "I" || map.level[y][x - 1] == "S"))
				{
					map.level[y][x] = "_";
					y--;
				}
				else
					if(map.level[y][x + 2] == "_")
					{
						map.level[y][x] = "_";
						x++;
					}
					else
						if(map.level[y - 1][x] == "_")
						{
							map.level[y][x] = "_";
							y--;
						}
						else
						{
							map.level[y][x] = "_";
							x++;
						}
			
	}
	
	public void guard_move(Map map)
	{
		Random rand = new Random();
		int action;
		
		switch(type)
		{
		 case Rookie:
			 guard_move_forward(map);
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
						 	guard_move_forward(map);
						 	movement = "forwards";
						 }
					 else
						 {
						 	guard_move_backwards(map);
						 	movement = "backwards";
						 }
				 }
				 else
				 {
					 action = rand.nextInt(2) + 1;
					 
					 if(action == 1)
						 if(movement == "forwards")
							 guard_move_forward(map);
						 else
							 guard_move_backwards(map);
					 else
						 asleep = true;
				 }
			 
			 break;
			 
		 case Suspicious:
			 action = rand.nextInt(2) + 1;
			 
			 if(action == 1)
				 if(movement == "forwards")
					 guard_move_forward(map);
				 else
					 guard_move_backwards(map);
			 else
				 if(movement == "forwards")
					 guard_move_backwards(map);
				 else
					 guard_move_forward(map);
			 
			 break;
			 
			 default:
				 break;
		}
	}
}
