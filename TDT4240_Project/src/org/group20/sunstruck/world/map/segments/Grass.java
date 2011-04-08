package org.group20.sunstruck.world.map.segments;

import java.util.HashMap;

import org.group20.sunstruck.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Grass extends Theme {

	private TextureRegion[] textures = {
			Game.textureAtlas.findRegion("grass1"),
			Game.textureAtlas.findRegion("grass2"),
			Game.textureAtlas.findRegion("grass3") };
	private HashMap<MapTypes, TextureRegion> transitions = new HashMap<MapTypes, TextureRegion>();

	public Grass() {
		setType(MapTypes.GRASS);
		setTextures(textures);
		transitions.put(MapTypes.DESERT,
				Game.textureAtlas.findRegion("grassToDesert"));
		transitions.put(MapTypes.ROCK,
				Game.textureAtlas.findRegion("grassToRock"));
		setTransitions(transitions);
	}
}
