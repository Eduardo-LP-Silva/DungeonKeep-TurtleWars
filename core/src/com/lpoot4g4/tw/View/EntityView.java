package com.lpoot4g4.tw.View;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class EntityView
{
    Sprite sprite;

    EntityView(GameView game)
    {
        sprite = createSprite(game);
    }

    public abstract Sprite createSprite(GameView game);

    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }
}
