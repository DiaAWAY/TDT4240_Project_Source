package org.group20.sunstruck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
	public static TextureAtlas TextureAtlas = new TextureAtlas(
			Gdx.files.internal("data/pack"));
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
	private int bossInterval = 50000; // playerScore >
										// bossInterval*bossCount =>
	// spawn boss
	private float enemySpawnTime = 0;

	private Game() {
		this(DIFFICULTIES.MEDIUM);
	}

	public void initializePlayer() {
		player = (Player) goFactory.createPlayer(new Vector2(0, 0), 0);
	}

	private Game(DIFFICULTIES d) {
		setDifficulty(d);
		world = new World(initGravity, true);
		world.setContactListener(this);
		gui = new GUI();
		input = new Input(gui);
		goFactory = new GameObjectFactory(d);
		shop = new Shop();
	}

	private static class GameHolder { // singleton holder
		public static final Game INSTANCE = new Game();
	}

	public static Game getInstance() {
		return GameHolder.INSTANCE;
	}

	@Override
	public void start() {
		Behavior.initFilters();
	}

	@Override
	public void update() {
		if (Shop.isActive) {
			input.update();
			return;
		}
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
			if (player.getScore() >= bossInterval * bossCount) {
				// System.out.println("Boss mode activated");
				bossMode = true;
			} else {
				spawnEnemy();
			}
		} else {
			spawnBoss();
		}
	}

	private void spawnEnemy() {
		if (!bossMode) {
			enemySpawnTime += Gdx.graphics.getDeltaTime();
			if (enemySpawnTime >= 0) {
				int random = randomNumber(0, 2); // 2 = n-1. 3 is the number of
													// enemies available
				switch (random) {
				case 0:
					spawnSmallLaserSquad();
					break;
				case 1:
					spawnMediumKamikazeSquad();
					break;
				case 2:
					spawnSmallKamikazeSquad();
					break;
				default:
					break;
				}
				enemySpawnTime = 0;
			}
		}
	}

	private void spawnBoss() {
		if (!bossAlive) {
			bossTimer += Gdx.graphics.getDeltaTime();
			// System.out.println("Trying to spawn boss{alive:" + bossAlive
			// + "}, remaining time:" + bossTimer + "/5.0");
			if (bossTimer >= 10.0) {
				// System.out.println("Spawning boss!");
				goFactory.createBoss(new Vector2(12, 0), (float) Math.PI);
				bossAlive = true;
				bossTimer = 0;
			}
		}
	}

	private void spawnMediumKamikazeSquad() {
		int numberOfShips = (int) (Math.random() * 5 + 1);
		while (numberOfShips-- > 0)
			goFactory.createMediumKamikazeShip(getNewEnemyPosition(), 0);
	}

	private void spawnSmallLaserSquad() {
		int numberOfShips = (int) (Math.random() * 5 + 1);
		while (numberOfShips-- > 0) {
			goFactory.createSmallLaserShip(getNewEnemyPosition(),
					(float) Math.PI);
		}

	}

	private void spawnSmallKamikazeSquad() {
		int numberOfShips = (int) (Math.random() * 5 + 1);
		while (numberOfShips-- > 0) {
			goFactory.createSmallKamikazeShip(getNewEnemyPosition(), 0);
		}

	}

	private Vector2 getNewEnemyPosition() {
		float x_min, x_max, y_min, y_max, scale, widthClearnce, heightClearance, x, y;

		scale = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
		x_min = Main.CAMERA_WIDTH;
		x_max = Main.CAMERA_WIDTH * 2;
		y_min = -Main.CAMERA_WIDTH * scale;
		y_max = Main.CAMERA_WIDTH * scale;

		widthClearnce = 3f;
		heightClearance = 3f;

		x = (float) ((x_min + widthClearnce / 2) + (x_max - x_min - widthClearnce)
				* Math.random());
		y = (float) ((y_min + heightClearance / 2) + (y_max - y_min - heightClearance)
				* Math.random());

		return new Vector2(x, y);
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

		// --- check if bodies are hitting the wall and act accordingly ---
		if (goA == null && goB != null) {
			goB.setScore(0); // player won't get any score for this
			goB.contact(contact, Float.MAX_VALUE);
			return;
		} else if (goA != null && goB == null) {
			goA.setScore(0); // player won't get any score for this
			goA.contact(contact, Float.MAX_VALUE);
			return;
		}

		// --- let's check if enemies are shooting at each other! ---
		if (goB.isEnemy() && goA.isEnemy()) { // enemy objects hitting each
												// other
			if (goB.isProjectile()) {
				goB.contact(contact, Float.MAX_VALUE);
			}
			if (goA.isProjectile()) {
				goA.contact(contact, Float.MAX_VALUE);
			}
			return;
		}

		// okay, now we're talking! enemy or player damage!
		goA.contact(contact, goB.getImpactDamage());
		goB.contact(contact, goA.getImpactDamage());
	}

	@Override
	public void endContact(Contact contact) {

	}

	private void clearDestroyedBodiesList() {
		Body body = null;
		for (int i = 0; i < destroyedBodiesList.size(); i++) {
			body = destroyedBodiesList.get(i);
			// System.out.println(body);
			player.setScore(player.getScore()
					+ ((GameObject) body.getUserData()).getScore());
			if (((GameObject) body.getUserData()) instanceof Boss) {
				// System.out.println("Boss Dead!");
				bossCount++;
				bossMode = false;
				bossAlive = false;
				enemySpawnTime = 0;
			}
			// world.destroyBody(body);
			if (((GameObject) body.getUserData()).isExploding())
				for (int j = 0; j < body.getFixtureList().size(); j++)
					body.destroyFixture(body.getFixtureList().get(j));
			else {
				world.destroyBody(body);
				destroyedBodiesList.remove(i);
			}
		}
		// destroyedBodiesList.clear();
	}

	// Getter's and setter's (No shit)

	@Override
	public void setGoFactory(GameObjectFactory goFactory) {
		this.goFactory = goFactory;
	}

	@Override
	public GameObjectFactory getGoFactory() {
		return goFactory;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Override
	public Shop getShop() {
		return shop;
	}

	@Override
	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public void setGui(GUI gui) {
		this.gui = gui;
	}

	@Override
	public GUI getGui() {
		return gui;
	}

	@Override
	public void setDifficulty(DIFFICULTIES d) {
		this.difficulty = d;
	}

	@Override
	public DIFFICULTIES getDifficulty() {
		return difficulty;
	}

	@Override
	public void setUpdateRate(float updaterate) {
		updateRate = updaterate;
	}

	@Override
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

	/**
	 * gets a random number between inclusive min and inclusive max
	 * 
	 * @param min
	 *            - the minimum value
	 * @param max
	 *            - the maximum value
	 * @return
	 */
	public int randomNumber(int min, int max) {
		return min + (int) Math.round((Math.random() * (max - min)));
	}
}
