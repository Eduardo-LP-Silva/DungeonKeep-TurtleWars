package dkeep.test;
import static org.junit.Assert.*;
import org.junit.Test;
import dkeep.logic.*;

public class Tests 
{
	@Test
	public void testMoveHero()
	{
		GameState gs = new GameState(3);
		Map test_map = new Map("test");
		Guard guard = new Guard(1,3, Guard.Guard_Type.Rookie);
		Hero hero = new Hero(1,1);
		assertEquals(new Hero(1,1), hero);
		hero.heroMove("s");
		hero.action(test_map, gs, guard);
		assertEquals(new Hero(1,2), hero);
	}
	
	@Test
	public void testNotMoveHero()
	{
		GameState gs = new GameState(3);
		Map test_map = new Map("test");
		Guard guard = new Guard(1,3, Guard.Guard_Type.Rookie);
		Hero hero = new Hero(1,1);
		hero.heroMove("a");
		hero.action(test_map, gs, guard);
		assertEquals(new Hero(1,1), hero);
	}
	
	@Test
	public void heroCaughtByGuard()
	{
		GameState gs = new GameState(3);
		Map test_map = new Map("test");
		Guard guard = new Guard(1,3, Guard.Guard_Type.Rookie);
		Hero hero = new Hero(1,1);
		hero.heroMove("d");
		hero.action(test_map, gs, guard);
		gs.test_collision(test_map.level, "G", hero);
		assertTrue(gs.isGame_over());
	}
	
	@Test
	public void heroNoExit()
	{
		GameState gs = new GameState(3);
		Map test_map = new Map("test");
		Hero hero = new Hero(1,1);
		hero.heroMove("s");
		hero.action(test_map, gs);
		hero.heroMove("a");
		hero.action(test_map, gs);
		assertFalse(gs.isVictory());
		assertEquals(new Hero(1,2), hero);
	}
	
	@Test
	public void doorsOpen()
	{
		GameState gs = new GameState(3);
		Map testMap = new Map("test");
		Hero hero = new Hero(1, 1);
		Guard guard = new Guard(1, 3, Guard.Guard_Type.Rookie);
		hero.heroMove("s");
		hero.action(testMap, gs, guard);
		hero.heroMove("s");
		hero.action(testMap, gs, guard);
		assertTrue(testMap.lever);
		assertEquals(testMap.level[2][0], "S");
		assertEquals(testMap.level[3][0], "S");
	}
	
	@Test
	public void levelProgression()
	{
		GameState gs = new GameState(3);
		Map testMap = new Map("test");
		Hero hero = new Hero(1, 1);
		Guard guard = new Guard(1, 3, Guard.Guard_Type.Rookie);
		hero.heroMove("s");
		hero.action(testMap, gs, guard);
		hero.heroMove("s");
		hero.action(testMap, gs, guard);
		hero.heroMove("a");
		hero.action(testMap, gs, guard);
		assertEquals(2, gs.getLevel_no());
	}
	
	
	
	

}
