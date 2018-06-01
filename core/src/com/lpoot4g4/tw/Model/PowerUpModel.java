package com.lpoot4g4.tw.Model;

public class PowerUpModel extends EntityModel
{
    /**
     * Power up possible effects.
     */
    public enum Effect {Health, Damage, Null}

    /**
     * The effect of the current power up.
     */
    private Effect effect;

    /**
     * The spawn time of the power up.
     */
    private long spawnTime;

    /**
     * The damage buff duration.
     */
    private final static float DAMAGE_BUFF_DURATION = 5;

    /**
     * Constructor of the class.
     *
     * @param x Power-up X coordinate.
     * @param y Power-up Y coordinate.
     */
    public PowerUpModel(float x, float y, Effect efct)
    {
        super(x,y);

        effect = efct;
    }

    /**
     * Returns the effect of the power up.
     *
     * @return The power up's effect.
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Returns the damage buff duration of the power up.
     *
     * @return The damage buff duration.
     */
    public static float getDamageBuffDuration() {
        return DAMAGE_BUFF_DURATION;
    }

    /**
     * Returns the time at which it spawned.
     *
     * @return The spawn time.
     */
    public long getSpawnTime() {
        return spawnTime;
    }

    /**
     * Sets the effect of the power up.
     *
     * @param effect The effect of the power up.
     */
    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    /**
     * Sets the spawn time of the power up.
     *
     * @param spawnTime The spawn time.
     */
    public void setSpawnTime(long spawnTime) {
        this.spawnTime = spawnTime;
    }
}
