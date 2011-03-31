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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Main implements ApplicationListener {
	// The width and height of the orthographical camera
	public static final float CAMERA_WIDTH = 10;
	public static float bgScale = 1.0f;
	public static float bgSpeed = 0.001f;
	public static Body eastBorder;
	public static Body northBorder;
	public static Body westBorder;
	public static Body southBorder;
	private Mesh mesh;
	private MapSegment last;
	private MapSegment first;
	private SpriteBatch guiBatch;
	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
	private float xOffset = 0;
	private boolean run = true;

	@Override
	public void create() {
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
				+ ", surface created");

		Game.getInstance().initializePlayer();

		// Scales the height.
		float scale = (float) Gdx.graphics.getHeight()
				/ Gdx.graphics.getWidth();
		bgScale = CAMERA_WIDTH * scale / 2;
		
		camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * scale);
		camera.position.set(0, 0, 0);
		
		//East border.
		PolygonShape eastBorderPoly = new PolygonShape();
		eastBorderPoly.setAsBox(0, CAMERA_WIDTH*scale);
		
		BodyDef eastBorderDef = new BodyDef();
		eastBorderDef.position.x = CAMERA_WIDTH;
		eastBorderDef.position.y = 0;
		eastBorderDef.type = BodyType.StaticBody;
		
		eastBorder = Game.getInstance().getWorld().createBody(eastBorderDef);
		eastBorder.createFixture(eastBorderPoly, 10);
		eastBorderPoly.dispose();
		
		//West border
		PolygonShape westBorderPoly = new PolygonShape();
		westBorderPoly.setAsBox(0, CAMERA_WIDTH*scale);
		
		BodyDef westBorderDef = new BodyDef();
		westBorderDef.position.x = -CAMERA_WIDTH;
		westBorderDef.position.y = 0;
		westBorderDef.type = BodyType.StaticBody;
		
		westBorder = Game.getInstance().getWorld().createBody(westBorderDef);
		westBorder.createFixture(westBorderPoly, 10);
		westBorderPoly.dispose();
		
		//North border
		PolygonShape northBorderPoly = new PolygonShape();
		northBorderPoly.setAsBox(CAMERA_WIDTH, 0);
		
		BodyDef northBorderDef = new BodyDef();
		northBorderDef.position.x = 0;
		northBorderDef.position.y = CAMERA_WIDTH*scale;
		northBorderDef.type = BodyType.StaticBody;
		
		northBorder = Game.getInstance().getWorld().createBody(northBorderDef);
		northBorder.createFixture(northBorderPoly, 10);
		northBorderPoly.dispose();
		
		//North border
		PolygonShape southBorderPoly = new PolygonShape();
		southBorderPoly.setAsBox(CAMERA_WIDTH, 0);
		
		BodyDef southBorderDef = new BodyDef();
		southBorderDef.position.x = 0;
		southBorderDef.position.y = -CAMERA_WIDTH*scale;
		southBorderDef.type = BodyType.StaticBody;
		
		southBorder = Game.getInstance().getWorld().createBody(southBorderDef);
		southBorder.createFixture(southBorderPoly, 10);
		southBorderPoly.dispose();
	
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
		
		//Update game objects
		Game.getInstance().update();
		
		//Draw background
		drawBackground();

		//Draw GUI objects.
		drawGui();

		//Update physics and camera.
		updatePhysicsAndCamera();

		//Draw game objects.
		drawGameObjects();

		//renderer.render(Game.getInstance().getWorld());
	}

	private void drawGameObjects() {		
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		for (GameObject go : Game.getInstance().getGameObjectList()) {
			TextureRegion texture = go.getTexture();
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
			spriteBatch.draw(new TextureRegion(texture), x, y, originX,
					originY, halfWidth, halfHeight, scaleX, scaleY, rotation);
		}
		spriteBatch.end();
		
	}

	private void updatePhysicsAndCamera() {
		// Update physics.
		Game.getInstance().getWorld().step(Gdx.app.getGraphics().getDeltaTime(), 8, 3);
		
		// Update camera.
		GL10 gl = Gdx.app.getGraphics().getGL10();
		camera.update();
		camera.apply(gl);
		
	}

	private void drawGui() {
		guiBatch.begin();
		for (Sprite sprite : Game.getInstance().getGui().getSpriteList())
			sprite.draw(guiBatch);
		guiBatch.end();
		
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
