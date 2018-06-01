package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class ProjectileView extends EntityView
{

    /**
     * Constructs a projectile view.
     *
     * @param game The game.
     */
    public ProjectileView(TurtleWars game)
    {
        super(game);
    }

    /**
     * Creates a sprite that represents the projectile.
     *
     * @param game The game.
     */
    public Sprite createSprite(TurtleWars game)
    {
        Texture tex = new Texture("projectile.png");

        return new Sprite(tex, tex.getWidth(), tex.getHeight());
    }
}
