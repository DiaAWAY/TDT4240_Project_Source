package org.group20.sunstruck.world.map.segments;

import java.util.HashMap;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rock extends Theme {

	private TextureRegion[] textures = { Game.TextureAtlas.findRegion("rock1"),
			Game.TextureAtlas.findRegion("rock2"),
			Game.TextureAtlas.findRegion("rock3") };
	private HashMap<MapTypes, TextureRegion> transitions = new HashMap<MapTypes, TextureRegion>();

	public Rock() {
		setType(MapTypes.ROCK);
		setTextures(textures);
		transitions.put(MapTypes.DESERT,
				Game.TextureAtlas.findRegion("rockToDesert"));
		transitions.put(MapTypes.GRASS,
				Game.TextureAtlas.findRegion("rockToGrass"));
		transitions.put(MapTypes.LAVA,
				Game.TextureAtlas.findRegion("rockToLava"));
		transitions.put(MapTypes.WATER,
				Game.TextureAtlas.findRegion("rockToWater"));
		setTransitions(transitions);
	}
}
