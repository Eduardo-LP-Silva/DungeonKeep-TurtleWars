package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.lpoot4g4.tw.TurtleWars;

public class lightTurtleView extends TurtleView
{
    public lightTurtleView(TurtleWars game)
    {
        super(game);

        Array<TextureRegion> frames = new Array<TextureRegion>();

        Texture walking_texture = game.getAssetManager().get("lightTurtleWalking.png", Texture.class);

        frames.add(new TextureRegion(walking_texture, 0, 0, sprite.getTexture().getWidth(),
                sprite.getTexture().getHeight()));

        frames.add(new TextureRegion(walking_texture, 89, 0, sprite.getTexture().getWidth(),
                sprite.getTexture().getHeight()));

        walking = new Animation(0.1f, frames);

        frames.clear();


    }

    @Override
    public Sprite createSprite(TurtleWars game)
    {
        return new Sprite(game.getAssetManager().get("bazookaTurtle.png", Texture.class));
    }
}
