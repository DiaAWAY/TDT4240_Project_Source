package org.group20.sunstruck.world.map.segments;

import java.util.Collection;
import java.util.HashMap;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.world.map.MapGenerator;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Theme {

	private TextureRegion[] textures;
	private MapTypes type;
	private HashMap<MapTypes, TextureRegion> transitions;

	public static enum MapTypes {
		DESERT, GRASS, WATER, ROCK, LAVA
	}

	public TextureRegion next() {
		return textures[randomIndexIn(textures)];
	}

	public TextureRegion transitionTo(MapTypes t) {
		//System.out.println("transitions=" + transitions);
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

	private int randomIndexIn(Object[] o) {
		return Game.getInstance().randomNumber(0, o.length - 1);
	}
}
