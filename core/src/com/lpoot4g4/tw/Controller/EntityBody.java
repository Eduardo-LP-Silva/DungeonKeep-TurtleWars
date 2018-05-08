package com.lpoot4g4.tw.Controller;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.EntityModel;

import static com.lpoot4g4.tw.View.GameView.PIXEL_TO_METER;

public abstract class EntityBody
{
    protected Body body;

    EntityBody(World world, EntityModel model)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX() * PIXEL_TO_METER, model.getY() * PIXEL_TO_METER);

        body = world.createBody(bodyDef);
        body.setUserData(model);

        createFixture();
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

}
