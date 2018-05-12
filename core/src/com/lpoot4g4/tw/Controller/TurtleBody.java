package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.lpoot4g4.tw.Model.TurtleModel;
import static com.lpoot4g4.tw.View.PlayView.PIXEL_TO_METER;

public class TurtleBody extends EntityBody
{
    private float speed;

    public TurtleBody(World world, TurtleModel tm)
    {
        super(world, tm, BodyDef.BodyType.DynamicBody);

        Width = 88;
        Height = 59;

        if(tm.getTurtleClass().toString().equals("Light"))
            speed = 5f;
        else
            speed = 0.7f;
    }

    public void moveLeft()
    {
        if(body.getLinearVelocity().x >= -7 && getX() > 0.5f)
        {
            /*
            if(body.getLinearVelocity().x > 0)
                body.setLinearVelocity(0, body.getLinearVelocity().y); */

            body.applyLinearImpulse(new Vector2(-speed,0), body.getWorldCenter(), true);
        }

    }

    public void moveRight()
    {

        if(body.getLinearVelocity().x <= 7 && getX() + getWidth() * PIXEL_TO_METER < 795f * PIXEL_TO_METER)
        {
           /* if(body.getLinearVelocity().x < 0)
                body.setLinearVelocity(0, body.getLinearVelocity().y); */

            body.applyLinearImpulse(new Vector2(speed,0), body.getWorldCenter(), true);
        }

    }

    public void jump()
    {
        if(!((TurtleModel) body.getUserData()).isJumping())
        {
            body.applyLinearImpulse(new Vector2(0,30), body.getWorldCenter(), true);
            ((TurtleModel) body.getUserData()).setJumping(true);
        }
    }

    public void bite()
    {
        ((TurtleModel) body.getUserData()).setBiting(true);

        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                ((TurtleModel) body.getUserData()).setBiting(false);
            }
        }, 1.5f);
    }

    @Override
    public void createFixture()
    {
        //Body Fixture

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.88f / 2, 0.59f / 2, new Vector2(0.44f,0.30f), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10;
        fixtureDef.friction = 0.5f;

        body.createFixture(fixtureDef).setUserData("Turtle Body");

        shape.dispose();

        //Paws/Bottom Fixture

        EdgeShape paws = new EdgeShape();

        paws.set(new Vector2(- (this.getWidth() / 2) * PIXEL_TO_METER, - (this.getHeight() / 2) * PIXEL_TO_METER),
                new Vector2((this.getWidth() / 2 - 1) * PIXEL_TO_METER, - (this.getHeight() / 2) * PIXEL_TO_METER));

        fixtureDef.shape = paws;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("Turtle Bottom");

        paws.dispose();

        //Left Side Fixture

        EdgeShape leftSide = new EdgeShape();

        leftSide.set(new Vector2(- (this.getWidth() / 2) * PIXEL_TO_METER, (this.getHeight() / 2) * PIXEL_TO_METER),
                new Vector2(- (this.getWidth() / 2) * PIXEL_TO_METER, - (this.getHeight() / 2) * PIXEL_TO_METER));

        fixtureDef.shape = leftSide;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("Turtle Left Side");

        leftSide.dispose();

        //Right Side Fixture

        EdgeShape rightSide = new EdgeShape();

        rightSide.set(new Vector2((this.getWidth() / 2) * PIXEL_TO_METER + 1, (this.getHeight() / 2) * PIXEL_TO_METER),
                new Vector2((this.getWidth() / 2) * PIXEL_TO_METER, - (this.getHeight() / 2) * PIXEL_TO_METER));

        fixtureDef.shape = rightSide;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("Turtle Right Side");

        rightSide.dispose();
    }
}
