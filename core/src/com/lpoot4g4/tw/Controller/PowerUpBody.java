package com.lpoot4g4.tw.Controller;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.PowerUpModel;

public class PowerUpBody extends EntityBody
{
    public PowerUpBody(World world, PowerUpModel model)
    {
        super(world, model, BodyDef.BodyType.DynamicBody, 12, 12);
    }

    @Override
    public void createFixture()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2, new Vector2(getWidth() / 2, getHeight() / 2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5;

        body.createFixture(fixtureDef).setUserData("PowerUp");

        shape.dispose();
    }
}
