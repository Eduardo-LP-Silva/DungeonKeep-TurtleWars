package dkeep.logic;

public class Character 
{
	protected int x, y, nx, ny;
	
	public Character(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getNx() 
	{
		return nx;
	}

	public void setNx(int nx) 
	{
		this.nx = nx;
	}

	public int getNy() 
	{
		return ny;
	}

	public void setNy(int ny) 
	{
		this.ny = ny;
	}

	@Override
	public boolean equals(Object c2)
	{
		if(!(c2 instanceof Character))
			return false;
		
		Character c1 = (Character) c2;
		
		return c1.x == x && c1.y == y;
	}
}
