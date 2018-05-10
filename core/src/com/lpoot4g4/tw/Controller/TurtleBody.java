package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.TurtleModel;

public class TurtleBody extends EntityBody
{
    private float speed;

    public TurtleBody(World world, TurtleModel tm)
    {
        super(world, tm, BodyDef.BodyType.DynamicBody);

        if(tm.getClass().toString().equals("Ligth"))
            speed = 12.42f;
        else
            speed = 8;
    }

    public void moveLeft()
    {
        body.applyLinearImpulse(new Vector2(-1,0), body.getWorldCenter(), true);
    }

    public void moveRight()
    {
        body.applyLinearImpulse(new Vector2(1,0), body.getWorldCenter(), true);
    }

    public void jump()
    {
        System.out.println(body.getLinearVelocity().y);

        if(!((TurtleModel) body.getUserData()).isJumping())
            body.applyLinearImpulse(new Vector2(0,30), body.getWorldCenter(), true);
    }

    @Override
    public void createFixture()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.88f / 2, 0.59f / 2, new Vector2(0.44f,0.30f), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10;
        fixtureDef.friction = 0.5f;

        body.createFixture(fixtureDef);

        shape.dispose();
    }
}
