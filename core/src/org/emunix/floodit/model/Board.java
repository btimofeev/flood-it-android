package org.emunix.floodit.model;

import com.badlogic.gdx.graphics.Color;

public class Board {

    private final int WIDTH;
    private final int HEIGHT;
    private final int NUMBER_COLORS;

    private final Color[][] board;

    private Color chosenColor;

    public Board(int width, int height, int numberColors) {
        WIDTH = width;
        HEIGHT = height;
        NUMBER_COLORS = numberColors;
        board = new Color[WIDTH][HEIGHT];
        Init();
    }

    private void Init() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                board[i][j] = org.emunix.floodit.model.Colors.getRandomColor(NUMBER_COLORS);
            }
        }
        chosenColor = board[0][0];
    }

    public void chooseColor(Color color) {
        if (!color.equals(chosenColor)) {
            changeColor(0, 0, color);
            chosenColor = color;
        }
    }

    private void changeColor(int x, int y, Color color) {
        board[x][y] = color;
        if (x - 1 >= 0 && board[x - 1][y].equals(chosenColor))
            changeColor(x - 1, y, color);
        if (x + 1 < WIDTH && board[x + 1][y].equals(chosenColor))
            changeColor(x + 1, y, color);
        if (y - 1 >= 0 && board[x][y - 1].equals(chosenColor))
            changeColor(x, y - 1, color);
        if (y + 1 < HEIGHT && board[x][y + 1].equals(chosenColor))
            changeColor(x, y + 1, color);
    }

    public boolean isFilled() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (!board[i][j].equals(chosenColor))
                    return false;
            }
        }
        return true;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getNumberColors() {
        return NUMBER_COLORS;
    }

    public Color getChosenColor() {
        return chosenColor;
    }

    public Color[][] getBoard() {
        return board;
    }
}
