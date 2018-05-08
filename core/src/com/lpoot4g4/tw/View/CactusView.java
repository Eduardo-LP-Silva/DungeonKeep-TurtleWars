package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class CactusView extends EntityView
{
    public CactusView(TurtleWars game)
    {
        super(game);
    }

    public Sprite createSprite(TurtleWars game)
    {
        Texture tex = game.getAssetManager().get("cactus.png", Texture.class);

        return new Sprite(tex, tex.getWidth(), tex.getHeight());
    }
}
