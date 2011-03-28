package org.group20.sunstruck;

import java.util.ArrayList;
import java.util.Collection;

import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.gameobject.GameObject;
import org.group20.sunstruck.gameobject.GameObjectFactory;
import org.group20.sunstruck.gameobject.Player;
import org.group20.sunstruck.gui.GUI;
import org.group20.sunstruck.input.Input;
import org.group20.sunstruck.interfaces.GameInterface;
import org.group20.sunstruck.world.map.MapGenerator;
import org.group20.sunstruck.world.map.segments.MapSegment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Game implements GameInterface {
	private float updateRate = 1.0f; // physics update rate	
	private float totalTime;	
	private Vector2 initGravity = new Vector2(0,0);	
	private DIFFICULTIES difficulty;
	
	private GameObjectFactory goFactory = new GameObjectFactory();	
	private Player player;	
	private Shop shop;	
	private World world;	
	private Input input;	
	private GUI gui;

	private TextureAtlas textureAtlas;
	
	private ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
	
	private Game() {
		this(DIFFICULTIES.MEDIUM);
	}
	
	public void initializePlayer(){
		player = new Player(new Vector2(0,0),0.5f, 0.5f, new TextureRegion(new Texture(Gdx.files.internal("data/TIE_Interceptor.png"))), 1, 10, 1, 1,1);
		gameObjectList.add(player);
	}
	
	
	private Game(DIFFICULTIES d) {
		setDifficulty(d);

		world = new World(initGravity, true);
		//textureAtlas = new TextureAtlas(Gdx.files.internal("data/pack"));
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

	public void update() {
		totalTime++;
		System.out.println("Total game updates: " + totalTime);
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

	public Collection<MapSegment> getDrawables() {
		ArrayList<MapSegment> list = new ArrayList<MapSegment>();
		return list;
	}

	@Override
	public void setMap(MapGenerator map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapGenerator getMap() {
		// TODO Auto-generated method stub
		return null;
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
	
	public ArrayList<GameObject> getGameObjectList(){
		return gameObjectList;
	}
}
