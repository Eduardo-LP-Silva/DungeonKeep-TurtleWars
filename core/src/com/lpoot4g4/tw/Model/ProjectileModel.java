package com.lpoot4g4.tw.Model;

public class ProjectileModel extends EntityModel
{
    /**
     * Projectile's base damage.
     */
    public final static int BASE_DAMAGE = 20;

    /**
     * Projectile's speed.
     */
    public final static float TRAVEL_SPEED = 15f;

    /**
     * Projectile's damage.
     */
    private int damage;

    /**
     * Time registered when the projectile hits a body (ms).
     */
    private long dissipationTimeStart = 0;

    /**
     * Constructor of the class.
     *
     * @param x Projectile X coordinate.
     * @param y Projectile Y coordinate.
     */
    public ProjectileModel(float x, float y)
    {
        super(x,y);

        damage = BASE_DAMAGE;
    }

    /**
     * Gets the time registered when the projectile hits a body.
     *
     * @return The time registered when the projectile hits a body.
     */
    public float getDissipationTimeStart()
    {
        return dissipationTimeStart;
    }

    /**
     * Makes this model flagged for removal in next step.
     */
    public void setForRemoval()
    {
        flaggedForRemoval = true;
        dissipationTimeStart = System.nanoTime();
    }
}
