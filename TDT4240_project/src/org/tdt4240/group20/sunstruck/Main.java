package org.tdt4240.group20.sunstruck;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class Main implements ApplicationListener 
{
	float r = 1, g = 0, b = 0;
	private Game game = new Game();
	private double time = 0;
	
	private Mesh mesh; // test code
	private Texture texture; // test code
	
	@Override 
	public void create () {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId() + ", surface created");
		Gdx.input.setInputProcessor(game.getInput());
		time = System.currentTimeMillis(); // TODO replace this with a more accurate method
		game.start();
	
		/** test code START */
		if (mesh == null) {
	        mesh = new Mesh(true, 3, 3, 
	                new VertexAttribute(Usage.Position, 3, "a_position"),
	                new VertexAttribute(Usage.ColorPacked, 4, "a_color"),
	                new VertexAttribute(Usage.TextureCoordinates, 2, "a_texCoords"));

	        mesh.setVertices(new float[] { -0.5f, -0.5f, 0, Color.toFloatBits(255, 0, 0, 255), 0, 1,
	                                       0.5f, -0.5f, 0, Color.toFloatBits(0, 255, 0, 255), 1, 1,
	                                       0, 0.5f, 0, Color.toFloatBits(0, 0, 255, 255), 0.5f, 0 });
	                                       
	        mesh.setIndices(new short[] { 0, 1, 2 });

	        FileHandle imageFileHandle = Gdx.files.internal("data/placeholder.png"); 
	        texture = new Texture(imageFileHandle);
		}
		/** test code END */
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
		texture.bind();
		mesh.render(GL10.GL_TRIANGLES, 0 ,3);
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
