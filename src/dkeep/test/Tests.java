package dkeep.test;
import static org.junit.Assert.*;

import java.util.ArrayList;

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
		assertEquals(gs.getCurrent_map()[1][1], "_");
		assertEquals(gs.getCurrent_map()[2][1], "H");
		
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
		gs.test_game_over();
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
		
		assertTrue(gs.isLever());
		assertEquals(gs.getCurrent_map()[2][0], "S");
		assertEquals(gs.getCurrent_map()[3][0], "S");
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
		ArrayList<Ogre> list = new ArrayList<Ogre>();
		list.add(new Ogre(1,4));
		gs.setOgres(list);
		gs.setTest(true);
		gs.getHero().setArmed(false);
		
		for(int i = 0; i < 2; i++)
			gs.getHero().heroMove("w", gs);
		
		gs.updateMap();
			
		gs.test_game_over();
		assertTrue(gs.isGame_over());
	}
	
	@Test
	public void stunOgre()
	{
		GameState gs = new GameState(2);
		ArrayList<Ogre> list = new ArrayList<Ogre>();
		list.add(new Ogre(1,4));
		list.add(new Ogre(4,4));
		gs.setOgres(list);
		gs.setTest(true);
		
		for(int i = 0; i < 2; i++)
			{
				gs.getHero().heroMove("w", gs);
				gs.stunOgres();
			}
		
		gs.updateMap();
		
		assertEquals(gs.getCurrent_map()[gs.getHero().getY() - 1][gs.getHero().getX()], "8");
		assertEquals(gs.getOgres().get(0).getTurns_stunned(), 1);
		
		gs.getHero().heroMove("d", gs);
		gs.stunOgres();
		gs.getHero().heroMove("w", gs);
		gs.stunOgres();
		gs.getHero().heroMove("d", gs);
		gs.stunOgres();
		gs.updateMap();
		
		assertEquals(gs.getCurrent_map()[gs.getHero().getY()][gs.getHero().getX() + 1], "8");
		assertEquals(gs.getOgres().get(1).getTurns_stunned(), 1);
		
		gs.getHero().heroMove("w", gs);
		gs.stunOgres();
		
		for(int j = 0; j < 2; j++)
		{
			gs.getHero().heroMove("a", gs);
			gs.stunOgres();
		}
		
		gs.updateMap();
		
		assertEquals(gs.getCurrent_map()[gs.getHero().getY() + 1][gs.getHero().getX()], "8");
	}
	
	@Test
	public void ogreOnKey()
	{
		GameState gs = new GameState(2);
		ArrayList<Ogre> list = new ArrayList<Ogre>();
		list.add(new Ogre(6,1));
		gs.setOgres(list);
		gs.setTest(true);
		gs.getOgres().get(0).setNy(gs.getOgres().get(0).getY());
		gs.getOgres().get(0).setNx(gs.getOgres().get(0).getX() + 1);
		gs.getOgres().get(0).action(gs);
		gs.updateMap();
		
		assertTrue(gs.getOgres().get(0).isOn_top_of_key());
		assertEquals(gs.getCurrent_map()[gs.getOgres().get(0).getY()][gs.getOgres().get(0).getX()],
				"$");
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
		
		assertTrue(gs.isKey());
		gs.getHero().heroMove("a", gs);
		gs.updateMap();
		assertEquals(gs.getCurrent_map()
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
		assertEquals(gs.getCurrent_map()
				[gs.getHero().getY()][gs.getHero().getX()], "A");
		assertFalse(gs.isVictory());
	}
	
	@Test
	public void OpenDoorLair()
	{
		GameState gs = new GameState(2);
		
		gs.setKey(true);
		
		gs.getHero().heroMove("d", gs);
		
		for(int i = 0; i < 6; i++)
			gs.getHero().heroMove("w", gs);
	
		for(int j = 0; j < 2; j++)
			gs.getHero().heroMove("a", gs);
		
		gs.updateMap();
		
		assertEquals(gs.getCurrent_map()
				[gs.getHero().getNy()][gs.getHero().getNx()], "S");
	}
	
	@Test
	public void exitLair()
	{
		GameState gs = new GameState(2);
		
		gs.setKey(true);
		
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
	public void testBackwardsGuardPath()
	{
		GameState gs = new GameState(1);
		gs.getGuard().setType("Suspicious");
		
		for(int i = 0; i < 13; i++)
		{
			gs.getGuard().guard_move_backwards(gs);
			gs.updateMap();
		}
		
		assertEquals(gs.getCurrent_map()[5][1], "G");
		assertEquals(gs.getGuard().getX(), 1);
		assertEquals(gs.getGuard().getY(), 5);
		
		for(int j = 0; j < 11; j++)
		{
			gs.getGuard().guard_move_backwards(gs);
			gs.updateMap();
		}
		
		
		assertEquals(gs.getCurrent_map()[1][8], "G");
		assertEquals(gs.getGuard().getX(), 8);
		assertEquals(gs.getGuard().getY(), 1);
		
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
		
		assertEquals(gs.getCurrent_map()[3][8], "G");
	}
	
	@Test
	public void testOgreNumber()
	{
		GameState gs = new GameState(2);
		
		gs.addOgres(7);
		assertEquals(7, gs.getOgres().size());
		
	}
	
	@Test
	public void testAutomaticHeroPlacement()
	{
		GameState gs = new GameState(2);
		
		assertEquals(gs.getHero().getX(), 1);
		assertEquals(gs.getHero().getY(), 7);
	}
	
	@Test
	public void ogresMove()
	{
		GameState gs = new GameState(2);
		ArrayList<Ogre> list = new ArrayList<Ogre>();
		Ogre o1_1 = new Ogre(3,3);
		Ogre o1_2 = new Ogre(5,5);
		Ogre o1_3 = new Ogre(2,2);
		Ogre o2_1 = new Ogre(3,3);
		Ogre o2_2 = new Ogre(5,5);
		Ogre o2_3 = new Ogre(2,2);
		
		list.add(o1_1);
		list.add(o1_2);
		list.add(o1_3);
		
		gs.setOgres(list);
		gs.setHero(new Hero(6,1));
		gs.updateMap();
		gs.getHero().heroMove("d", gs);
		gs.updateMap();
		
		assertTrue(o1_1 != o2_1 || o1_2 != o2_2 || o1_3 != o2_3);
	}
	
	@Test
	public void ogreRecoverFromStun()
	{
		GameState gs = new GameState(2);
		gs.setHero(new Hero(2,2));
		ArrayList<Ogre> l = new ArrayList<Ogre>();
		l.add(new Ogre(3,2));
		gs.setOgres(l);
		gs.updateMap();
		gs.stunOgres();
		
		assertEquals(gs.getOgres().get(0).getTurns_stunned(), 1);
		
		for(int i = 0; i < 3; i++)
		{
			gs.getHero().heroMove("s", gs);
			gs.updateMap();
		}
		
		assertEquals(gs.getOgres().get(0).getTurns_stunned(), 0);
	}
	
	@Test
	public void ogreSmashKey()
	{
		GameState gs = new GameState(2);
		ArrayList<Ogre> l = new ArrayList<Ogre>();
		l.add(new Ogre(6,1));
		gs.setOgres(l);
		gs.updateMap();
		
		gs.getOgres().get(0).getClub().setNx(gs.getOgres().get(0).getX() + 1);
		gs.getOgres().get(0).getClub().setNy(gs.getOgres().get(0).getY());
		gs.getOgres().get(0).smash(gs);
		gs.updateMap();
		
		assertTrue(gs.getOgres().get(0).isClub_on_key());
		assertEquals(gs.getCurrent_map()
				[gs.getOgres().get(0).getClub().getY()][gs.getOgres().get(0).getClub().getX()],
				"$");
	}
	
}
