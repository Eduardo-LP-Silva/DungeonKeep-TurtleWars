package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.lpoot4g4.tw.TurtleWars;

public class lightTurtleView extends TurtleView
{
    /**
     * Class constructor.
     *
     * @param game The game itself.
     */
    public lightTurtleView(TurtleWars game)
    {
        super(game);

        Array<TextureRegion> frames = new Array<TextureRegion>();

        Texture walking_texture = game.getAssetManager().get("lightTurtle.png", Texture.class);

        frames.add(new TextureRegion(walking_texture, 88, 0, 88, 59));

        frames.add(new TextureRegion(walking_texture, 176, 0, 88, 59));

        walking = new Animation(0.1f, frames);

        frames.clear();
    }

    /**
     * Creates a sprite that represents the light turtle.
     *
     * @param game The game itself.
     */
    @Override
    public Sprite createSprite(TurtleWars game)
    {
        Texture walking_texture = game.getAssetManager().get("lightTurtle.png", Texture.class);

        return new Sprite(new TextureRegion(walking_texture, 0, 0, 88, 59));
    }
}
