package com.lpoot4g4.tw.Model;

import com.badlogic.gdx.Gdx;

public class TurtleModel extends EntityModel
{
    public enum TurtleClass {Light, Heavy}
    public enum State {RunningRight, RunningLeft, Standing}

    private final static int MAX_HEALTH = 100;
    private final static int lightTurtleMeleeDamage = 5;
    private final  static  int lightTurtleStompDamage = 10;
    private final static int heavyTurtleMeleeDamage = 15;
    private final static int heavyTurtleStompDamage = 20;
    private final static int RELOADING_TIME = 2;
    private final static float BITING_TIME = 0.5f;

    private TurtleClass turtleClass;
    private int health;
    private boolean jumping;
    private boolean biting;
    private  boolean buffed = false;
    private boolean firing = false;
    private long reloadTime = -1;
    private long buffTime = -1;
    private long biteTime = -1;
    private int melee_damage;
    private int stomp_damage;
    private State previousState = State.Standing;
    private State currentState = State.Standing;

    public TurtleModel(float x,float y, TurtleClass tc)
    {
        super(x,y);

        health = MAX_HEALTH;
        turtleClass = tc;
        jumping = false;
        biting = false;

        if(tc.toString().equals("Light"))
        {
            melee_damage = lightTurtleMeleeDamage;
            stomp_damage = lightTurtleStompDamage;
        }
        else
        {
            melee_damage = heavyTurtleMeleeDamage;
            stomp_damage = heavyTurtleStompDamage;
        }
    }

    public TurtleClass getTurtleClass()
    {
        return turtleClass;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public static int getLightTurtleMeleeDamage() {
        return lightTurtleMeleeDamage;
    }

    public static int getLightTurtleStompDamage() {
        return lightTurtleStompDamage;
    }

    public static int getHeavyTurtleMeleeDamage() {
        return heavyTurtleMeleeDamage;
    }

    public static int getHeavyTurtleStompDamage() {
        return heavyTurtleStompDamage;
    }

    public boolean isJumping()
    {
        return jumping;
    }

    public boolean isBiting() {
        return biting;
    }

    public boolean isFiring() {
        return firing;
    }

    public int getMelee_damage() {
        return melee_damage;
    }

    public int getHealth() {
        return health;
    }

    public boolean isBuffed() {
        return buffed;
    }

    public int getStomp_damage() {
        return stomp_damage;
    }

    public State getCurrentState() {
        return currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setTurtleClass(TurtleClass turtleClass)
    {
        this.turtleClass = turtleClass;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setBiting(boolean biting) {
        this.biting = biting;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public void setBuffed(boolean buffed) {
        this.buffed = buffed;
    }

    public void setMelee_damage(int melee_damage) {
        this.melee_damage = melee_damage;
    }

    public void setStomp_damage(int stomp_damage) {
        this.stomp_damage = stomp_damage;
    }

    public void setReloadTime(long reloadTime)
    {
        this.reloadTime = reloadTime;
    }

    public void setBuffTime(long buffTime) {
        this.buffTime = buffTime;
    }

    public void setBiteTime(long biteTime) {
        this.biteTime = biteTime;
    }

    public void update()
    {
        if(firing)
        {
            if((System.currentTimeMillis() - reloadTime) / 1000 > RELOADING_TIME && reloadTime >= 0)
            {
                reloadTime = -1;
                firing = false;
            }
        }

        if(buffed)
        {
            if((System.currentTimeMillis() - buffTime) / 1000 > PowerUpModel.getDamageBuffDuration() && buffTime >= 0)
            {
                buffTime = - 1;
                buffed = false;

                if(getTurtleClass().toString().equals("Light"))
                {
                    setMelee_damage(TurtleModel.getLightTurtleMeleeDamage());
                    setStomp_damage(TurtleModel.getLightTurtleStompDamage());
                }
                else
                {
                    setMelee_damage(TurtleModel.getHeavyTurtleMeleeDamage());
                    setStomp_damage(TurtleModel.getHeavyTurtleStompDamage());
                }
            }
        }

        if(biting)
        {
            if((System.currentTimeMillis() - biteTime) / 1000 > BITING_TIME)
            {
                biteTime = -1;
                biting = false;
            }
        }
    }

    public void inflictDamage(int damage)
    {
        if(damage > health)
            health = 0;
        else
            health -= damage;
    }

    public void addHealth(int hp)
    {
        if(health < 75)
            health += hp;
        else
            health = 100;
    }
}
