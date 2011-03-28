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
	public static float bgSpeed = 0.1f;
	private Mesh mesh;
	private MapSegment last;
	private MapSegment first;
	private SpriteBatch guiBatch;
	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
	private double time = 0;
	private float xOffset = 0;
	private boolean run = true;

	@Override
	public void create() {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
				+ ", surface created");
		
		Game.getInstance().initializePlayer();

		// Scales the width.
		float scale = (float) Gdx.graphics.getHeight()
				/ Gdx.graphics.getWidth();
		camera = new OrthographicCamera(7, 7 * scale);
		camera.position.set(0, 0, 0);
		bgScale = 7 * scale / 2;

		spriteBatch = new SpriteBatch();
		guiBatch = new SpriteBatch();
		renderer = new Box2DDebugRenderer();

		Game.getInstance().start();
		first = Game.getInstance().getMap().getNext();
		last = Game.getInstance().getMap().getNext();

		/* mesh configuration START */
		if (mesh == null) {
			mesh = new Mesh(true, 4, 4, new VertexAttribute(Usage.Position, 3,
					"a_position"), new VertexAttribute(Usage.ColorPacked, 4,
					"a_color"), new VertexAttribute(Usage.TextureCoordinates,
					2, "a_texCoords"));

			mesh.setVertices(new float[] { -1.0f * bgScale, -1.0f * bgScale, 0,
					Color.WHITE.toFloatBits(), 0, 1, 1.0f * bgScale,
					-1.0f * bgScale, 0, Color.WHITE.toFloatBits(), 1, 1,
					1.0f * bgScale, 1.0f * bgScale, 0,
					Color.WHITE.toFloatBits(), 1, 0, -1.0f * bgScale,
					1.0f * bgScale, 0, Color.WHITE.toFloatBits(), 0, 0 });

			mesh.setIndices(new short[] { 0, 1, 2, 3 });
		}
		/* mesh configuration END */
	}

	@Override
	public void render() {
		if (!run)
			return;
		time += Gdx.graphics.getDeltaTime();
		if (time >= 0.01) {
			Game.getInstance().getInput().update();
			Game.getInstance().getPlayer().update();
			time = 0;
			if (Game.getInstance().getInput().getHasFiredBomb())
				System.out.println("ohjoy");
		}

		// Background color.
		GL10 gl = Gdx.app.getGraphics().getGL10();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Draw background
		drawBackground();

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
			System.out.println(go + " " + go.getTexture());
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
			spriteBatch.draw(new TextureRegion(texture), x, y, originX,
					originY, width, height, scaleX, scaleY, rotation);
		}
		spriteBatch.end();
		renderer.render(Game.getInstance().getWorld());
	}

	/**
	 * pushes the current matrix, draws the backgrounds then pops the matrix.
	 */
	private void drawBackground() {
		Gdx.gl11.glPushMatrix();
		Gdx.graphics.getGL11().glEnable(GL10.GL_TEXTURE_2D);

		Gdx.gl11.glTranslatef(-xOffset, 0, 0);
		first.getTexture().bind();
		mesh.render(GL10.GL_TRIANGLE_FAN, 0, 4);
		Gdx.gl11.glTranslatef(xOffset, 0, 0);

		Gdx.gl11.glTranslatef(bgScale * 2, 0, 0);
		Gdx.gl11.glTranslatef(-xOffset, 0, 0);
		last.getTexture().bind();
		mesh.render(GL10.GL_TRIANGLE_FAN, 0, 4);
		Gdx.gl11.glTranslatef(xOffset, 0, 0);

		Gdx.graphics.getGL11().glDisable(GL10.GL_TEXTURE_2D);
		Gdx.gl11.glPopMatrix();
		xOffset += bgSpeed;
		if (xOffset > bgScale * 2) {
			first = last;
			last = Game.getInstance().getMap().getNext();
			xOffset = 0;
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
