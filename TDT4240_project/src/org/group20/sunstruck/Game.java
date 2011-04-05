package org.group20.sunstruck;

import java.util.ArrayList;
import java.util.Iterator;

import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import org.group20.sunstruck.gameobject.Boss;
import org.group20.sunstruck.gameobject.GameObject;
import org.group20.sunstruck.gameobject.GameObjectFactory;
import org.group20.sunstruck.gameobject.Player;
import org.group20.sunstruck.gui.GUI;
import org.group20.sunstruck.input.Input;
import org.group20.sunstruck.interfaces.GameInterface;
import org.group20.sunstruck.world.map.MapGenerator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

public class Game implements GameInterface, ContactListener {
	public static boolean DEBUG = false;
	public static TextureAtlas textureAtlas = new TextureAtlas(Gdx.files
			.internal("data/pack"));
	private float updateRate = 1.0f; // physics update rate
	private float totalTime;
	private Vector2 initGravity = new Vector2(0, 0);
	private DIFFICULTIES difficulty;
	private GameObjectFactory goFactory;
	private MapGenerator map = new MapGenerator();
	private Player player;
	private Shop shop;
	private World world;
	private Input input;
	private GUI gui;
	private ArrayList<Body> destroyedBodiesList = new ArrayList<Body>();
	private float time;
	private boolean bossMode = false;
	private boolean bossAlive = false;
	private float bossTimer = 0;
	private int bossCount = 1;
	private int bossInterval = 100; // playerScore > bossInterval*bossCount =>
									// spawn boss
	private float enemySpawnTime = 0;

	private Game() {
		this(DIFFICULTIES.MEDIUM);
	}

	public void initializePlayer() {
		player = (Player) goFactory.createPlayer();
	}

	private Game(DIFFICULTIES d) {
		setDifficulty(d);

		world = new World(initGravity, true);
		world.setContactListener(this);
		gui = new GUI();
		input = new Input(gui);
		goFactory = new GameObjectFactory(d);
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

	public void update() {
		clearDestroyedBodiesList();

		totalTime++;
		if (Game.DEBUG)
			System.out.println("Total game updates: " + totalTime);

		time += Gdx.graphics.getDeltaTime();
		if (time >= 0.01) {
			input.update();
			Body body = null;
			GameObject go = null;
			Iterator<Body> it = world.getBodies();
			while (it.hasNext()) {
				body = it.next();
				if (body == null)
					continue;
				if (body.getType() == BodyType.StaticBody)
					continue;
				go = (GameObject) body.getUserData();
				go.update();
			}
			time = 0;
		}

		// SPAWNING ENEMIES
		if (!bossMode) {
			if (player.getScore() > bossInterval * bossCount) {
				bossMode = true;
			} else {
				spawnEnemy();
			}
		} else {
			spawnBoss();
		}
	}
	private void spawnEnemy() {
		
		enemySpawnTime += Gdx.graphics.getDeltaTime();
		if (!bossMode && enemySpawnTime >= 2.0) {
			System.out.println("Spawning enemy!");
			goFactory.createEnemy1(new Vector2(7, (int) Math.random() * 7))
					.setBehavior(BEHAVIOR.SPRAY);
			bossAlive = true;
			enemySpawnTime = 0;
		}
	}

	private void spawnBoss() {
		bossTimer += Gdx.graphics.getDeltaTime();
		if (!bossAlive && bossTimer >= 5.0) {
			System.out.println("Spawning boss!");
			goFactory.createBoss(new Vector2(7, 0));
			bossTimer = 0;
		}
	}

	@Override
	public void beginContact(Contact contact) {
		Body A = contact.getFixtureA().getBody();
		Body B = contact.getFixtureB().getBody();

		GameObject goA = null;
		GameObject goB = null;

		if (A.getType() != BodyType.StaticBody)
			goA = (GameObject) contact.getFixtureA().getBody().getUserData();
		if (B.getType() != BodyType.StaticBody)
			goB = (GameObject) contact.getFixtureB().getBody().getUserData();

		if (goA != null) {
			if (goB != null) {
				if (!goB.isProjectile() && !goA.isProjectile() && goB.isEnemy()
						&& goA.isEnemy())
					return;
				goA.contact(contact.GetWorldManifold(), goB.getImpactDamage());
			} else {
				goA.setScore(0);
				goA.contact(contact.GetWorldManifold(), Float.MAX_VALUE);
			}
		}

		if (goB != null) {
			if (goA != null) {
				if (!goA.isProjectile() && !goB.isProjectile() && goA.isEnemy()
						&& goB.isEnemy())
					return;
				goB.contact(contact.GetWorldManifold(), goB.getImpactDamage());
			} else {
				goB.setScore(0);
				goB.contact(contact.GetWorldManifold(), Float.MAX_VALUE);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {

	}

	private void clearDestroyedBodiesList() {
		for (Body body : destroyedBodiesList) {
			// System.out.println(body);
			player.setScore(player.getScore()
					+ ((GameObject) body.getUserData()).getScore());
			if (((GameObject) body.getUserData()) instanceof Boss) {
				System.out.println("Boss Dead!");
				bossCount++;
				bossMode = false;
				enemySpawnTime = -5;
			}
			System.out.println("player score: " + player.getScore());
			world.destroyBody(body);
		}
		destroyedBodiesList.clear();
	}

	// Getter's and setter's (No shit)

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
	public void setWorld(World world) {
		this.world = world;

	}

	@Override
	public World getWorld() {
		return world;
	}

	public ArrayList<Body> getDestroyedBodiesList() {
		return destroyedBodiesList;

	}

	/**
	 * The boss interval decides at which score values bosses spawn. example:
	 * value set to 100 will spawn bosses when scores are above 100, 200, 300,
	 * etc.
	 * 
	 * @param i
	 */
	public void setBossInterval(int i) {
		this.bossInterval = i;
	}
}
