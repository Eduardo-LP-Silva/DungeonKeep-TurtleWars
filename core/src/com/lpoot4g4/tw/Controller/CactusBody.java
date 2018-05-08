package com.lpoot4g4.tw.Controller;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.CactusModel;

public class CactusBody extends EntityBody
{
    public CactusBody(World world, CactusModel ct)
    {
        super(world, ct);
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
