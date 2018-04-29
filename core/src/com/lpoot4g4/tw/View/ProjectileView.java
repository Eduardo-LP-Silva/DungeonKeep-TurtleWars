package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class ProjectileView extends EntityView
{
    public ProjectileView(TurtleWars game)
    {
        super(game);
    }

    public Sprite createSprite(TurtleWars game)
    {
        Texture tex = new Texture("projectile.png");

        return new Sprite(tex, tex.getWidth(), tex.getHeight());
    }
}
