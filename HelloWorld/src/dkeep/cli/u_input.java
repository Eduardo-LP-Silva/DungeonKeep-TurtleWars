package dkeep.cli;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;
import dkeep.logic.Hero;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Map;
import dkeep.logic.GameState;


public class u_input 
{
	public static void print_map(Map map)
	{
		for(int i = 0; i < map.level.length; i++)
		{
			for(int j = 0; j < map.level[i].length; j++)
				System.out.print(map.level[i][j]);
				
			System.out.print("\n");
		}
	}
	
	
	public static void main(String[] args) 
	{
		String move = "start";
		Scanner in = new Scanner(System.in);
		Hero hero = new Hero(1,1);
		Guard guard = new Guard(8,1, Guard.Guard_Type.Suspicious);
		Map map = new Map();
		print_map(map);
		GameState gs = new GameState(1);
		
		while(!move.equalsIgnoreCase("exit") && gs.level_no == 1)
		{
			move =  in.next();
			hero.hero_move(move);
			hero.action(map, gs, guard);
			
			if(map.level[hero.y][hero.x].equals("K"))
			{
				map.lever = true;
				map.level[Map.door1_1.y][Map.door1_1.x] = "S";
				map.level[Map.door1_2.y][Map.door1_2.x] = "S";
				map.level[Map.door1_3.y][Map.door1_3.x] = "S";
				map.level[Map.door1_4.y][Map.door1_4.x] = "S";
				map.level[Map.door1_5.y][Map.door1_5.x] = "S";
				map.level[Map.door1_6.y][Map.door1_6.x] = "S";
				map.level[Map.door1_7.y][Map.door1_7.x] = "S";
			}
				
			map.level[hero.y][hero.x] = "H";
			
			if(guard.asleep)
				map.level[guard.y][guard.x] = "g";
			else
				map.level[guard.y][guard.x] = "G";
			
			print_map(map);
			
			if(gs.game_over(map.level, "G", hero))
			{
				System.out.print("Game Over\n");
				in.close();
				return; 
			}
		}
		
		if(gs.level_no == 2)
			map.next_level();
		else
		{
			System.out.print("Error in transition from level 1 to level 2");
			in.close();
			return;
		}
		
		print_map(map);
		
		Random rand = new Random();
		Ogre ogre = new Ogre(4, 1);
		
		hero.x = 1;
		hero.y = 7;
		hero.ny = 7;
		hero.nx = 1;
		
		while(!move.equalsIgnoreCase("exit") && gs.level_no == 2)
		{
			move = in.next();
			hero.hero_move(move);
			hero.action(map, gs);
			
			ogre.move( rand.nextInt(4) + 1);
			
			ogre.swing_club(rand.nextInt(4) + 1);
			ogre.smash(map);
			ogre.action(map);
			
			if(map.key)
				map.level[hero.y][hero.x] = "K";
			else
				map.level[hero.y][hero.x] = "H";
		
			print_map(map);
			
			if(gs.game_over(map.level, "O", hero) || gs.game_over(map.level, "*", hero) 
					|| gs.game_over(map.level, "$", hero))
			{
				System.out.print("Game Over\n");
				in.close();
				return;
			}
			
			if(gs.victory)
			{
				System.out.print("Victory!\n");
				in.close();
				return;
			}
		}
		
		in.close();
	}

}

