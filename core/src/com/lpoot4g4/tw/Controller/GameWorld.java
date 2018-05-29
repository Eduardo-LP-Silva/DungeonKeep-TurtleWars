package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.lpoot4g4.tw.Model.EntityModel;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.Model.PlatformModel;
import com.lpoot4g4.tw.Model.PowerUpModel;
import com.lpoot4g4.tw.Model.ProjectileModel;
import com.lpoot4g4.tw.Model.TurtleModel;

import static java.lang.Math.abs;


import java.util.ArrayList;
import java.util.Random;

import static com.lpoot4g4.tw.View.PlayView.PIXEL_TO_METER;

public class GameWorld implements ContactListener
{
    private World world;
    private TurtleBody player1;
    private TurtleBody player2;
    private PlatformBody floor;
    private PlatformBody ceiling;
    private PowerUpBody powerUp;
    private wallBody leftWall;
    private wallBody rightWall;
    private ArrayList<ProjectileBody> missiles;
    private GameModel gameModel;

    public GameWorld(GameModel gm)
    {
        world = new World(new Vector2(0, -9.8f), true);
        gameModel = gm;
        player1 = new TurtleBody(world, gameModel.getPlayer1());
        player2 = new TurtleBody(world, gameModel.getPlayer2());
        floor = new PlatformBody(world, gameModel.getFloor());
        ceiling = new PlatformBody(world, gameModel.getCeiling());
        leftWall = new wallBody(world, new PlatformModel(0,0));
        rightWall =  new wallBody(world, new PlatformModel(800,0));
        missiles = new ArrayList<ProjectileBody>();
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

    public ArrayList<ProjectileBody> getMissiles() {
        return missiles;
    }

    public World getWorld()
    {
        return world;
    }

    public void update(float delta)
    {
        gameModel.update();
        //player2Defend();
        //player2Attack();

        if(gameModel.getPowerUp().getEffect().toString().equals("Null"))
        {
            Random rand = new Random();
            gameModel.spawnPowerUp();

            Timer.schedule(new Timer.Task()
            {
                @Override
                public void run()
                {
                    powerUp = new PowerUpBody(world, gameModel.getPowerUp());
                }
            }, rand.nextInt(15) + 1);
        }

        world.step(1/60f, 6,2);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies)
        {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x / PIXEL_TO_METER,
                    body.getPosition().y / PIXEL_TO_METER);
        }

