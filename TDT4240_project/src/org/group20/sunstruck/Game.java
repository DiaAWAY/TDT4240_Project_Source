package org.group20.sunstruck;

import java.util.ArrayList;

import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.gameobject.GameObject;
import org.group20.sunstruck.gameobject.GameObjectFactory;
import org.group20.sunstruck.gameobject.Player;
import org.group20.sunstruck.gameobject.TieInterceptor;
import org.group20.sunstruck.gui.GUI;
import org.group20.sunstruck.input.Input;
import org.group20.sunstruck.interfaces.GameInterface;
import org.group20.sunstruck.world.map.MapGenerator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

public class Game implements GameInterface, ContactListener {
	public static boolean DEBUG = false;
	public static TextureAtlas textureAtlas = new TextureAtlas(
			Gdx.files.internal("data/pack"));
	private float updateRate = 1.0f; // physics update rate
	private float spawnRate = 1.0f;
	private float totalTime;
	private Vector2 initGravity = new Vector2(0, 0);
	private DIFFICULTIES difficulty;
	private GameObjectFactory goFactory = new GameObjectFactory();
	private MapGenerator map = new MapGenerator();
	private Player player;
	private Shop shop;
	private World world;
	private Input input;
	private GUI gui;
	private ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
	private ArrayList<GameObject> gameObjectsToBeDestroyed = new ArrayList<GameObject>();
	private float time;

	private Game() {
		this(DIFFICULTIES.MEDIUM);
	}

	public void initializePlayer() {
		player = new Player(new Vector2(0, 0), 1f, 1f,
				textureAtlas.findRegion("TIEBomber"), 1, 10, 1000, 1000, 1000,
				100);
		player.getBody().setFixedRotation(true);
		gameObjectList.add(player);
	}

	private Game(DIFFICULTIES d) {
		setDifficulty(d);

		world = new World(initGravity, true);
		world.setContactListener(this);
		gui = new GUI();
		input = new Input(gui);
	}

	private static class GameHolder { // singleton holder
		public static final Game INSTANCE = new Game();
	}

	public static Game getInstance() {
		return GameHolder.INSTANCE;
	}

	public void start() {
		Behavior.initFilters();
	}

	public synchronized void update() {
		clearGameObjectsToBeDestroyed();

		totalTime++;
		if (Game.DEBUG)
			System.out.println("Total game updates: " + totalTime);

		time += Gdx.graphics.getDeltaTime();
		if (time >= 0.01) {
			input.update();
			for (GameObject o : gameObjectList) {
				o.update();
				if (!(o instanceof Player))
					Behavior.applyBehavior(o);
			}
			time = 0;
		}
		if (Math.random() > 0.99)
			spawnEnemies();
	}

	@Override
	public void beginContact(Contact contact) {
		Body A = contact.getFixtureA().getBody();
		Body B = contact.getFixtureB().getBody();

		GameObject goA = null;
		GameObject goB = null;

		for (GameObject go : gameObjectList) {
			if (go.getBody().equals(A))
				goA = go;
			if (go.getBody().equals(B))
				goB = go;
		}

		if (goA != null)
			if (goB != null)
				goA.contact(contact.GetWorldManifold(), goB.getImpactDamage());
			else
				goA.contact(contact.GetWorldManifold(), Float.MAX_VALUE);
		if (goB != null)
			if (goA != null)
				goB.contact(contact.GetWorldManifold(), goB.getImpactDamage());
			else
				goB.contact(contact.GetWorldManifold(), Float.MAX_VALUE);
	}

	@Override
	public void endContact(Contact contact) {

	}

	private void clearGameObjectsToBeDestroyed() {
		for (GameObject go : gameObjectsToBeDestroyed) {
			// Remove from physics
			world.destroyBody(go.getBody());
			// Remove from gameObjectList
			gameObjectList.remove(go);
		}
		gameObjectsToBeDestroyed.clear();
	}

	private void spawnEnemies() {
		TieInterceptor t = new TieInterceptor(new Vector2(6, (int)(Math.random()*14-6)), 1f, 1f,
				textureAtlas.findRegion("TIEFighter"), 1, 10, 10, 10, 10, 100);
		t.getBody().setFixedRotation(true);
		gameObjectList.add(t);
	}

	// Getter's and setter's (No shit)
	public ArrayList<GameObject> getGameObjectsToBeDestroyed() {
		return gameObjectsToBeDestroyed;
	}

	public void setGoFactory(GameObjectFactory goFactory) {
		this.goFactory = goFactory;
	}

	public GameObjectFactory getGoFactory() {
		return goFactory;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Shop getShop() {
		return shop;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Input getInput() {
		return input;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public GUI getGui() {
		return gui;
	}

	public void setDifficulty(DIFFICULTIES d) {
		this.difficulty = d;
	}

	public DIFFICULTIES getDifficulty() {
		return difficulty;
	}

	public void setUpdateRate(float updaterate) {
		updateRate = updaterate;
	}

	public float getUpdateRate() {
		return updateRate;
	}

	@Override
	public void setMap(MapGenerator map) {
		this.map = map;
	}

	@Override
	public MapGenerator getMap() {
		return map;
	}

	@Override
	public void setWorld(com.badlogic.gdx.physics.box2d.World world) {
		this.world = world;

	}

	@Override
	public com.badlogic.gdx.physics.box2d.World getWorld() {
		return world;
	}

	@Override
	public void setTextureAtlas(TextureAtlas textureAtlas) {
		this.textureAtlas = textureAtlas;

	}

	@Override
	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	public ArrayList<GameObject> getGameObjectList() {
		return gameObjectList;
	}
}
