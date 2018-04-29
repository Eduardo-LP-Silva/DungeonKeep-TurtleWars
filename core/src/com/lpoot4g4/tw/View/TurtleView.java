package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class TurtleView extends EntityView
{
    public TurtleView(TurtleWars game)
    {
        super(game);
    }

    public Sprite createSprite(TurtleWars game) //TODO Differentiante between types of turtles
    {
        Texture txt = new Texture("bazookaTurtle.png");

        Sprite s = new Sprite(txt, txt.getWidth(), txt.getHeight());

        return s;
    }
}
