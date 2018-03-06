package dkeep.logic;

public class Character 
{
	public int x, y, nx, ny;
	
	public Character(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object c2)
	{
		return getClass().equals(c2.getClass()) && 
				(((Character)c2).x == x) && (((Character) c2).y == y);
	}
}
