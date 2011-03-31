package org.group20.sunstruck;

import org.group20.sunstruck.gameobject.GameObject;
import org.group20.sunstruck.world.map.segments.MapSegment;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class Main implements ApplicationListener {
	public static float bgScale = 1.0f;
	public static float bgSpeed = 3.14f;
	private MapSegment last;
	private MapSegment first;
	private SpriteBatch guiBatch;
	private SpriteBatch spriteBatch;
	private SpriteBatch backgroundBatch;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
	private double time = 0;
	private int bgIteration = 0;
	private boolean run = true;

	@Override
	public void create() {
//		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
//				+ ", surface created");
		
		Game.getInstance().initializePlayer();

		// Scales the width.
		float scale = (float) Gdx.graphics.getHeight()
				/ Gdx.graphics.getWidth();
		camera = new OrthographicCamera(7, 7 * scale);
		camera.position.set(0, 0, 0);
		bgScale = 7 * scale / 2;

		spriteBatch = new SpriteBatch();
		backgroundBatch = new SpriteBatch();
		guiBatch = new SpriteBatch();
		renderer = new Box2DDebugRenderer();

		Game.getInstance().start();
		first = Game.getInstance().getMap().getNext();
		last = Game.getInstance().getMap().getNext();
	}

	@Override
	public void render() {
		if (!run)
			return;
		
		//Background color.
		GL10 gl = Gdx.app.getGraphics().getGL10();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		time += Gdx.graphics.getDeltaTime();
		if (time >= 0.01) {
			Game.getInstance().getInput().update();
			Game.getInstance().getPlayer().update();
			time = 0;
			if (Game.getInstance().getInput().getHasFiredBomb())
				System.out.println("ohjoy");
			// Draw background
			drawBackground();
		}

		// Draw GUI objects.
		guiBatch.begin();
		for (Sprite sprite : Game.getInstance().getGui().getSpriteList())
			sprite.draw(guiBatch);
		guiBatch.end();

		// Update physics.
		Game.getInstance().getWorld().step(Gdx.app.getGraphics().getDeltaTime(), 8, 3);
		renderer.render(Game.getInstance().getWorld());

		// Update camera.
		camera.update();
		camera.apply(gl);

		// Draw game objects.
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		for (GameObject go : Game.getInstance().getGameObjectList()) {
			TextureRegion texture = go.getTexture();
			//System.out.println(go + " " + go.getTexture());
			float x, y, originX, originY, width, height, scaleX, scaleY, rotation;

			width = go.getWidth();
			height = go.getHeight();

			rotation = (float) (go.getBody().getAngle() * 180 / Math.PI);

			x = go.getBody().getPosition().x - width / 2;
			y = go.getBody().getPosition().y - height / 2;

			originX = width / 2;
			originY = height / 2;

			scaleX = 2;
			scaleY = 2;
			spriteBatch.draw(texture, x, y, originX,
					originY, width, height, scaleX, scaleY, rotation);
		}
		spriteBatch.end();
		renderer.render(Game.getInstance().getWorld());
	}

	/**
	 * pushes the current matrix, draws the backgrounds then pops the matrix.
	 */
	private void drawBackground() {
		TextureRegion rf = first.getTextureRegion();
		TextureRegion rl = last.getTextureRegion();
		float bgPosition =  bgIteration*bgSpeed;
		backgroundBatch.begin();
		backgroundBatch.draw(rf, -bgPosition, 0);
		backgroundBatch.draw(rl, -bgPosition+rf.getRegionWidth(), 0);
		backgroundBatch.end();
		bgIteration++;
		if (bgPosition > rf.getRegionWidth()-1) {
			first = last;
			last = Game.getInstance().getMap().getNext();
			bgIteration = 0;
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
	public void resize(int x, int y) {

	}

}
