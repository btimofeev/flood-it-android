package org.emunix.floodit.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import org.emunix.floodit.ui.Button;
import org.emunix.floodit.model.Game;

public class GameRenderer {

    private final FitViewport viewport;
    private Game game;
    private Button[] buttons;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

    public static final int TILE_SIZE = 35;

    public GameRenderer(Game game, Button[] buttons) {
        this.game = game;
        this.buttons = buttons;
        font.setColor(Color.WHITE);

        camera = new OrthographicCamera();
        viewport = new FitViewport(540, 960, camera);
    }

    public void render(float delta) {
        camera.update();

        Gdx.gl.glClearColor(45/255f, 45/255f, 45/255f, 1); // #2D2D2D
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(camera.combined);
        drawBoard();
        drawButtons();
        drawText();
    }

    private void drawText() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, String.format("Turns: %d/%d", game.getTurns(), game.getMaxTurns()), 100, 920);

        if (game.getState() == Game.State.WIN) {
            font.draw(batch, "You won!", 135, 620);
        }

        if (game.getState() == Game.State.LOSE) {
            font.draw(batch, "You lost!", 135, 620);
        }

        batch.end();
    }

    private void drawBoard() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Color[][] board = game.getBoard();
        for (int i = 0; i < game.getBoardHeight(); i++) {
            for (int j = 0; j < game.getBoardWidth(); j++) {
                shapeRenderer.setColor(board[i][j]);
                shapeRenderer.rect(25 + (j*TILE_SIZE), 810 - (i*TILE_SIZE), TILE_SIZE, TILE_SIZE);
            }
        }
        shapeRenderer.end();
    }

    private void drawButtons() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (org.emunix.floodit.ui.Button button : buttons) {
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(button.getX() + button.getWidth()/2, button.getY() + button.getHeight()/2, button.getWidth()/2, 40);
            shapeRenderer.setColor(button.getColor());
            shapeRenderer.circle(button.getX() + button.getWidth()/2, button.getY() + button.getHeight()/2, button.getWidth()/2-2, 40);
        }
        shapeRenderer.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
