package com.lpoot4g4.tw.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.lpoot4g4.tw.TurtleWars;

public class MenuView
{
    private TurtleWars game;
    private static int SPACING = 20;
    private Sprite newGameBtn;
    private Sprite optionsBtn;
    private Sprite exitBtn;

    public MenuView(TurtleWars g)
    {
        game = g;
    }

    public void loadAssets()
    {
        this.game.getAssetManager().load("menuBackground.png", Texture.class);
        this.game.getAssetManager().load("newGameBtn.png", Texture.class);
        this.game.getAssetManager().load("optionsBtn.png", Texture.class);
        this.game.getAssetManager().load("exitBtn.png", Texture.class);
        this.game.getAssetManager().finishLoading();

        newGameBtn = new Sprite(this.game.getAssetManager().get("newGameBtn.png", Texture.class));
        optionsBtn = new Sprite(this.game.getAssetManager().get("optionsBtn.png", Texture.class));
        exitBtn = new Sprite(this.game.getAssetManager().get("exitBtn.png", Texture.class));
    }

    public void render(float delta)
    {
        Texture menuBackground = game.getAssetManager().get("menuBackground.png", Texture.class);

        game.getBatch().begin();
        game.getBatch().draw(menuBackground, 0, 0, TurtleWars.WIDTH, TurtleWars.HEIGHT);
        newGameBtn.setPosition(newGameBtn.getHeight() / 2,  TurtleWars.HEIGHT - newGameBtn.getHeight() - SPACING);
        newGameBtn.draw(game.getBatch());
        game.getBatch().end();
    }

    public void unloadAssets()
    {
        this.game.getAssetManager().unload("menuBackground.png");
        this.game.getAssetManager().unload("newGameBtn.png");
        this.game.getAssetManager().unload("optionsBtn.png");
        this.game.getAssetManager().unload("exitBtn.png");
    }
}
