package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.ProjectileModel;

public class ProjectileBody extends EntityBody
{
    public ProjectileBody(World w, ProjectileModel pm)
    {
        super(w, pm, BodyDef.BodyType.DynamicBody);

        //TODO Initialize Width and Heigth
    }

    @Override
    public void createFixture()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1.33f / 2, 2.29f / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10;

        body.createFixture(fixtureDef);

        shape.dispose();
    }
}
