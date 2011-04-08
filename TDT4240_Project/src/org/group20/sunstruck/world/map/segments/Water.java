package org.group20.sunstruck.world.map.segments;

import java.util.HashMap;
import org.group20.sunstruck.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Water extends Theme {

	private TextureRegion[] textures = {
			Game.textureAtlas.findRegion("water1"),
			Game.textureAtlas.findRegion("water2"),
			Game.textureAtlas.findRegion("water3") };
	private HashMap<MapTypes, TextureRegion> transitions = new HashMap<MapTypes, TextureRegion>();

	public Water() {
		setType(MapTypes.WATER);
		setTextures(textures);
		transitions.put(MapTypes.ROCK,
				Game.textureAtlas.findRegion("waterToRock"));
		setTransitions(transitions);
	}
}
