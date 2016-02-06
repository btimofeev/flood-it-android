package org.emunix.floodit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import org.emunix.floodit.screens.GameScreen;

public class FloodIt extends Game {
	
	@Override
	public void create () {
		Gdx.graphics.setContinuousRendering(false);
		setScreen(new GameScreen());
	}

}
