package org.group20.sunstruck;

import java.util.Iterator;

import org.group20.sunstruck.gameobject.GameObject;
import org.group20.sunstruck.gui.GUI;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Main implements ApplicationListener {
	// The width and height of the orthographical, camera
	public static final float CAMERA_WIDTH = 10;
	public static float bgScale = 1.0f;
	public static float bgSpeed = 2.0f;
	public static Body eastBorder;
	public static Body northBorder;
	public static Body westBorder;
	public static Body southBorder;
	private TextureRegion lastBg;
	private TextureRegion firstBg;
	private SpriteBatch guiBatch;
	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
	private int bgIteration = 0;
	private float time = 0;
	private boolean run = true;
	private Matrix4 normalProjectionMatrix = new Matrix4();
	private Matrix4 rotatedMatrix = new Matrix4();
	private Matrix4 notRotatedMatrix = new Matrix4();

	@Override
	public void create() {
		// Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
		// + ", surface created");
		Game.getInstance().initializePlayer();
		// Scales the height.
		float scale = (float) Gdx.graphics.getHeight()
				/ Gdx.graphics.getWidth();
		bgScale = CAMERA_WIDTH * scale / 2;

		camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * scale);
		camera.position.set(0, 0, 0);

		// East border.
		PolygonShape eastBorderPoly = new PolygonShape();
		eastBorderPoly.setAsBox(0, CAMERA_WIDTH * scale);

		BodyDef eastBorderDef = new BodyDef();
		eastBorderDef.position.x = CAMERA_WIDTH * 2;
		eastBorderDef.position.y = 0;
		eastBorderDef.type = BodyType.StaticBody;

		eastBorder = Game.getInstance().getWorld().createBody(eastBorderDef);
		eastBorder.createFixture(eastBorderPoly, 10);
		eastBorderPoly.dispose();

		// West border
		PolygonShape westBorderPoly = new PolygonShape();
		westBorderPoly.setAsBox(0, CAMERA_WIDTH * scale);

		BodyDef westBorderDef = new BodyDef();
		westBorderDef.position.x = -CAMERA_WIDTH * 2;
		westBorderDef.position.y = 0;
		westBorderDef.type = BodyType.StaticBody;

		westBorder = Game.getInstance().getWorld().createBody(westBorderDef);
		westBorder.createFixture(westBorderPoly, 10);
		westBorderPoly.dispose();

		// North border
		PolygonShape northBorderPoly = new PolygonShape();
		northBorderPoly.setAsBox(CAMERA_WIDTH * 2, 0);

		BodyDef northBorderDef = new BodyDef();
		northBorderDef.position.x = 0;
		northBorderDef.position.y = CAMERA_WIDTH * scale;
		northBorderDef.type = BodyType.StaticBody;

		northBorder = Game.getInstance().getWorld().createBody(northBorderDef);
		northBorder.createFixture(northBorderPoly, 10);
		northBorderPoly.dispose();

		// North border
		PolygonShape southBorderPoly = new PolygonShape();
		southBorderPoly.setAsBox(CAMERA_WIDTH * 2, 0);

		BodyDef southBorderDef = new BodyDef();
		southBorderDef.position.x = 0;
		southBorderDef.position.y = -CAMERA_WIDTH * scale;
		southBorderDef.type = BodyType.StaticBody;

		southBorder = Game.getInstance().getWorld().createBody(southBorderDef);
		southBorder.createFixture(southBorderPoly, 10);
		southBorderPoly.dispose();

		spriteBatch = new SpriteBatch();
		normalProjectionMatrix.set(spriteBatch.getProjectionMatrix());

		notRotatedMatrix.set(spriteBatch.getTransformMatrix());

		rotatedMatrix.set(notRotatedMatrix.getValues());
		rotatedMatrix.setToRotation(new Vector3(0, 0, 1), -90);
		System.out.println(rotatedMatrix);

		renderer = new Box2DDebugRenderer();

		Game.getInstance().start();
		firstBg = Game.getInstance().getMap().getNext();
		lastBg = Game.getInstance().getMap().getNext();
	}

	@Override
	public void render() {
		if (!run)
			return;

		Gdx.gl.glClearColor((float) Math.random(), 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();

		// Update game objects
		Game.getInstance().update();

		if (Menu.isActive) {
			System.out.println("I render: " + Menu.isActive);
			Game.getInstance().getMenu().update();
			drawMenu();

			if (GUI.isHelpActive) {
				drawHelp();
			}
			spriteBatch.end();
			return;
		}

		// Draw background
		drawBackground();
		// I have no idea why I have to do this:
		Game.getInstance().getGui().getControlSpriteList().get(1)
				.draw(spriteBatch);

		if (Shop.isActive) {
			Game.getInstance().getShop().update();
			drawGuiShop();
			drawGuiScore();
			return;
		}
		// Draw GUI controls objects.
		drawGuiControls();

		// Update physics
		updatePhysics();

		// Update camera
		updateCamera();
		spriteBatch.end();
		// Draw game objects.
		drawGameObjects();

		spriteBatch.setProjectionMatrix(normalProjectionMatrix);
		// Draw Stats
		drawGuiStats();

		// renderer.render(Game.getInstance().getWorld());

	}

	private void drawMenu() {
		spriteBatch.draw(Game.getInstance().getGui().getTextureMenuScreen(), 0,
				0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (Sprite sprite : Game.getInstance().getGui().getMenuSpriteList()) {
			sprite.draw(spriteBatch);
		}
	}

	private void drawHelp() {
		spriteBatch.draw(Game.getInstance().getGui().getTextureHelpScreen(), 0,
				0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	private void drawGuiStats() {
		Game.getInstance().getGui().updateStats();
		spriteBatch.setTransformMatrix(rotatedMatrix);
		spriteBatch.begin();
		for (int i = 0; i < Game.getInstance().getGui().getStatsFontList()
				.size(); i++) {
			Game.getInstance().getGui().getStatsFontList().get(i)
					.draw(spriteBatch);
		}

		spriteBatch.end();
		spriteBatch.setTransformMatrix(notRotatedMatrix);
	}

	private void drawGuiScore() {
		Game.getInstance().getGui().updateStats();
		spriteBatch.begin();
		Game.getInstance().getGui().getScoreShopFont().draw(spriteBatch);
		spriteBatch.end();
	}

	private void drawGuiShop() {
		spriteBatch.end();
		spriteBatch.begin();
		for (BitmapFontCache text : Game.getInstance().getGui()
				.getShopFontList()) {
			text.draw(spriteBatch);
		}
		for (Sprite sprite : Game.getInstance().getGui().getShopSpriteList()) {
			sprite.draw(spriteBatch);
		}
		spriteBatch.end();
	}

	private void drawGameObjects() {

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();

		GameObject go = null;
		Iterator<Body> it = Game.getInstance().getWorld().getBodies();
		while (it.hasNext()) {
			go = (GameObject) it.next().getUserData();

			if (go == null)
				continue;
			float x, y, originX, originY, halfWidth, halfHeight, scaleX, scaleY, rotation;

			halfWidth = go.getWidth() / 2;
			halfHeight = go.getHeight() / 2;

			rotation = (float) (go.getBody().getAngle() * 180 / Math.PI);

			x = go.getBody().getPosition().x - halfWidth / 2;
			y = go.getBody().getPosition().y - halfHeight / 2;

			originX = halfWidth / 2;
			originY = halfHeight / 2;

			scaleX = 2;
			scaleY = 2;

			spriteBatch.draw(go.getTexture(), x, y, originX, originY,
					halfWidth, halfHeight, scaleX, scaleY, rotation);
		}
		spriteBatch.end();

	}

	private void updatePhysics() {
		Game.getInstance().getWorld()
				.step(Gdx.app.getGraphics().getDeltaTime(), 8, 3);
	}

	private void updateCamera() {
		GL10 gl = Gdx.app.getGraphics().getGL10();
		camera.update();
		camera.apply(gl);
	}

	private void drawGuiControls() {
		for (Sprite sprite : Game.getInstance().getGui().getControlSpriteList()) {
			sprite.draw(spriteBatch);
			// spriteBatch.draw(sprite.getTexture(), sprite.getX(),
			// sprite.getY());
		}
		// Sprite sprite =
		// Game.getInstance().getGui().getControlSpriteList().get(1);
		// sprite.draw(spriteBatch);
		// sprite = Game.getInstance().getGui().getControlSpriteList().get(0);
		// sprite.draw(spriteBatch);
	}

	/**
	 * pushes the current matrix, draws the backgrounds then pops the matrix.
	 */
	private void drawBackground() {
		float bgPosition = bgIteration * bgSpeed;
		if (firstBg != null) {
			spriteBatch.draw(firstBg, -bgPosition, 0, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
		} else {
			System.err.println("drawBackground(): firstBg=" + firstBg);
		}
		if (lastBg != null) {
			spriteBatch.draw(lastBg, -bgPosition + Gdx.graphics.getWidth(), 0,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		} else {
			System.err.println("drawBackground(): lastBg=" + lastBg);
		}
		time += Gdx.app.getGraphics().getDeltaTime();
		if (time > 0.01f) {
			time = 0;
			bgIteration++;
		}
		if (bgPosition > Gdx.graphics.getWidth() - 1) {
			firstBg = lastBg;
			lastBg = Game.getInstance().getMap().getNext();
			bgIteration = 0;
		}

	}

	@Override
	public void dispose() {
		// Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
		// + ", application destroyed");
	}

	@Override
	public void pause() {
		run = false;
		// store data here
	}

	@Override
	public void resume() {
		// Game.textureAtlas = new
		// TextureAtlas(Gdx.files.internal("data/pack"));
		run = true;
	}

	@Override
	public void resize(int x, int y) {

	}

}
