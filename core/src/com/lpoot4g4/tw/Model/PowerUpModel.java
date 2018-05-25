package com.lpoot4g4.tw.Model;

public class PowerUpModel extends EntityModel
{
    public enum Effect {Health, Damage, Null};

    private Effect effect;

    public PowerUpModel(float x, float y, Effect efct)
    {
        super(x,y);

        effect = efct;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
