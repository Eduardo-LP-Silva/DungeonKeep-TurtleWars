package com.lpoot4g4.tw.Model;

public class PowerUpModel extends EntityModel
{
    public enum Effect {Health, Damage, Null}

    private Effect effect;
    private long spawnTime;
    private final static float DAMAGE_BUFF_DURATION = 5;

    public PowerUpModel(float x, float y, Effect efct)
    {
        super(x,y);

        effect = efct;
    }

    public Effect getEffect() {
        return effect;
    }

    public static float getDamageBuffDuration() {
        return DAMAGE_BUFF_DURATION;
    }

    public long getSpawnTime() {
        return spawnTime;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public void setSpawnTime(long spawnTime) {
        this.spawnTime = spawnTime;
    }
}
