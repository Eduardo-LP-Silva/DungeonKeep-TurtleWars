import java.util.Scanner;
import java.lang.String;


public class P1 
{
	public static int g_y, g_x;
	
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
					 if(((map[g_y][g_x - 1] == "I") || (map[g_y][g_x] == "S"))
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
		int hx = 1, hy = 1, nx = 1, ny = 1, d1_x = 4, d1_y = 1, d2_x = 2, d2_y = 3, d3_x = 4, 
				d3_y = 3, d4_x = 0, d4_y = 5, d5_x = 0, d5_y = 6, d6_x = 2, d6_y = 8, d7_x = 4, 
				d7_y = 8;
		boolean lever = false, game_over = false;
		String move = "start";
		Scanner in = new Scanner (System.in);
		
		g_y =1;
		g_x = 8;
		
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
		
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
				System.out.print(map[i][j]);
				
			System.out.print("\n");
		}
		
		
		while(!move.equalsIgnoreCase("exit"))
		{
			move =  in.next();
			
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
				System.out.print("Victory!\n");
				in.close();
				return;
				 
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
			
			if((map[hy][hx + 1] == "G") || (map[hy][hx - 1] == "G")
					|| (map[hy - 1][hx] == "G") || (map[hy + 1][hx] == "G"))
			{
				map[hy][hx] = "_";
				game_over = true;
			}

			for (int i = 0; i < map.length; i++) 
			{
				for (int j = 0; j < map[i].length; j++)
					System.out.print(map[i][j]);

				System.out.print("\n");
			}
			
			if(game_over)
			{
				System.out.print("Game Over\n");
				in.close();
				return;
			}
		}
		
		in.close();
		
	}

}
