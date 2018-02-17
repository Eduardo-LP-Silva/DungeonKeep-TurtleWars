import java.util.Scanner;
import java.lang.String;


public class P1 
{

	public static boolean movement(String map[][], int nx, int ny)
	{
		switch(map[ny][nx])
		{
		 case "X":
			 return false;
			 
		 case "I":
			 return false;
			 
		 case "_":
			 return true;
			 
		 case "K":
			 return true;
			 
		 case "S":
			 return true;
			 
		default:
			return false;
				 
		}
		
	}
	
	public static void main(String[] args) 
	{
		int hx = 1, hy = 1, nx = 1, ny = 1;
		boolean lever = false;
		String move = "start";
		Scanner in = new Scanner (System.in);
		
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
			
			if (movement(map, nx, ny)) 
			{
				if(lever)
				{
					map[hy][hx] = "K";
					lever = false;
				}
				else
					map[hy][hx] = "_";
				
				hx = nx;
				hy = ny;
				
				if(map[hy][hx].equals("K"))
				{
					lever = true;
					map[1][4] = "S";
					map[3][2] = "S";
					map[3][4] = "S";
					map[5][0] = "S";
					map[6][0] = "S";
					map[8][2] = "S";
					map[8][4] = "S";
				}
				
				map[hy][hx] = "H";

			}
			
			for (int i = 0; i < map.length; i++) 
			{
				for (int j = 0; j < map[i].length; j++)
					System.out.print(map[i][j]);

				System.out.print("\n");
			}
		}
		
		in.close();
		
	}

}
