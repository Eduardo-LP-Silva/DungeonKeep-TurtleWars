package com.lpoot4g4.tw.View;

import com.badlogic.gdx.ScreenAdapter;
import com.lpoot4g4.tw.Model.GameModel;
import com.lpoot4g4.tw.TurtleWars;

public class OptionsView extends ScreenAdapter
{
    private TurtleWars game;
    private GameModel gameModel;

    public OptionsView(TurtleWars g, GameModel gm)
    {
        game = g;
        gameModel = gm;
    }
}
