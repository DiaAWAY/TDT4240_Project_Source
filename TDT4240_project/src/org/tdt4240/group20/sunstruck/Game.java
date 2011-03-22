package org.tdt4240.group20.sunstruck;

import org.tdt4240.group20.sunstruck.behavior.Behavior;
import org.tdt4240.group20.sunstruck.gameobject.GameObjectFactory;
import org.tdt4240.group20.sunstruck.gameobject.Player;
import org.tdt4240.group20.sunstruck.gui.GUI;
import org.tdt4240.group20.sunstruck.input.Input;
import org.tdt4240.group20.sunstruck.world.World;
import org.tdt4240.group20.sunstruck.world.map.MapGenerator;

import com.badlogic.gdx.math.Vector2;

public class Game {
	/** 
	 * should we implement an interface here? 
	 * could be a goal in  modifiabilty...
	 **/

	public static enum DIFFICULTIES {EASY, MEDIUM, HARD}
	private DIFFICULTIES DIFFICULTY;
	private double UPDATERATE = 1.0; // physics update rate
	private GameObjectFactory goFactory = new GameObjectFactory();
	private Player player = new Player();
	private Shop shop = new Shop();
	private MapGenerator map = new MapGenerator();
	private World world = new World(new Vector2(0.0f, 0.5f), false); // TODO set to true for performance?
	private Input input = new Input();
	private GUI gui = new GUI();
	private float totalTime;

	private Game() {
		this(DIFFICULTIES.MEDIUM);
	}

	private Game(DIFFICULTIES d) {
		setDifficulty(d);
	}
	
	private static class GameHolder { // singleton holder
		public static final Game INSTANCE = new Game();
	}
	
	public static Game getInstance() {
		return GameHolder.INSTANCE;
	}
	
	public void start() {
		Behavior.initFilters();
		world.add(goFactory.getProductA());
		world.add(goFactory.getProductB());
		world.add(goFactory.getProductA("Hii"));
	}

	public void update() {
		world.update();
		totalTime++;
		System.out.println("Total game updates: " + totalTime);
	}
	
	// Getter's and setter's

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

	public void setMap(MapGenerator map) {
		this.map = map;
	}

	public MapGenerator getMap() {
		return map;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public World getWorld() {
		return world;
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
		this.DIFFICULTY = d;
	}

	public DIFFICULTIES getDifficulty() {
		return DIFFICULTY;
	}

	public void setUpdateRate(double updaterate) {
		UPDATERATE = updaterate;
	}

	public double getUpdateRate() {
		return UPDATERATE;
	}
}
