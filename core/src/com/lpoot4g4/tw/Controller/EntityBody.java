package com.lpoot4g4.tw.Controller;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.EntityModel;

import static com.lpoot4g4.tw.View.PlayView.PIXEL_TO_METER;

public abstract class EntityBody
{
    protected Body body;
    protected float Width; //Pixels
    protected float Height; //Pixels

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

    public float getWidth() {
        return Width;
    }

    public float getHeight() {
        return Height;
    }
}
