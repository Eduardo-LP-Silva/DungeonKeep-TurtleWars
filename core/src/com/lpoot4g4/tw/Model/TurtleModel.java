package com.lpoot4g4.tw.Model;

public class TurtleModel extends EntityModel
{
    public enum TurtleClass {Light, Heavy}
    public enum State {RunningRight, RunningLeft, Standing}

    private final static int MAX_HEALTH = 100;

    private TurtleClass turtleClass;
    private int health;
    private boolean jumping;
    private boolean biting;
    private boolean firing = false;
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
            melee_damage = 5;
            stomp_damage = 10;
        }
        else
        {
            melee_damage = 15;
            stomp_damage = 20;
        }
    }

    public TurtleClass getTurtleClass()
    {
        return turtleClass;
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

    public void inflictDamage(int damage)
    {
        if(damage > health)
            health = 0;
        else
            health -= damage;
    }
}
