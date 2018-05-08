package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.PlatformModel;

public class PlatformBody extends EntityBody
{
    public PlatformBody(World world, PlatformModel pm)
    {
        super(world, pm);
    }

    @Override
    public void createFixture()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(7.3f / 2, 0.44f / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10;

        body.createFixture(fixtureDef);

        shape.dispose();
    }
}
