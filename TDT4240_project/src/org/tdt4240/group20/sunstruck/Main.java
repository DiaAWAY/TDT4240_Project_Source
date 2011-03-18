package org.tdt4240.group20.sunstruck;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class Main implements ApplicationListener 
{
	float r = 1, g = 0, b = 0;
	private Game game = new Game();
	private double time = 0;
	
	@Override 
	public void create () {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId() + ", surface created");
		Gdx.input.setInputProcessor(game.getInput());
		time = System.currentTimeMillis(); // TODO replace this with a more accurate method
		game.start();
	}

	@Override 
	public void render () {
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// UPDATE THE WORLD
		if ((System.currentTimeMillis() - time) > 1000/game.getUpdateRate()) {
			game.update();
			time = System.currentTimeMillis();
		}
		// FETCH THE WORLD DATA
		//game.getWorld().getDrawables();
	}

	@Override 
	public void dispose () {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId() + ", application destroyed");
	}


	@Override 
	public void pause () {
	}

	@Override 
	public void resume () {
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

}
