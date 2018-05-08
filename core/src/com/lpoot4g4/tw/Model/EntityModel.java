package com.lpoot4g4.tw.Model;

public abstract class EntityModel
{
    private float x;
    private float y;

    public EntityModel(float x,float y)
    {
        this.x = x;
        this.y = y;
    }

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }


    public float getX()
    {
        return this.x;
    }


    public float getY()
    {
        return this.y;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }
}
