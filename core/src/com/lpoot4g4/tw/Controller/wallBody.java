package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.PlatformModel;
import com.lpoot4g4.tw.TurtleWars;

public class wallBody extends EntityBody
{
    public wallBody(World world, PlatformModel pm)
    {
        super(world, pm, BodyDef.BodyType.StaticBody, 1, TurtleWars.HEIGHT);
    }

    @Override
    public void createFixture()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0, TurtleWars.HEIGHT / 2, new Vector2(0,TurtleWars.HEIGHT / 2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 100;

        body.createFixture(fixtureDef).setUserData("Wall");

        shape.dispose();
    }
}
