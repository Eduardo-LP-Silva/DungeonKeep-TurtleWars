package dkeep.test;
import static org.junit.Assert.*;
import org.junit.Test;
import dkeep.logic.*;

public class Tests 
{
	
	@Test
	public void testMoveHero()
	{
		GameState gs = new GameState(0);
		assertEquals(new Hero(1,1), gs.getHero());
		gs.getHero().heroMove("s", gs);
		gs.updateMap();
		assertEquals(new Hero(1,2), gs.getHero());
		assertEquals(gs.getCurrent_map().getLevel()[1][1], "_");
		assertEquals(gs.getCurrent_map().getLevel()[2][1], "H");
	}
	
	
	@Test
	public void testNotMoveHero()
	{
		GameState gs = new GameState(0);
		gs.getHero().heroMove("a", gs);
		assertEquals(new Hero(1,1), gs.getHero());
	}
	
	
	@Test
	public void heroCaughtByGuard()
	{
		GameState gs = new GameState(0);
		gs.getHero().heroMove("d", gs);
		gs.test_collision("G");
		assertTrue(gs.isGame_over());
	}
	
	
	@Test
	public void heroNoExit()
	{
		GameState gs = new GameState(0);
		gs.getHero().heroMove("s", gs);
		gs.getHero().heroMove("a", gs);
		assertFalse(gs.isVictory());
		assertEquals(new Hero(1,2), gs.getHero());
	}
	
	
	@Test
	public void doorsOpen()
	{
		GameState gs = new GameState(0);
		gs.getHero().heroMove("s", gs);
		gs.getHero().heroMove("s", gs);
		assertTrue(gs.getCurrent_map().isLever());
		assertEquals(gs.getCurrent_map().getLevel()[2][0], "S");
		assertEquals(gs.getCurrent_map().getLevel()[3][0], "S");
	}
	
	
	@Test
	public void levelProgression()
	{
		GameState gs = new GameState(0);
		gs.getHero().heroMove("s", gs);
		gs.getHero().heroMove("s", gs);
		gs.getHero().heroMove("a", gs);
		assertEquals(2, gs.getLevel_no());
	}
	
	
	@Test
	public void deathByOgre()
	{
		GameState gs = new GameState(2);
		gs.getHero().setArmed(false);

		for(int i = 0; i < 2; i++)
			gs.getHero().heroMove("w", gs);
			
		gs.test_collision("O");
		assertTrue(gs.isGame_over());
	}
	
	
	@Test
	public void pickKey()
	{
		GameState gs = new GameState(2);
		
		for(int i = 0; i < 4; i++)
			gs.getHero().heroMove("d", gs);
	
		
		for(int j = 0; j < 4; j++)
			gs.getHero().heroMove("w", gs);
		
		gs.getHero().heroMove("d", gs);
		gs.getHero().heroMove("d", gs);
		gs.getHero().heroMove("w", gs);
		gs.getHero().heroMove("w", gs);
		
		assertTrue(gs.getCurrent_map().isKey());
		gs.getHero().heroMove("a", gs);
		gs.updateMap();
		assertEquals(gs.getCurrent_map().getLevel()
				[gs.getHero().getY()][gs.getHero().getX()], "K");
	}
	
	@Test
	public void failedExitKeep()
	{
		GameState gs = new GameState(2);
		
		gs.getHero().heroMove("d", gs);
		
		for(int i = 0; i < 6; i++)
			gs.getHero().heroMove("w", gs);
	
		for(int j = 0; j < 3; j++)
			gs.getHero().heroMove("a", gs);
		
		gs.updateMap();
		assertEquals(gs.getCurrent_map().getLevel()
				[gs.getHero().getY()][gs.getHero().getX()], "A");
		assertFalse(gs.isVictory());
	}
	
	@Test
	public void OpenDoorLair()
	{
		GameState gs = new GameState(2);
		
		gs.getCurrent_map().setKey(true);
		
		gs.getHero().heroMove("d", gs);
		
		for(int i = 0; i < 6; i++)
			gs.getHero().heroMove("w", gs);
	
		for(int j = 0; j < 2; j++)
			gs.getHero().heroMove("a", gs);
		
		gs.updateMap();
		
		assertEquals(gs.getCurrent_map().getLevel()
				[Map.door2_1.getY()][Map.door2_1.getX()], "S");
	}
	
	@Test
	public void exitLair()
	{
		GameState gs = new GameState(2);
		
		gs.getCurrent_map().setKey(true);
		
		gs.getHero().heroMove("d", gs);
		
		for(int i = 0; i < 6; i++)
			gs.getHero().heroMove("w", gs);
	
		for(int j = 0; j < 3; j++)
			gs.getHero().heroMove("a", gs);
		
		assertTrue(gs.isVictory());
	}
	
	@Test(timeout = 1000)
	public void testRandomOgre()
	{
		boolean up = false, down = false, left = false, right = false;
		int ox, oy;
		GameState gs = new GameState(2);
		gs.addOgres(2);
		
		while(!up || !down || !left || !right)
		{
			ox = gs.getOgres().get(0).getX();
			oy = gs.getOgres().get(0).getY();
			gs.moveOgres();
			
			if(gs.getOgres().get(0).getY() == oy + 1)
				up = true;
			
			if(gs.getOgres().get(0).getY() == oy - 1)
				down = true;
			

			if(gs.getOgres().get(0).getX() == ox + 1)
				right = true;
			
			if(gs.getOgres().get(0).getX() == ox - 1)
				left = true;
					
		}
	}
	
	@Test(timeout = 1000)
	public void testRandomSleep()
	{
		GameState gs = new GameState(1);
		
		while(!gs.getGuard().isAsleep())
		{
			gs.getHero().heroMove("d", gs);
			gs.getHero().heroMove("a", gs);
		}
	}
	
	@Test(timeout = 1000)
	public void testRandomTurnBack()
	{
		GameState gs = new GameState(1);
		
		gs.getGuard().setType("Suspicious");
		
		while(gs.getGuard().getMovement().equals("forward"))
		{
			gs.getHero().heroMove("d", gs);
			gs.getHero().heroMove("a", gs);
		}
	}
	
	@Test
	public void testGuardPath()
	{
		GameState gs = new GameState(1);
		
		gs.getGuard().setType("Rookie");
		
		for(int i = 0; i < 11; i++)
		{
			gs.getHero().heroMove("d", gs);
			gs.getHero().heroMove("a", gs);
			gs.updateMap();
		}
		
		assertEquals(gs.getCurrent_map().getLevel()[3][8], "G");
	}
	
	@Test
	public void testOgreNumber()
	{
		GameState gs = new GameState(2);
		
		gs.addOgres(7);
		assertEquals(7, gs.getOgres().size());
		
	}
	
}
