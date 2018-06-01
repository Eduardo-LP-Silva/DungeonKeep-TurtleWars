package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.TurtleModel;
import static com.lpoot4g4.tw.View.PlayView.PIXEL_TO_METER;
import static java.lang.Math.abs;

public class TurtleBody extends EntityBody
{
    /**
     * The turtle's speed.
     */
    private float speed;

    /**
     * Boolean to check if the AI player is retreating from an attack.
     */
    private boolean retreating = false;

    /**
     * Constructor of the class.
     *
     * @param world The world where the turtle is.
     * @param tm The turtle's model.
     */
    public TurtleBody(World world, TurtleModel tm)
    {
        super(world, tm, BodyDef.BodyType.DynamicBody, 88, 59);

        if(tm.getTurtleClass().toString().equals("Light"))
            speed = 5f;
        else
            speed = 1f;
    }

    /**
     * Checks if the AI player is retreating.
     *
     * @return The retreating variable value.
     */
    public boolean isRetreating() {
        return retreating;
    }

    /**
     * Sets the retreating flag for the AI player.
     *
     * @param retreating The new value for the retreating flag.
     */
    public void setRetreating(boolean retreating) {
        this.retreating = retreating;
    }

    /**
     * Moves the body to the left.
     */
    public void moveLeft()
    {
        if(body.getLinearVelocity().x >= -7 && getX() > 0.5f)
        {
            body.applyLinearImpulse(new Vector2(-speed,0), body.getWorldCenter(), true);
        }

    }
    /**
     * Moves the body to the right.
     */
    public void moveRight()
    {
        if(body.getLinearVelocity().x <= 7 && getX() + getWidth() * PIXEL_TO_METER < 795f * PIXEL_TO_METER)
        {
            body.applyLinearImpulse(new Vector2(speed,0), body.getWorldCenter(), true);
        }

    }

    /**
     * Makes the body jump.
     */
    public void jump()
    {
        if(!((TurtleModel) body.getUserData()).isJumping())
        {
            body.applyLinearImpulse(new Vector2(0,30), body.getWorldCenter(), true);
            ((TurtleModel) body.getUserData()).setJumping(true);
        }
    }

    /**
     * Initiates a bite attack.
     */
    public void bite()
    {
        ((TurtleModel) body.getUserData()).setBiting(true);
        ((TurtleModel) body.getUserData()).setBiteTime(System.currentTimeMillis());

    }

    /**
     * Creates the turtle's fixture.
     */
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

        leftSide.set(new Vector2(0, this.getHeight() * PIXEL_TO_METER - 10 * PIXEL_TO_METER),
                new Vector2(0, 0));

        fixtureDef.shape = leftSide;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("Turtle Left Side");

        leftSide.dispose();

        //Right Side Fixture

        EdgeShape rightSide = new EdgeShape();

        rightSide.set(new Vector2(this.getWidth() * PIXEL_TO_METER, this.getHeight() * PIXEL_TO_METER - 10 * PIXEL_TO_METER),
                new Vector2(this.getWidth() * PIXEL_TO_METER, 0));

        fixtureDef.shape = rightSide;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("Turtle Right Side");

        rightSide.dispose();
    }

    /**
     * Checks if a turtle is in biting range of a position according to it's velocity and distance.
     *
     * @param player2X The X coordinate of player two (or other position).
     *
     * @return true case is in range, false otherwise.
     */
    public boolean isInBitingRange(float player2X)
    {
        float distance = abs(this.getX() - player2X);

        if(distance / this.speed < 0.5f)
            return true;
        else
            return false;
    }
}
