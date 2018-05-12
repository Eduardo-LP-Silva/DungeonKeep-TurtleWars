package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lpoot4g4.tw.Model.EntityModel;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.Model.PlatformModel;
import com.lpoot4g4.tw.Model.TurtleModel;

import java.util.ArrayList;
import static com.lpoot4g4.tw.View.PlayView.PIXEL_TO_METER;

public class GameWorld implements ContactListener
{
    private ArrayList<CactusBody> cacti;
    private World world;
    private TurtleBody player1;
    private TurtleBody player2;
    private PlatformBody floor;
    private wallBody leftWall;
    private wallBody rightWall;
    private GameModel gameModel;

    public GameWorld(GameModel gm)
    {
        world = new World(new Vector2(0, -9.8f), true);
        gameModel = gm;
        player1 = new TurtleBody(world, gameModel.getPlayer1());
        player2 = new TurtleBody(world, gameModel.getPlayer2());
        cacti = new ArrayList<CactusBody>();
        floor = new PlatformBody(world, gameModel.getFloor());
        leftWall = new wallBody(world, new PlatformModel(0,0));
        rightWall =  new wallBody(world, new PlatformModel(800,0));
        world.setContactListener(this);
    }

    public TurtleBody getPlayer1()
    {
        return player1;
    }

    public TurtleBody getPlayer2()
    {
        return player2;
    }

    public World getWorld()
    {
        return world;
    }

    public void update(float delta)
    {
        gameModel.update();

        world.step(1/60f, 6,2);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies)
        {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x / PIXEL_TO_METER,
                    body.getPosition().y / PIXEL_TO_METER);
        }
    }

    public void turtleContact(Contact contact)
    {
        Fixture turtleFxtA = contact.getFixtureA(), fxtB = contact.getFixtureB();
        TurtleModel turtleModelA = (TurtleModel) turtleFxtA.getBody().getUserData();

        if(turtleFxtA.getUserData().equals("Turtle Bottom"))
            turtleModelA.setJumping(false);

        if(fxtB.getBody().getUserData() instanceof TurtleModel)
        {
            TurtleModel turtleModelB = (TurtleModel) fxtB.getBody().getUserData();

            if(turtleModelA.isBiting())
                if((turtleFxtA.getUserData().equals("Turtle Left Side") && turtleModelA.getX() > turtleModelB.getX())
                        || (turtleFxtA.getUserData().equals("Turtle Right Side") && turtleModelA.getX() < turtleModelB.getX()))
                turtleModelB.inflictDamage(turtleModelA.getMelee_damage());

            if(turtleModelB.isBiting())
                if((fxtB.getUserData().equals("Turtle Left Side") && turtleModelB.getX() > turtleModelA.getX())
                        || (fxtB.getUserData().equals("Turtle Right Side") && turtleModelB.getX() < turtleModelA.getX()))
                    turtleModelA.inflictDamage(turtleModelB.getMelee_damage());

            return;
        }

        if(fxtB.getUserData().equals("Wall"))
        {
            if(turtleFxtA.getUserData().equals("Turtle Left Side") || turtleFxtA.getUserData().equals("Turtle Right Side"))
            {
                turtleModelA.setJumping(false);
            }

            return;
        }

    }

    public void wallContact(Contact contact)
    {
        Fixture wallFxtA = contact.getFixtureA(), fxtB = contact.getFixtureB();

        if(fxtB.getBody().getUserData() instanceof TurtleModel)
        {
            TurtleModel turtleModel = (TurtleModel) fxtB.getBody().getUserData();

            if(fxtB.getUserData().equals("Turtle Bottom"))
                turtleModel.setJumping(false);

            if(fxtB.getUserData().equals("Turtle Left Side") || fxtB.getUserData().equals("Turtle Right Side"))
            {
                System.out.print("asd\n");
                turtleModel.setJumping(false);
            }
        }
    }

    @Override
    public void beginContact(Contact contact)
    {
        Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

        if(fixtureA.getBody().getUserData() instanceof TurtleModel)
            turtleContact(contact);
        else
            if(fixtureA.getUserData().equals("Wall"))
                wallContact(contact);

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
