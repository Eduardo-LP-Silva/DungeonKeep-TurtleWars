package com.lpoot4g4.tw.Model;

public abstract class EntityModel
{
    /**
     * Entity's X coordinate.
     */
    protected float x;

    /**
     * Entity's Y coordinate.
     */
    protected float y;

    /**
     * Boolean to check if the models is flagged to be removed.
     */
    protected  boolean flaggedForRemoval;

    /**
     * Boolean to check if the model is facing the left wall.
     */
    protected boolean backwards = false;

    /**
     * Constructor of the class.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public EntityModel(float x,float y)
    {
        this.x = x;
        this.y = y;
        flaggedForRemoval = false;
    }

    /**
     * Sets the entity position.
     *
     * @param x The new X coordinate.
     * @param y The new Y coordinate.
     */
    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the entity's X coordinate.
     *
     * @return The X coordinate.
     */
    public float getX()
    {
        return this.x;
    }

    /**
     * Returns the entity's Y coordinate.
     *
     * @return The Y coordinate.
     */
    public float getY()
    {
        return this.y;
    }

    /**
     * Checks if the model is flagged for removal.
     *
     * @return True if it is flagged, false otherwise.
     */
    public boolean isFlaggedForRemoval() {
        return flaggedForRemoval;
    }

    /**
     * Checks if the model is backwards (facing left wall).
     *
     * @return True if the model is backwards, false otherwise.
     */
    public boolean isBackwards() {
        return backwards;
    }

    /**
     * Sets the new X coordinate.
     *
     * @param x The new X coordinate.
     */
    public void setX(float x)
    {
        this.x = x;
    }

    /**
     * Sets the new Y coordinate.
     *
     * @param y The new Y coordinate.
     */
    public void setY(float y)
    {
        this.y = y;
    }

    /**
     * Sets the value of the removal flag.
     *
     * @param flaggedForRemoval The new value of the remove flag.
     */
    public void setFlaggedForRemoval(boolean flaggedForRemoval) {
        this.flaggedForRemoval = flaggedForRemoval;
    }

    /**
     * Sets the value of the backwards flag.
     *
     * @param backwards The new value of the backwards flag.
     */
    public void setBackwards(boolean backwards) {
        this.backwards = backwards;
    }
}
