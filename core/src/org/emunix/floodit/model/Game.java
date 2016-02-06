package org.emunix.floodit.model;

import com.badlogic.gdx.graphics.Color;

public class Game {

    private Board board;

    private int boardWidth = 14;
    private int boardHeight = 14;
    private int numberColors = 6;
    private int turns;
    private int maxTurns = 25;
    private State state;

    public enum State {
        RUNNING,
        WIN,
        LOSE
    }

    public Game() {
        newGame();
    }

    public void newGame() {
        board = new Board(boardWidth, boardHeight, numberColors);
        state = State.RUNNING;
        turns = 0;
    }

    public void chooseColor(Color color) {
        if (color.equals(board.getChosenColor())) return;

        board.chooseColor(color);
        turns++;

        if (turns > maxTurns) {
            state = State.LOSE;
        } else if (board.isFilled()) {
            state = State.WIN;
        }
    }

    public Color[][] getBoard() {
        return board.getBoard();
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getNumberColors() {
        return numberColors;
    }

    public State getState() {
        return state;
    }

    public int getTurns() {
        return turns;
    }

    public int getMaxTurns() {
        return maxTurns;
    }
}
