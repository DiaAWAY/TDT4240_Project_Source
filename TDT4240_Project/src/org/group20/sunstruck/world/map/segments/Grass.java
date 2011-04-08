package org.group20.sunstruck.world.map.segments;

import java.util.HashMap;

import org.group20.sunstruck.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Grass extends Theme {

	private TextureRegion[] textures = {
			Game.TextureAtlas.findRegion("grass1"),
			Game.TextureAtlas.findRegion("grass2"),
			Game.TextureAtlas.findRegion("grass3") };
	private HashMap<MapTypes, TextureRegion> transitions = new HashMap<MapTypes, TextureRegion>();

	public Grass() {
		setType(MapTypes.GRASS);
		setTextures(textures);
		transitions.put(MapTypes.DESERT,
				Game.TextureAtlas.findRegion("grassToDesert"));
		transitions.put(MapTypes.ROCK,
				Game.TextureAtlas.findRegion("grassToRock"));
		setTransitions(transitions);
	}
}
