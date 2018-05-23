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
import static java.lang.Math.abs;

public class TurtleBody extends EntityBody
{
    private float speed;
    private boolean retreating = false;

    public TurtleBody(World world, TurtleModel tm)
    {
        super(world, tm, BodyDef.BodyType.DynamicBody, 88, 59);

        if(tm.getTurtleClass().toString().equals("Light"))
            speed = 5f;
        else
            speed = 1f;
    }

    public boolean isRetreating() {
        return retreating;
    }

    public void setRetreating(boolean retreating) {
        this.retreating = retreating;
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
        }, 0.5f);
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

        paws.set(new Vector2( (0 + 10) * PIXEL_TO_METER,  0),
                new Vector2((this.getWidth() - 10) * PIXEL_TO_METER,  0));

        fixtureDef.shape = paws;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("Turtle Bottom");

        paws.dispose();

        //Left Side Fixture

        EdgeShape leftSide = new EdgeShape();

        leftSide.set(new Vector2(0, this.getHeight() * PIXEL_TO_METER),
                new Vector2(0, 0));

        fixtureDef.shape = leftSide;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("Turtle Left Side");

        leftSide.dispose();

        //Right Side Fixture

        EdgeShape rightSide = new EdgeShape();

        rightSide.set(new Vector2(this.getWidth() * PIXEL_TO_METER, this.getHeight() * PIXEL_TO_METER),
                new Vector2(this.getWidth() * PIXEL_TO_METER, 0));

        fixtureDef.shape = rightSide;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("Turtle Right Side");

        rightSide.dispose();
    }

    public boolean isInBittingRange(float player2X)
    {
        float distance = abs(this.getX() - player2X);

        if(distance / this.speed < 0.5f)
            return true;
        else
            return false;
    }
}
