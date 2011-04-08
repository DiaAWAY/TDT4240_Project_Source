package org.group20.sunstruck.world.map.segments;

import java.util.HashMap;
import org.group20.sunstruck.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Desert extends Theme {

	private TextureRegion[] textures = {
			Game.TextureAtlas.findRegion("desert1"),
			Game.TextureAtlas.findRegion("desert2"),
			Game.TextureAtlas.findRegion("desert3") };
	private HashMap<MapTypes, TextureRegion> transitions = new HashMap<MapTypes, TextureRegion>();

	public Desert() {
		setType(MapTypes.DESERT);
		setTextures(textures);
		transitions.put(MapTypes.GRASS,
				Game.TextureAtlas.findRegion("desertToGrass"));
		transitions.put(MapTypes.ROCK,
				Game.TextureAtlas.findRegion("desertToRock"));
		setTransitions(transitions);
	}
}
