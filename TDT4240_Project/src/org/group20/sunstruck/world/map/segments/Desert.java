package org.group20.sunstruck.world.map.segments;

import java.util.HashMap;
import org.group20.sunstruck.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Desert extends Theme {

	private TextureRegion[] textures = {
			Game.textureAtlas.findRegion("desert1"),
			Game.textureAtlas.findRegion("desert2"),
			Game.textureAtlas.findRegion("desert3") };
	private HashMap<MapTypes, TextureRegion> transitions = new HashMap<MapTypes, TextureRegion>();

	public Desert() {
		setType(MapTypes.DESERT);
		setTextures(textures);
		transitions.put(MapTypes.GRASS, Game.textureAtlas.findRegion("desertToGrass"));
		transitions.put(MapTypes.ROCK, Game.textureAtlas.findRegion("desertToRock"));
		setTransitions(transitions);
	}
}
