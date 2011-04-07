package org.group20.sunstruck.world.map.segments;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Theme {

	protected boolean isTransitional = false;
	
	private TextureRegion[] textures;
	private MapTypes type;
	private HashMap<MapTypes, TextureRegion> transitions;
	
	public static enum MapTypes {
		DESERT, GRASS, WATER, ROCK, LAVA
//		DESERT_GRASS, DESERT_ROCK,
//		GRASS_DESERT, GRASS_WATER, 
//		GRASS_ROCK, ROCK_GRASS, 
//		ROCK_WATER, WATER_ROCK, 
//		ROCK_LAVA, LAVA_ROCK, 
//		ROCK_DESERT, 
//		DEFAULT
	}
	
	public TextureRegion next() {
		return textures[randomIndexIn(textures)];
	}
	
	public TextureRegion transitionTo(MapTypes t) {
		System.out.println("transitions="+transitions);
		return transitions.get(t);
	}

	public TextureRegion[] getTextures() {
		return textures;
	}

	public void setTextures(TextureRegion[] t) {
		textures = t;
	}
	
	public HashMap<MapTypes, TextureRegion> getTransitions() {
		return transitions;
	}

	public void setTransitions(HashMap<MapTypes, TextureRegion> t) {
		transitions = t;
	}	

	public MapTypes getType() {
		return type;
	}

	public void setType(Theme.MapTypes t) {
		type = t;
	}
	
	public boolean isTransitional() {
		return isTransitional;
	}
	
	private int randomIndexIn(Object[] o) {
		return (int) (Math.random() * (o.length - 1) + 0.5);
	}	
}
