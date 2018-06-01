package com.lpoot4g4.tw.View;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoot4g4.tw.TurtleWars;

public abstract class EntityView
{
    /**
     * The sprite representing this entity view.
     */
    protected Sprite sprite;

    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public EntityView(TurtleWars game)
    {
        sprite = createSprite(game);
    }

    /**
     * Returns the sprite associated with this view.
     *
     * @return The sprite associated with this view.
     */
    public Sprite getSprite()
    {
        return sprite;
    }

    /**
     * Abstract method that creates the view sprite. Concrete
     * implementation should extend this method to create their
     * own sprites.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this view.
     */
     public abstract Sprite createSprite(TurtleWars game);

    /**
     * Draws the sprite from this view using a sprite batch.
     *
     * @param batch The sprite batch to be used for drawing.
     */
     public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }
}
