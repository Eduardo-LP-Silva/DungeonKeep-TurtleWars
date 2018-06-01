package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.EntityModel;

import static com.lpoot4g4.tw.View.PlayView.PIXEL_TO_METER;

public abstract class EntityBody
{
    /**
     * The body of the entity.
     */
    protected Body body;

    /**
     * Body's width(pixels).
     */
    protected float Width;


    /**
     * Body's height(pixels).
     */
    protected float Height;

    /**
     * Constructor for this class. Creates the body in the game world.
     *
     * @param world The game world.
     * @param model The game model.
     * @param bt The body definition.
     * @param W Body's width.
     * @param H Body's height.
     */
    EntityBody(World world, EntityModel model, BodyDef.BodyType bt, float W, float H)
    {
        Width = W;
        Height = H;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bt;
        bodyDef.position.set(model.getX() * PIXEL_TO_METER, model.getY() * PIXEL_TO_METER);
        bodyDef.fixedRotation = true;

        body = world.createBody(bodyDef);
        body.setUserData(model);

        createFixture();
    }

    /**
     * Returns the body itself.
     *
     * @return The body.
     */
    public Body getBody()
    {
        return body;
    }

    public abstract void  createFixture();

    /**
     * Wraps the getX method from the Box2D body class.
     *
     * @return the x-coordinate of this body.
     */
    public float getX()
    {
        return body.getPosition().x;
    }

    /**
     * Wraps the getY method from the Box2D body class.
     *
     * @return the y-coordinate of this body.
     */
    public float getY()
    {
        return body.getPosition().y;
    }

    /**
     * Returns the body's width.
     *
     * @return The body's width.
     */
    public float getWidth() {
        return Width;
    }

    /**
     * Returns the body's height.
     *
     * @return The body's height.
     */
    public float getHeight() {
        return Height;
    }
}
