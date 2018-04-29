package com.lpoot4g4.tw.Model;

public class ProjectileModel extends EntityModel
{
    public final static int BASE_DAMAGE = 20;
    private int damage;

    public ProjectileModel(float x, float y)
    {
        super(x,y);

        damage = BASE_DAMAGE;
    }
}
