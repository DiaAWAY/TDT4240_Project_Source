package org.group20.sunstruck.world.map.segments;

import java.util.HashMap;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rock extends Theme {

	private TextureRegion[] textures = { Game.textureAtlas.findRegion("rock1"),
			Game.textureAtlas.findRegion("rock2"),
			Game.textureAtlas.findRegion("rock3") };
	private HashMap<MapTypes, TextureRegion> transitions = new HashMap<MapTypes, TextureRegion>();

	public Rock() {
		setType(MapTypes.ROCK);
		setTextures(textures);
		transitions.put(MapTypes.DESERT, Game.textureAtlas.findRegion("rockToDesert"));
		transitions.put(MapTypes.GRASS, Game.textureAtlas.findRegion("rockToGrass"));
	    transitions.put(MapTypes.LAVA, Game.textureAtlas.findRegion("rockToLava"));
	    transitions.put(MapTypes.WATER, Game.textureAtlas.findRegion("rockToWater"));
	    setTransitions(transitions);
	}
}
