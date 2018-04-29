package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TurtleView extends EntityView
{
    public TurtleView(GameView game)
    {
        super(game);
    }

    @Override
    public Sprite createSprite(GameView game)
    {
        Texture txt = new Texture("turtleCanion.png");
        Sprite s = new Sprite(txt, txt.getWidth(), txt.getHeight());

        return s;
    }
}
