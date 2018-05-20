package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoot4g4.tw.TurtleWars;

public class ProjectileView extends EntityView
{

    public ProjectileView(TurtleWars game)
    {
        super(game);
    }
    public Sprite backwardsProjectile;

    public Sprite createSprite(TurtleWars game)
    {
        Texture tex = new Texture("projectile.png");
        Texture back_tex = game.getAssetManager().get("projectileBackwards.png", Texture.class);

        backwardsProjectile = new Sprite(back_tex, back_tex.getWidth(), back_tex.getHeight());

        return new Sprite(tex, tex.getWidth(), tex.getHeight());
    }

    public Sprite getBackwardsProjectile() {
        return backwardsProjectile;
    }
}
