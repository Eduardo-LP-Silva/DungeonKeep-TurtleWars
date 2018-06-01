package com.lpoot4g4.tw.Model;

public class TurtleModel extends EntityModel
{
    /**
     * Possible types of a turtle.
     */
    public enum TurtleClass {Light, Heavy}

    /**
     * Possible movement states for a turtle.
     */
    public enum State {RunningRight, RunningLeft, Standing}

    /**
     * Turtle's max health.
     */
    private final static int MAX_HEALTH = 100;

    /**
     * Bite damage of light turtle.
     */
    private final static int lightTurtleMeleeDamage = 5;

    /**
     * Stomp damage of light turtle.
     */
    private final  static  int lightTurtleStompDamage = 10;

    /**
     * Bite damage of heavy turtle.
     */
    private final static int heavyTurtleMeleeDamage = 15;

    /**
     * Stomp damage of heavy turtle.
     */
    private final static int heavyTurtleStompDamage = 20;

    /**
     * Cooldown time to shoot the missile again.
     */
    private final static int RELOADING_TIME = 2;

    /**
     * Cooldown time to bite again.
     */
    private final static float BITING_TIME = 0.5f;

    /**
     * Turtle's class.
     */
    private TurtleClass turtleClass;

    /**
     * Turtle's health.
     */
    private int health;

    /**
     * True if the turtle is jumping.
     */
    private boolean jumping;

    /**
     * True if the turtle is biting.
     */
    private boolean biting;

    /**
     * True if the turtle caught a damage power up.
     */
    private  boolean buffed = false;

    /**
     * True if the turtle is firing.
     */
    private boolean firing = false;

    /**
     * Current time spent reloading
     */
    private long reloadTime = -1;

    /**
     * Current time the power up has been active on the turtle.
     */
    private long buffTime = -1;

    /**
     * Current time the turtle has been biting.
     */
    private long biteTime = -1;

    /**
     * Current bite damage.
     */
    private int melee_damage;

    /**
     * Current stomp damage.
     */
    private int stomp_damage;

    /**
     * Previous turtle's state.
     */
    private State previousState = State.Standing;

    /**
     * Current turtle's state.
     */
    private State currentState = State.Standing;

    /**
     * Constructor of the class.
     *
     * @param x The turtle's X coordinate.
     * @param y The turtle's Y coordinate.
     * @param tc The turtle's class.
     */
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

    /**
     * Return the turtle's class.
     *
     * @return The turtle's class.
     */
    public TurtleClass getTurtleClass()
    {
        return turtleClass;
    }

    /**
     * Returns the light turtle's bite damage.
     *
     * @return The bite damage.
     */
    public static int getLightTurtleMeleeDamage() {
        return lightTurtleMeleeDamage;
    }

    /**
     * Returns the light turtle's stomp damage.
     *
     * @return The stomp damage.
     */
    public static int getLightTurtleStompDamage() {
        return lightTurtleStompDamage;
    }

    /**
     * Returns the heavy turtle's bite damage.
     *
     * @return The bite damage.
     */
    public static int getHeavyTurtleMeleeDamage() {
        return heavyTurtleMeleeDamage;
    }

    /**
     * Returns the heavy turtle's stomp damage.
     *
     * @return The stomp damage.
     */
    public static int getHeavyTurtleStompDamage() {
        return heavyTurtleStompDamage;
    }

    /**
     * Checks if the turtle is jumping.
     *
     * @return True if the turtle is jumping.
     */
    public boolean isJumping()
    {
        return jumping;
    }

    /**
     * Checks if the turtle is biting.
     *
     * @return True if the turtle is biting.
     */
    public boolean isBiting() {
        return biting;
    }

    /**
     * Checks if the turtle is firing.
     *
     * @return True if the turtle is firing.
     */
    public boolean isFiring() {
        return firing;
    }

    /**
     * Returns the current bite damage.
     *
     * @return The bite damage.
     */
    public int getMelee_damage() {
        return melee_damage;
    }

    /**
     * Returns the turtle's health.
     *
     * @return The health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Checks if the turtle caught a damage power up.
     *
     * @return True if it has caught a damage power up.
     */
    public boolean isBuffed() {
        return buffed;
    }

    /**
     * Returns the current stomp damage.
     *
     * @return The stomp damage.
     */
    public int getStomp_damage() {
        return stomp_damage;
    }

    /**
     * Returns the turtle's current state.
     *
     * @return The current state.
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * Returns the previous turtle's state.
     *
     * @return The previous state.
     */
    public State getPreviousState() {
        return previousState;
    }

    /**
     * Sets the turtle's class.
     *
     * @param turtleClass The class.
     */
    public void setTurtleClass(TurtleClass turtleClass)
    {
        this.turtleClass = turtleClass;
    }

    /**
     * Sets the turtle's jumping flag.
     *
     * @param jumping The boolean value for the jumping flag.
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    /**
     * Sets the turtle's biting flag.
     *
     * @param biting The boolean value for the biting flag.
     */
    public void setBiting(boolean biting) {
        this.biting = biting;
    }

    /**
     * Sets the turtle's firing flag.
     *
     * @param firing The boolean value for the firing flag.
     */
    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    /**
     * Sets the turtle's current state.
     *
     * @param currentState The current state.
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * Sets the turtle's previous state.
     *
     * @param previousState The previous state.
     */
    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    /**
     * Sets the turtle's buffed flag.
     *
     * @param buffed The boolean value for the buffed flag.
     */
    public void setBuffed(boolean buffed) {
        this.buffed = buffed;
    }

    /**
     * Sets the turtle's bite damage.
     *
     * @param melee_damage The bite damage.
     */
    public void setMelee_damage(int melee_damage) {
        this.melee_damage = melee_damage;
    }

    /**
     * Sets the turtle's stomp damage.
     *
     * @param stomp_damage The stomp damage.
     */
    public void setStomp_damage(int stomp_damage) {
        this.stomp_damage = stomp_damage;
    }

    /**
     * Sets the turtle's current reload time mark.
     *
     * @param reloadTime The reload time mark.
     */
    public void setReloadTime(long reloadTime)
    {
        this.reloadTime = reloadTime;
    }

    /**
     * Sets the turtle's buff time mark.
     *
     * @param buffTime The buff time mark.
     */
    public void setBuffTime(long buffTime) {
        this.buffTime = buffTime;
    }

    /**
     * Sets the bite time mark.
     *
     * @param biteTime The bite time mark.
     */
    public void setBiteTime(long biteTime) {
        this.biteTime = biteTime;
    }

    /**
     * Updates the turtle's timed events (current buffs, reload and biting).
     */
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

    /**
     * Takes health from the turtle.
     *
     * @param damage The damage taken.
     */
    public void inflictDamage(int damage)
    {
        if(damage > health)
            health = 0;
        else
            health -= damage;
    }

    /**
     * Adds health to turtle.
     *
     * @param hp The health to add.
     */
    public void addHealth(int hp)
    {
        if(health < 75)
            health += hp;
        else
            health = 100;
    }
}
