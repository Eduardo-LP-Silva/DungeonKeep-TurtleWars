package com.lpoot4g4.tw.Model;
import com.lpoot4g4.tw.Model.EntityModel;

public class TurtleModel extends EntityModel
{
    public enum TurtleClass {Light, Heavy};

    private final static int MAX_HEALTH = 100;
    private TurtleClass turtleClass;
    private int health;

    public TurtleModel(float x,float y, TurtleClass tc)
    {
        super(x,y);
        health = MAX_HEALTH;
        turtleClass = tc;
    }

    public void setTurtleClass(TurtleClass turtleClass)
    {
        this.turtleClass = turtleClass;
    }
}
