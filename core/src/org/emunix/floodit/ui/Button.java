package org.emunix.floodit.ui;

import com.badlogic.gdx.graphics.Color;

public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Button(int x, int y, int width, int height, Color c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = c;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Color getColor() {
        return color;
    }
}