        updateTurtles();
    }

    public void player2Attack()
    {
        if(player1.getX() < player2.getX())
        {
            if(player2.isRetreating())
            {
                if(abs(player2.getX() - player1.getX()) * PIXEL_TO_METER > 1)
                    player2.setRetreating(false);
            }
            else
                player2.moveLeft();

            if(player2.isInBitingRange(player1.getX()))
            {
                player2.bite();
                player2.moveLeft();

                Timer.schedule(new Timer.Task()
                {
                    @Override
                    public void run()
                    {
                        player2.moveRight();
                        player2.setRetreating(true);
                    }
                }, 0.5f);
            }
            else
            {
                if(!gameModel.getPlayer2().isFiring())
                {
                    if(player1.getY() > player2.getY() + player2.getHeight() * PIXEL_TO_METER)
                        player2.jump();

                    if(player1.getY() >= player2.getY() && player1.getY() <= player2.getY() + player2.getHeight() * PIXEL_TO_METER)
                        fireTurtle2();
                }
            }
        }
        else
        {
            if(player2.isRetreating())
            {
                if(abs(player2.getX() - player1.getX()) * PIXEL_TO_METER > 1)
                    player2.setRetreating(false);
            }
            else
                player2.moveLeft();

            if(player2.isInBitingRange(player1.getX()))
            {
                player2.bite();
                player2.moveRight();

                Timer.schedule(new Timer.Task()
                {
                    @Override
                    public void run()
                    {
                        player2.moveLeft();
                        player2.setRetreating(true);
                    }
                }, 0.5f);
            }
            else
            {
                if(!gameModel.getPlayer2().isFiring())
                {
                    if(player1.getY() > player2.getY() + player2.getHeight() * PIXEL_TO_METER)
                        player2.jump();

                    if(player1.getY() >= player2.getY() && player1.getY() <= player2.getY() + player2.getHeight() * PIXEL_TO_METER)
                        fireTurtle2();
                }


            }
        }
    }

    void player2Defend()
    {
        if(gameModel.getPlayer1().isFiring() &&
                (player2.getY() >= player1.getY() &&
                        player2.getY() <= player1.getY() + player1.getHeight() * PIXEL_TO_METER))
            player2.jump();

        if(player1.isInBitingRange(player2.getX()) && gameModel.getPlayer1().isBiting())
            if(player1.getX() < player2.getX())
            {
                if(rightWall.getX() - player2.getX() < 0.02f) // < ? >
                    player2.moveRight();
                else
                {
                    if(!gameModel.getPlayer1().isJumping())
                    {
                        player2.jump();
                        player2.moveLeft();
                    }
                    else
                        player2.moveLeft();
                }
            }
            else
            {
                if(player2.getX() > 0.02f)
                    player2.moveLeft();
                else
                {
                    if(!gameModel.getPlayer1().isJumping())
                    {
                        player2.jump();
                        player2.moveRight();
                    }
                    else
                        player2.moveRight();
                }
            }

        if(player1.getY() > player2.getY())
            if(abs(player1.getX() - player2.getX()) * PIXEL_TO_METER < 0.03f)
            {
                if(player1.getX() + player1.getWidth() * PIXEL_TO_METER < player2.getX())
                {
                    if((rightWall.getX() - (player2.getX() + player2.getWidth() * PIXEL_TO_METER)) * PIXEL_TO_METER > 0.01f)
                        player2.moveRight();
                    else
                        player2.moveLeft();
                }
                else
                    if(player1.getX() > player2.getX() + player2.getWidth() * PIXEL_TO_METER)
                    {
                        if(player2.getX() > 0.01f)
                            player2.moveLeft();
                        else
                            player2.moveRight();
                    }
                    else
                    {

                        if(player2.getX() < rightWall.getX() / 2)
                            player2.moveRight();
                        else
                            player2.moveLeft();
                    }
            }
    }

    public void removePowerUp()
    {
        gameModel.getPowerUp().setEffect(PowerUpModel.Effect.Null);
        gameModel.getPowerUp().setFlaggedForRemoval(true);
    }

    public void updateTurtles()
    {
        // Turtle 1

        if(player1.getBody().getLinearVelocity().x > 0)
            ((TurtleModel) player1.getBody().getUserData()).setCurrentState(TurtleModel.State.RunningRight);
        else
            if(player1.getBody().getLinearVelocity().x < 0)
                ((TurtleModel) player1.getBody().getUserData()).setCurrentState(TurtleModel.State.RunningLeft);
            else
                ((TurtleModel) player1.getBody().getUserData()).setCurrentState(TurtleModel.State.Standing);

        // Turtle 2

        if(player2.getBody().getLinearVelocity().x > 0)
            ((TurtleModel) player2.getBody().getUserData()).setCurrentState(TurtleModel.State.RunningRight);
        else
            if(player2.getBody().getLinearVelocity().x < 0)
                ((TurtleModel) player2.getBody().getUserData()).setCurrentState(TurtleModel.State.RunningLeft);
            else
                ((TurtleModel) player2.getBody().getUserData()).setCurrentState(TurtleModel.State.Standing);
    }

    public void FireTurtle1()
    {
        float speed;

        ProjectileModel missile;

        if(player1.getX() < player2.getX())
        {
            speed = ProjectileModel.TRAVEL_SPEED;
            missile = new ProjectileModel(gameModel.getPlayer1().getX() + player1.getWidth() + 10,
                    gameModel.getPlayer1().getY() + player1.getHeight());

            missile.setBackwards(false);
        }
        else
        {
            speed = - ProjectileModel.TRAVEL_SPEED;
            missile = new ProjectileModel(gameModel.getPlayer1().getX() - 50,
                    gameModel.getPlayer1().getY() + player1.getHeight());

            missile.setBackwards(true);
        }

        gameModel.addMissile(missile);

        ProjectileBody missileBody = new ProjectileBody(world, missile);

        missiles.add(missileBody);

        missileBody.move(speed);

        gameModel.getPlayer1().setFiring(true);

        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                gameModel.getPlayer1().setFiring(false);
            }
        }, 2f);
    }

    public void fireTurtle2()
    {
        float speed;

        ProjectileModel missile;

        if(player2.getX() < player1.getX())
        {
            speed = ProjectileModel.TRAVEL_SPEED;
            missile = new ProjectileModel(gameModel.getPlayer2().getX() + player2.getWidth() + 10,
                    gameModel.getPlayer2().getY() + player2.getHeight());

            missile.setBackwards(false);
        }
        else
        {
            speed = - ProjectileModel.TRAVEL_SPEED;
            missile = new ProjectileModel(gameModel.getPlayer2().getX() - 50,
                    gameModel.getPlayer2().getY() + player2.getHeight());

            missile.setBackwards(true);
        }

        gameModel.addMissile(missile);

        ProjectileBody missileBody = new ProjectileBody(world, missile);

        missiles.add(missileBody);

        missileBody.move(speed);

        gameModel.getPlayer2().setFiring(true);

        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                gameModel.getPlayer2().setFiring(false);
            }
        }, 2f);
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
                if((turtleFxtA.getUserData().equals("Turtle Left Side"))
                        || (turtleFxtA.getUserData().equals("Turtle Right Side")))
                turtleModelB.inflictDamage(turtleModelA.getMelee_damage());

            if(turtleModelB.isBiting())
                if((fxtB.getUserData().equals("Turtle Left Side"))
                        || (fxtB.getUserData().equals("Turtle Right Side")))
                    turtleModelA.inflictDamage(turtleModelB.getMelee_damage());

            if(turtleFxtA.getUserData().equals("Turtle Bottom") && fxtB.getUserData().equals("Turtle Body")
                    && turtleModelA.getY() > turtleModelB.getY())
            {
                turtleModelB.inflictDamage(turtleModelA.getStomp_damage());
                turtleFxtA.getBody().applyLinearImpulse(new Vector2(0, 60), turtleFxtA.getBody().getWorldCenter(), true);
                turtleModelA.setJumping(true);
            }
            else
                if(fxtB.getUserData().equals("Turtle Bottom") && turtleFxtA.getUserData().equals("Turtle Body")
                        && turtleModelB.getY() > turtleModelA.getY())
                {
                    turtleModelA.inflictDamage((turtleModelB.getStomp_damage()));
                    fxtB.getBody().applyLinearImpulse(new Vector2(0, 60), fxtB.getBody().getWorldCenter(), true);
                    turtleModelB.setJumping(true);
                }

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

        if(fxtB.getBody().getUserData() instanceof ProjectileModel)
        {
            ProjectileModel pm = (ProjectileModel) fxtB.getBody().getUserData();
            turtleModelA.inflictDamage(ProjectileModel.BASE_DAMAGE);
            pm.setForRemoval();

            return;
        }

        if(fxtB.getBody().getUserData() instanceof PowerUpModel)
        {
            String effect = ((PowerUpModel) fxtB.getBody().getUserData()).getEffect().toString();

            if(effect.equals("Health"))
                turtleModelA.addHealth(25);
            else
                if(effect.equals("Damage") && !turtleModelA.isBuffed())
                {
                    turtleModelA.setMelee_damage(turtleModelA.getMelee_damage() + 15);
                    turtleModelA.setStomp_damage(turtleModelA.getStomp_damage() + 15);
                    turtleModelA.setBuffed(true);
                }

                removePowerUp();
        }
    }

    public void bulletContact(Contact contact)
    {
        Fixture bulletFxt1 = contact.getFixtureA(), fxtB = contact.getFixtureB();
        ProjectileModel bullet1 = (ProjectileModel) bulletFxt1.getBody().getUserData();

        if(fxtB.getBody().getUserData() instanceof TurtleModel)
        {
            TurtleModel turtleModel = (TurtleModel) fxtB.getBody().getUserData();

            turtleModel.inflictDamage(ProjectileModel.BASE_DAMAGE);
        }
        else
            if(fxtB.getBody().getUserData() instanceof  PowerUpModel)
                removePowerUp();

        bullet1.setForRemoval();
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
                turtleModel.setJumping(false);
            }
        }

        if(fxtB.getBody().getUserData() instanceof ProjectileModel)
        {
            ProjectileModel pm = (ProjectileModel) fxtB.getBody().getUserData();

            pm.setForRemoval();
        }
    }

    public void powerUpContact(Contact contact)
    {
        PowerUpModel powerUpM = (PowerUpModel) contact.getFixtureA().getBody().getUserData();
        Fixture fxtB = contact.getFixtureB();

        if(fxtB.getBody().getUserData() instanceof TurtleModel)
        {
            String effect = powerUpM.getEffect().toString();
            TurtleModel turtleModelB = (TurtleModel) fxtB.getBody().getUserData();

            if(effect.equals("Health"))
                turtleModelB.addHealth(25);
            else
                if(effect.equals("Damage") && !turtleModelB.isBuffed())
                {
                    turtleModelB.setMelee_damage(turtleModelB.getMelee_damage() + 15);
                    turtleModelB.setStomp_damage(turtleModelB.getStomp_damage() + 15);
                    turtleModelB.setBuffed(true);
                }

            removePowerUp();
        }
        else
            if(fxtB.getBody().getUserData() instanceof ProjectileModel)
                removePowerUp();
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
            else
                if(fixtureA.getBody().getUserData() instanceof ProjectileModel)
                    bulletContact(contact);
                else
                    if(fixtureA.getBody().getUserData() instanceof PowerUpModel)
                        powerUpContact(contact);
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

    /**
     * Removes objects that have been flagged for removal on the
     * previous step.
     */
    public void removeBody(Body body)
    {
        world.destroyBody(body);
    }

    /**
     * Removes objects that have been flagged for removal on the
     * previous step.
     */
    public void removeFlagged()
    {
        Array<Body> bodies = new Array<Body>();

        world.getBodies(bodies);

        for (Body body : bodies)
        {
            if (((EntityModel)body.getUserData()).isFlaggedForRemoval())
            {
                //gameModel.getMissiles().remove((EntityModel) body.getUserData());
                world.destroyBody(body);
            }
        }
    }
}
