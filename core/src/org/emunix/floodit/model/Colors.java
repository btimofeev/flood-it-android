package org.emunix.floodit.model;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

public class Colors {
    public static final Color BLUE = new Color(91/255f, 192/255f, 235/255f, 0);
    public static final Color RED = new Color(229/255f, 89/255f, 52/255f, 0);
    public static final Color GREEN = new Color(155/255f, 197/255f, 61/255f, 0);
    public static final Color YELLOW = new Color(253/255f, 231/255f, 76/255f, 0);
    public static final Color VIOLET = new Color(248/255f, 150/255f, 216/255f, 0);
    public static final Color DARKBLUE = new Color(36/255f, 123/255f, 160/255f,0);

    public static Color getRandomColor(int n) {
        Random rand = new Random();
        switch (rand.nextInt(n) + 1) {
            case 1: return BLUE;
            case 2: return RED;
            case 3: return GREEN;
            case 4: return YELLOW;
            case 5: return VIOLET;
            case 6: return DARKBLUE;
            default: throw new IllegalArgumentException();
        }
    }
}
