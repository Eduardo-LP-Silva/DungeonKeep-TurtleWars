package com.lpoot4g4.tw.Model;

public class CactusModel extends EntityModel
{
    public final static int MAX_HEALTH = 20;
    private int health;

    public CactusModel(float x, float y)
    {
        super(x,y);

        health = MAX_HEALTH;
    }
}
