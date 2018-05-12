package com.lpoot4g4.tw.Model;

public class TurtleModel extends EntityModel
{
    public enum TurtleClass {Light, Heavy}

    private final static int MAX_HEALTH = 100;

    private TurtleClass turtleClass;
    private int health;
    private boolean jumping;
    private boolean biting;
    private int melee_damage;

    public TurtleModel(float x,float y, TurtleClass tc)
    {
        super(x,y);

        health = MAX_HEALTH;
        turtleClass = tc;
        jumping = false;
        biting = false;

        if(tc.toString().equals("Light"))
            melee_damage = 5;
        else
            melee_damage = 15;
    }

    public TurtleClass getTurtleClass()
    {
        return turtleClass;
    }

    public boolean isJumping()
    {
        return jumping;
    }

    public boolean isBiting() {
        return biting;
    }

    public int getMelee_damage() {
        return melee_damage;
    }

    public int getHealth() {
        return health;
    }

    public void setTurtleClass(TurtleClass turtleClass)
    {
        this.turtleClass = turtleClass;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setBiting(boolean biting) {
        this.biting = biting;
    }

    public void inflictDamage(int damage)
    {
        if(damage > health)
            health = 0;
        else
            health -= damage;
    }
}
