package org.group20.sunstruck;

import org.group20.sunstruck.world.map.segments.MapSegment;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class Main implements ApplicationListener {
	private float r = 1, g = 0, b = 0;
	private double time = 0;
	private boolean run = true;
	private float yOffset = 0;
	private MapSegment first;
	private MapSegment last;
	private Mesh mesh; // test code
//	private Texture texture; // test code

	@Override
	public void create() {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
				+ ", surface created");
		Gdx.input.setInputProcessor(Game.getInstance().getInput());
		time = System.currentTimeMillis(); // TODO replace this with a more
											// accurate method
		Game.getInstance().start();
		first = Game.getInstance().getMap().getNext();
		last = Game.getInstance().getMap().getNext();

		/** test code START */
		if (mesh == null) {
			mesh = new Mesh(true, 4, 4, new VertexAttribute(Usage.Position, 3,
					"a_position"), new VertexAttribute(Usage.ColorPacked, 4,
					"a_color"), new VertexAttribute(Usage.TextureCoordinates,
					2, "a_texCoords"));

			mesh.setVertices(new float[] { -1.0f, -1.0f, 0,
					Color.WHITE.toFloatBits(), 0, 1, 1.0f, -1.0f, 0,
					Color.WHITE.toFloatBits(), 1, 1, 1.0f, 1.0f, 0,
					Color.WHITE.toFloatBits(), 1, 0, -1.0f, 1.0f, 0,
					Color.WHITE.toFloatBits(), 0, 0 });

			mesh.setIndices(new short[] { 0, 1, 2, 3 });
			
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
		Gdx.gl11.glPushMatrix();
		Gdx.graphics.getGL11().glEnable(GL10.GL_TEXTURE_2D);

		Gdx.gl11.glTranslatef(0, -yOffset, 0);
		first.getTexture().bind();
		mesh.render(GL10.GL_TRIANGLE_FAN, 0, 4);
		Gdx.gl11.glTranslatef(0, yOffset, 0);

		Gdx.gl11.glTranslatef(0, 2.0f, 0);
		Gdx.gl11.glTranslatef(0, -yOffset, 0);
		last.getTexture().bind();
		mesh.render(GL10.GL_TRIANGLE_FAN, 0, 4);
		Gdx.gl11.glTranslatef(0, yOffset, 0);

		Gdx.graphics.getGL11().glDisable(GL10.GL_TEXTURE_2D);
		Gdx.gl11.glPopMatrix();
		yOffset += 0.01;
		if (yOffset > 2) {
			MapSegment temp = last;
			first = temp;
			last = Game.getInstance().getMap().getNext();
			yOffset = 0;
		}
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
