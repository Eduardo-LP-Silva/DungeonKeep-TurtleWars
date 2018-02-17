import java.util.Scanner;
import java.lang.String;
import java.util.Random;

public class P1 
{
	public static int g_y, g_x, hy, hx, ny, nx;
	
	public static void print_map(String map[][])
	{
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
				System.out.print(map[i][j]);
				
			System.out.print("\n");
		}
	}
	
	public static boolean game_over(String map[][], String enemy)
	{
		if((map[hy][hx + 1] == enemy) || (map[hy][hx - 1] == enemy)
				|| (map[hy - 1][hx] == enemy) || (map[hy + 1][hx] == enemy))
		{
			map[hy][hx] = "_";
			return true;
		}
		else
			return false;
	}
	
	public static void hero_move(String move)
	{
		switch(move)
		{
			case "w":
				ny = hy - 1;
				nx = hx;
				break;
				
			case "s":
				ny = hy + 1;
				nx = hx;
				break;
				
			case "a":
				ny = hy;
				nx = hx - 1;
				break;
				
			case "d":
				ny = hy;
				nx = hx + 1;
				break;
		}
	}
	
	public static void guard_move(String map[][])
	{
		 if((g_y == 1) && (g_x == 8))
		 {
			 map[g_y][g_x] = "_";
			 g_x -= 1;
		 }
		 else
			 if(map[g_y][g_x - 1] == "X")
			 {
				 map[g_y][g_x] = "_";
				 g_y += 1;
			 }
			 else
				 if((map[g_y][g_x - 1] == "_") && (g_y == 5) && (map[g_y][g_x + 1] != "X"))
				 {
					 map[g_y][g_x] = "_";
					 g_x -= 1;
				 }
				 else
					 if(((map[g_y][g_x - 1] == "I") || (map[g_y][g_x - 1] == "S"))
							 && (map[g_y + 1][g_x] == "_"))
					 {
						 map[g_y][g_x] = "_";
						 g_y += 1;
					 }
					 else
						 if(map[g_y][g_x + 1] == "_")
						 {
							 map[g_y][g_x] = "_";
							 g_x += 1;
						 }
						 else
						 {
							 map[g_y][g_x] = "_";
							 g_y -= 1;
						 }
	}
	
	public static void main(String[] args) 
	{
		int d1_x = 4, d1_y = 1, d2_x = 2, d2_y = 3, d3_x = 4, d3_y = 3, d4_x = 0, d4_y = 5, 
				d5_x = 0, d5_y = 6, d6_x = 2, d6_y = 8, d7_x = 4, d7_y = 8;
		boolean lever = false, next_level = false;
		String move = "start";
		Scanner in = new Scanner (System.in);
		
		g_y =1;
		g_x = 8;
		hx =1;
		hy = 1;
		nx = 1;
		ny = 1;
		
		String map[][] = { 
				{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}, 
				{"X", "H", "_", "_", "I", "_", "X", "_", "G", "X"},
				{"X", "X", "X", "_", "X", "X", "X", "_", "_", "X"},
				{"X","_","I","_","I","_","X","_","_","X"},
				{"X", "X", "X", "_", "X", "X", "X", "_", "_", "X"},
				{"I", "_", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"I", "_", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "X", "X", "_", "X", "X", "X", "X", "_", "X"},
				{"X","_","I","_","I","_","X","K","_","X"},
				{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}};
		
		print_map(map);
		
		while(!move.equalsIgnoreCase("exit") && !next_level)
		{
			move =  in.next();
			
			hero_move(move);
			
			switch(map[ny][nx])
			{
			 case "X":
				 break;
				 
			 case "I":
				 break;
				 
			 case "_":
				 
				 if(lever)
				 {
					map[hy][hx] = "K";
					lever = false;
				}
				else
					map[hy][hx] = "_";
				 
				 hx = nx;
				 hy = ny;
				 guard_move(map);
				 break;
				 
			 case "K":
				lever = true;
				map[hy][hx] = "_";
				hx = nx;
				hy = ny;
				guard_move(map);
				break;
				 
			 case "S":
				next_level = true;
				break;
				 
			default:
				break;
					 
			}
				
			if(map[hy][hx].equals("K"))
			{
				lever = true;
				map[d1_y][d1_x] = "S";
				map[d2_y][d2_x] = "S";
				map[d3_y][d3_x] = "S";
				map[d4_y][d4_x] = "S";
				map[d5_y][d5_x] = "S";
				map[d6_y][d6_x] = "S";
				map[d7_y][d7_x] = "S";
			}
				
			map[hy][hx] = "H";
			map[g_y][g_x] = "G";
			
			print_map(map);
			
			if(game_over(map, "G"))
			{
				System.out.print("Game Over\n");
				in.close();
				return;
			}
		}
		
		String lair[][] = {
				{"X", "X", "X", "X", "X", "X", "X", "X", "X"},
				{"I", "_", "_", "_", "O", "_", "_", "k", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "_", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "H", "_", "_", "_", "_", "_", "_", "X"},
				{"X", "X", "X", "X", "X", "X", "X", "X", "X"}};
		
		print_map(lair);
		
		int d8_x = 0, d8_y = 1, on = 0, o_x = 4, o_y = 1, no_x = 4, no_y = 1, cn = 0, c_x = 5, 
				c_y = 1, cn_x = 5, cn_y = 1;
		Random rand = new Random();
		boolean key = false, o_key = false, c_key = false;
		
		hx = 1;
		hy = 7;
		ny = 7;
		nx = 1;
		
		while(!move.equalsIgnoreCase("exit"))
		{
			move = in.next();
			
			hero_move(move);
			
			switch(lair[ny][nx])
			{
				case "X":
					break;
					
				case "I":
					
					if(key)
						lair[d8_y][d8_x] = "S";
						
					
						
					break;
					
				case "S":
					
					if(key)
					{
						System.out.print("Victory!\n");
						in.close();
						return;
					}
					
					break;
					
				case "k":
					lair[hy][hx] = "_";
					hy = ny;
					hx = nx;
					key = true;
					break;
					
				case "_":
					lair[hy][hx] = "_";
					hy = ny;
					hx = nx;
					break;
					
				default:
					break;
 			}
			
			on = rand.nextInt(4) + 1;
			
			switch(on)
			{
				case 1:
					no_y = o_y -1;
					break;
					
				case 2:
					no_x = o_x + 1;
					break;
					
				case 3:
					no_y = o_y + 1;
					break;
					
				case 4:
					no_x = o_x - 1;
					break;
					
				default:
					break;	
			}
			
			cn = rand.nextInt(4) + 1;
			
			switch(cn)
			{
				case 1:
					cn_x = o_x;
					cn_y = o_y - 1;
					break;
					
				case 2:
					cn_x = o_x + 1;
					cn_y = o_y;
					break;
					
				case 3:
					cn_x = o_x;
					cn_y = o_y + 1;
					break;
					
				case 4:
					cn_x = o_x - 1;
					cn_y = o_y;
					break;
					
				default:
					break;
			}
			
			switch(lair[cn_y][cn_x])
			{		
				case "_":
					
					if(c_key)
					{
						lair[c_y][c_x] = "k";
						c_key = false;
					}
					else
						lair[c_y][c_x] = "_";
					
					c_x = cn_x;
					c_y = cn_y;
					lair[c_y][c_x] = "*";
					break;
					
				case "k":
					lair[c_y][c_x] = "_";
					c_y = cn_y;
					c_x = cn_x;
					lair[c_y][c_x] = "$";
					c_key = true;
					break;
					
				default:
					break;
					
			}
			
			switch(lair[no_y][no_x])
			{
				case "X":
					break;
					
				case "I":
					break;
					
				case "_":
					
					if(o_key)
					{
						lair[o_y][o_x] = "k";
						o_key = false;	
					}
					else
						lair[o_y][o_x] = "_";
					
					o_x = no_x;
					o_y = no_y;
					lair[o_y][o_x] = "O";
					break;
					
				case "k":
					lair[o_y][o_x] = "_";
					o_x = no_x;
					o_y = no_y;
					lair[o_y][o_x] = "$";
					o_key = true;
					
				case "S":
					break;
					
				default:
					break;
			}
			
			if(key)
				lair[hy][hx] = "K";
			else
				lair[hy][hx] = "H";
			
			
			print_map(lair);
			
			
			if(game_over(lair, "O") || game_over(lair, "*") || game_over(lair, "$"))
			{
				System.out.print("Game Over\n");
				in.close();
				return;
			}
			
		}
		
		in.close();
	}

}
