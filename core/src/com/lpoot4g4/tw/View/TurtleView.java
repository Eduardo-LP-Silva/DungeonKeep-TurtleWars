package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lpoot4g4.tw.TurtleWars;
import com.badlogic.gdx.graphics.g2d.Animation;

public abstract class TurtleView extends EntityView
{
    /**
     * The animation used when the turtle is moving.
     */
    protected Animation<TextureRegion> walking;

    /**
     *  The animation timer.
     */
    protected float animationTimer = 0;

    /**
     * Constructs a turtle view.
     *
     * @param game The game.
     */
    public TurtleView(TurtleWars game) {
        super(game);
    }

    /**
     * Creates a sprite that represents the turtle.
     *
     * @param game The game.
     */
    public abstract Sprite createSprite(TurtleWars game);
}