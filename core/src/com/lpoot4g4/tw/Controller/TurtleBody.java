package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.TurtleModel;

public class TurtleBody extends EntityBody
{
    private float speed;

    public TurtleBody(World world, TurtleModel tm)
    {
        super(world, tm);

        if(tm.getClass().toString().equals("Ligth"))
            speed = 12.42f;
        else
            speed = 8;
    }

    public void moveLeft(float delta)
    {

    }

    @Override
    public void createFixture()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.88f / 2, 0.59f / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10;

        body.createFixture(fixtureDef);

        shape.dispose();
    }
}
