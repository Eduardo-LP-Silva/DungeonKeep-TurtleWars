package com.lpoot4g4.tw.Controller;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.PowerUpModel;

import static com.lpoot4g4.tw.View.PlayView.PIXEL_TO_METER;

public class PowerUpBody extends EntityBody
{
    public PowerUpBody(World world, PowerUpModel model)
    {
        super(world, model, BodyDef.BodyType.DynamicBody, 45, 45);
    }

    @Override
    public void createFixture()
    {
        CircleShape shape = new CircleShape();

        shape.setRadius(getWidth() * PIXEL_TO_METER / 2);
        shape.setPosition(new Vector2(getWidth() * PIXEL_TO_METER / 2, getHeight() * PIXEL_TO_METER / 2));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10;

        body.createFixture(fixtureDef).setUserData("PowerUp");

        shape.dispose();
    }
}
