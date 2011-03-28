package org.group20.sunstruck;


import org.group20.sunstruck.gameobject.GameObject;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;


public class Main implements ApplicationListener {
	//The width and height of the orthographical camera
	public static final float CAMERA_WIDTH = 10;
	
	private static final boolean Object = false;
	
	float r = 1, g = 0, b = 0;
	
	private double time = 0;
	
	private boolean run = true;
	
	private OrthographicCamera camera;

	private SpriteBatch spriteBatch;
	private SpriteBatch guiBatch;
	
	Box2DDebugRenderer renderer;
	
	int counter = 0;
	
	/*	
	private Mesh mesh; // test code
	private Texture texture; // test code
	*/

	@Override
	public void create() {
		Game.getInstance().initializePlayer();
		
		//Scales the height.
		camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH*Gdx.graphics.getHeight()/Gdx.graphics.getWidth());        
        camera.position.set(0, 0, 0);
        
        
		spriteBatch = new SpriteBatch();
		guiBatch = new SpriteBatch();
		
		renderer = new Box2DDebugRenderer();
		/*
		Gdx.app.log("Simple Test", "Thread=" + Thread.currentThread().getId()
				+ ", surface created");
		Gdx.input.setInputProcessor(Game.getInstance().getInput());
		sprites = new SpriteBatch();
		time = System.currentTimeMillis(); // TODO replace this with a more
											// accurate method
		Game.getInstance().start();

		/ test code START /
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
		time+= Gdx.graphics.getDeltaTime();
		if(time>= 0.01){
			Game.getInstance().getInput().update();
			Game.getInstance().getPlayer().update();
			time = 0;
			if(Game.getInstance().getInput().getHasFiredBomb())
				System.out.println("ohjoy");
		}
		
		//Background colour.
        GL10 gl = Gdx.app.getGraphics().getGL10();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	     
        //Draw GUI objects.
        guiBatch.begin();
        for(Sprite sprite : Game.getInstance().getGui().getSpriteList())
        	sprite.draw(guiBatch);
        guiBatch.end();
        
        //Update physics.
		Game.getInstance().getWorld().step(Gdx.app.getGraphics().getDeltaTime(), 8, 3);
		
		//Update camera.
        camera.update();
        camera.apply(gl);
		
        //Draw game objects.
		spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        
       	for(GameObject go : Game.getInstance().getGameObjectList()){
       		TextureRegion texture = go.getTexture();
       		float x, y, originX, originY, halfWidth, halfHeight, scaleX, scaleY, rotation;

       		halfWidth = go.getWidth()/2;
       		halfHeight = go.getHeight()/2;
       		
       		rotation = (float) (go.getBody().getAngle()*180/Math.PI);
       		
       		x = go.getBody().getPosition().x-halfWidth/2;
       		y = go.getBody().getPosition().y-halfHeight/2;
       		
       		originX = halfWidth/2;
       		originY = halfHeight/2;
       		
       		scaleX = 2;
       		scaleY = 2;       	
       		spriteBatch.draw(new TextureRegion(texture), x, y, originX, originY, halfWidth, halfHeight, scaleX, scaleY, rotation);
       	}
       	
        spriteBatch.end();
        
		//renderer.render(Game.getInstance().getWorld());
	
		
		/*
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
		for (MapSegment m : Game.getInstance().getDrawables()) {
			System.out.println(m);
			mesh.render(GL10.GL_TRIANGLE_FAN, 0, 4);
			m.getTexture().bind();
		}
		*/
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
