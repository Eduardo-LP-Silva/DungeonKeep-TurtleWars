package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.ProjectileModel;

public class ProjectileBody extends EntityBody
{
    /**
     * Constructor of the class
     *
     * @param w The world where the projectile is.
     * @param pm The projectile's model.
     */
    public ProjectileBody(World w, ProjectileModel pm) {
        super(w, pm, BodyDef.BodyType.KinematicBody, 42, 27);
    }

    /**
     * Creates the projectile's fixture.
     */
    @Override
    public void createFixture()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.42f / 2, 0.27f / 2, new Vector2(0.21f, 0.27f / 2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10;

        body.createFixture(fixtureDef).setUserData("Bullet");

        shape.dispose();
    }

    /**
     * Moves the projectile according to a certain speed.
     *
     * @param speed The projectile's speed.
     */
    public void move(float speed)
    {
        body.setLinearVelocity(new Vector2(speed, 0));
    }
}
