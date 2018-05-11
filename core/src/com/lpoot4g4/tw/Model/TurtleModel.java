package com.lpoot4g4.tw.Model;
import com.lpoot4g4.tw.Model.EntityModel;

public class TurtleModel extends EntityModel
{
    public enum TurtleClass {Light, Heavy};

    private final static int MAX_HEALTH = 100;
    private TurtleClass turtleClass;
    private int health;
    private boolean jumping;
    private boolean bitting;

    public TurtleModel(float x,float y, TurtleClass tc)
    {
        super(x,y);

        health = MAX_HEALTH;
        turtleClass = tc;
        jumping = false;
        bitting = false;
    }

    public TurtleClass getTurtleClass()
    {
        return turtleClass;
    }

    public boolean isJumping()
    {
        return jumping;
    }

    public void setTurtleClass(TurtleClass turtleClass)
    {
        this.turtleClass = turtleClass;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
