package com.lpoot4g4.tw.Model;

public class ProjectileModel extends EntityModel
{
    public final static int BASE_DAMAGE = 20;
    public final static float TRAVEL_SPEED = 15f;

    private int damage;
    private long dissipationTimeStart = 0; //ms

    public ProjectileModel(float x, float y)
    {
        super(x,y);

        damage = BASE_DAMAGE;
    }

    public float getDissipationTimeStart()
    {
        return dissipationTimeStart;
    }

    public void setForRemoval()
    {
        flaggedForRemoval = true;
        dissipationTimeStart = System.nanoTime();
    }
}
