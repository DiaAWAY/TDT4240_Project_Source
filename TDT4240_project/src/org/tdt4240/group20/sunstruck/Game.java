package org.tdt4240.group20.sunstruck;

import org.tdt4240.group20.sunstruck.behavior.Behavior;
import org.tdt4240.group20.sunstruck.gameobject.GameObjectFactory;
import org.tdt4240.group20.sunstruck.gameobject.Player;
import org.tdt4240.group20.sunstruck.gui.GUI;
import org.tdt4240.group20.sunstruck.input.Input;
import org.tdt4240.group20.sunstruck.world.MapGenerator;
import org.tdt4240.group20.sunstruck.world.World;

public class Game {
	/** singleton pattern? should we implement an interface here? 
	 *  could be a goal in  modifiabilty...
	 **/
	

	public static Game GAME = null;
	public static enum DIFFICULTIES {EASY, MEDIUM, HARD}
	private double UPDATERATE = 1.0; // physics update rate
	private DIFFICULTIES difficulty;
	private GameObjectFactory goFactory = new GameObjectFactory();
	private Player player = new Player();
	private Shop shop = new Shop();
	private MapGenerator map = new MapGenerator();
	private World world = new World();
	private Input input = new Input();
	private GUI gui = new GUI();
	private float totalTime;

	public Game() {
		this(DIFFICULTIES.MEDIUM);
	}

	public Game(DIFFICULTIES d) {
		setDifficulty(d);
		Game.GAME = this;
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

	public void setDifficulty(DIFFICULTIES difficulty) {
		this.difficulty = difficulty;
	}

	public DIFFICULTIES getDifficulty() {
		return difficulty;
	}

	public void setUpdateRate(double updaterate) {
		UPDATERATE = updaterate;
	}

	public double getUpdateRate() {
		return UPDATERATE;
	}
}
