package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.PlatformModel;

public class PlatformBody extends EntityBody
{
    /**
     * Constructor of the class.
     *
     * @param world The world where the platform is.
     * @param pm The platform model.
     */
    public PlatformBody(World world, PlatformModel pm)
    {
        super(world, pm, BodyDef.BodyType.StaticBody, 800, 48);
    }

    /**
     * Creates the fixture (horizontal).
     */
    @Override
    public void createFixture()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8f / 2, 0.48f / 2, new Vector2(4f,0.24f), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 100;
        fixtureDef.friction = 0.5f;

        body.createFixture(fixtureDef).setUserData("Platform");

        shape.dispose();
    }
}
