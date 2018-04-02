package dkeep.logic;

public class Character 
{
	protected int x, y, nx, ny;
	
	/** 
	 * Constructor of the class.
	 * 
	 * @param x The x coordinate of the new character.
	 * @param y The y coordinate of the new character.
	 * 
	 */
	public Character(int x, int y)
	{
		this.x = x;
		this.y = y;
		nx = x;
		ny = y;
	}
	
	/**
	 * Returns the X coordinate
	 * 
	 * @return The X coordinate
	 * 
	 */
	public int getX() 
	{
		return x;
	}
	
	/**
	 * Modifies the value of the X coordinate.
	 * 
	 * @param x New value for the X coordinate.
	 * 
	 */
	public void setX(int x) 
	{
		this.x = x;
	}
	
	/**
	 * Returns the value of the Y coordinate.
	 * 
	 * @return The value of the Y coordinate.
	 */
	public int getY() 
	{
		return y;
	}
	
	/**
	 * Modifies the value of the Y coordinate
	 * 
	 * @param y New value for the Y coordinate
	 * 
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
	
	/**
	 * Returns the (possible) next X coordinate.
	 * 
	 * @return The (possible) next X coordinate.
	 */
	public int getNx() 
	{
		return nx;
	}
	
	/**
	 * Sets the value of the (possible) next X coordinate
	 * 
	 * @param nx The value of the (possible) next X coordinate
	 */
	public void setNx(int nx) 
	{
		this.nx = nx;
	}
	
	/**
	 * Returns the (possible) next Y coordinate.
	 * 
	 * @return The (possible) next Y coordinate.
	 */
	public int getNy() 
	{
		return ny;
	}

	/**
	 * Sets the value of the (possible) next Y coordinate
	 * 
	 * @param ny The value of the (possible) next Y coordinate
	 */
	public void setNy(int ny) 
	{
		this.ny = ny;
	}
	
	/**
	 * Compares to Character objects. They are considered equal if they have the same X and Y 
	 * coordinates.
	 * 
	 * @param c2 The second Character to compare to the original Character.
	 * @return False if the coordinates do not match, true otherwise.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object c2)
	{
		if(!(c2 instanceof Character))
			return false;
		
		Character c1 = (Character) c2;
		
		return c1.x == x && c1.y == y;
	}
}
