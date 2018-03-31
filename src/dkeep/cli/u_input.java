package dkeep.cli;
import java.util.Scanner;
import java.lang.String;
import dkeep.logic.GameState;
import dkeep.gui.*;


public class u_input 
{
	protected StartUpWindow window;
	
	public static void main(String[] args) 
	{
		String move = "start";
		Scanner in = new Scanner(System.in);
		GameState gs = new GameState(1);
		gs.print_map();
		
		
		while(!move.equalsIgnoreCase("exit") && gs.getLevel_no() == 1)
		{
			move =  in.next();
			gs.getHero().heroMove(move, gs);
			gs.updateMap();
			gs.print_map();
			
			if(gs.test_game_over())
			{
				in.close();
				System.out.println("Game Over\n");
				return; 
			}
		}
		
		if(move.equalsIgnoreCase("exit"))
		{
			System.out.println("Exit");
			in.close();
			return;
		}
		
		gs.print_map();
		gs.addOgres(2);
		
		while(!move.equalsIgnoreCase("exit") && gs.getLevel_no() == 2)
		{
			move = in.next();
			gs.getHero().heroMove(move, gs);
			gs.updateMap();
			gs.print_map();
					
			if(gs.test_game_over())
			{
				in.close();
				System.out.println("Game Over\n");
				return;
			}
			
			if(gs.isVictory())
			{
				System.out.print("Victory!\n");
				in.close();
				return;
			}
		}
		
		in.close();
	}

}

