package com.lpoot4g4.tw.Model;

public abstract class EntityModel
{
    protected float x;
    protected float y;
    protected  boolean flaggedForRemoval;

    public EntityModel(float x,float y)
    {
        this.x = x;
        this.y = y;
        flaggedForRemoval = false;
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

    public boolean isFlaggedForRemoval() {
        return flaggedForRemoval;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setFlaggedForRemoval(boolean flaggedForRemoval) {
        this.flaggedForRemoval = flaggedForRemoval;
    }
}
