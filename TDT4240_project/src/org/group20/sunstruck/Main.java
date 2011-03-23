package org.group20.sunstruck;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class Main implements ApplicationListener {
	float r = 1, g = 0, b = 0;
	private double time = 0;
	private boolean run = true;

	private Mesh mesh; // test code
	private Texture texture; // test code

	@Override
	public void create() {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
				+ ", surface created");
		time = System.currentTimeMillis(); // TODO replace this with a more
											// accurate method
		Game.getInstance().start();

		/** test code START */
		if (mesh == null) {
			mesh = new Mesh(true, 4, 4, new VertexAttribute(Usage.Position, 3,
					"a_position"), new VertexAttribute(Usage.ColorPacked, 4,
					"a_color"), new VertexAttribute(Usage.TextureCoordinates,
					2, "a_texCoords"));

			mesh.setVertices(new float[] { -0.5f, -0.5f, 0,
					Color.WHITE.toFloatBits(), 0, 1, 0.5f, -0.5f, 0,
					Color.WHITE.toFloatBits(), 1, 1, 0.5f, 0.5f, 0,
					Color.WHITE.toFloatBits(), 1, 0, -0.5f, 0.5f, 0,
					Color.WHITE.toFloatBits(), 0, 0 });

			mesh.setIndices(new short[] { 0, 1, 2, 3 });

			FileHandle imageFileHandle = Gdx.files
					.internal("data/placeholder.png");
			texture = new Texture(imageFileHandle);
			texture.bind();
			Gdx.graphics.getGL10().glEnable(GL10.GL_TEXTURE_2D);
		}
		/** test code END */
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 0, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// UPDATE THE WORLD
		if (run
				&& (System.currentTimeMillis() - time) > 1000 / Game
						.getInstance().getUpdateRate()) {
			Game.getInstance().update();
			time = System.currentTimeMillis();
		}
		// FETCH THE WORLD DATA
		// game.getWorld().getDrawables();

		mesh.render(GL10.GL_TRIANGLE_FAN, 0, 4);
	}

	@Override
	public void dispose() {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
				+ ", application destroyed");
	}

	@Override
	public void pause() {
		run = false;
		// store data here
	}

	@Override
	public void resume() {
		run = true;
		// reload data?
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

}
