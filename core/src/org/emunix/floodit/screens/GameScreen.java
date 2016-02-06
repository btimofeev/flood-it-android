package org.emunix.floodit.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import org.emunix.floodit.controller.GameInputProcessor;
import org.emunix.floodit.model.Colors;
import org.emunix.floodit.model.Game;
import org.emunix.floodit.view.GameRenderer;

public class GameScreen implements Screen {

    private Game game;
    private GameRenderer renderer;
    private GameInputProcessor inputProcessor;

    private org.emunix.floodit.ui.Button[] buttons = new org.emunix.floodit.ui.Button[]{
            new org.emunix.floodit.ui.Button(75, 210, 100, 100, Colors.BLUE),
            new org.emunix.floodit.ui.Button(220, 210, 100, 100, Colors.RED),
            new org.emunix.floodit.ui.Button(365, 210, 100, 100, Colors.GREEN),
            new org.emunix.floodit.ui.Button(75, 80, 100, 100, Colors.YELLOW),
            new org.emunix.floodit.ui.Button(220, 80, 100, 100, Colors.VIOLET),
            new org.emunix.floodit.ui.Button(365, 80, 100, 100, Colors.DARKBLUE)
    };

    public GameScreen() {
        game = new Game();
        renderer = new GameRenderer(game, buttons);
        inputProcessor = new GameInputProcessor(game, renderer.getCamera(), buttons);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
