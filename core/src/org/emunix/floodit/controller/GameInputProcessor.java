package org.emunix.floodit.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import org.emunix.floodit.model.Game;
import org.emunix.floodit.ui.Button;

public class GameInputProcessor implements InputProcessor {

    private final Game game;
    private final Camera camera;
    private final Button[] buttons;

    public GameInputProcessor(Game game, Camera camera, Button[] buttons) {
        this.game = game;
        this.camera = camera;
        this.buttons = buttons;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;

        if (game.getState() == Game.State.WIN || game.getState() == Game.State.LOSE) {
            game.newGame();
        }

        //Gdx.app.log("Flood-it", String.format("Click at %d, %d", screenX, screenY));
        Vector3 wc = camera.unproject(new Vector3(screenX, screenY, 0));
        //Gdx.app.log("Flood-it", String.format("Projected at %f, %f", wc.x, wc.y));
        for (Button but : buttons) {
            if (wc.x >= but.getX() && wc.x <= but.getX()+but.getWidth() &&
                    wc.y >= but.getY() && wc.y <= but.getY()+but.getHeight()) {
                game.chooseColor(but.getColor());
                break;
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
