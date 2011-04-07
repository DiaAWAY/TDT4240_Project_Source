package org.group20.sunstruck.world.map.segments;

import org.group20.sunstruck.Game;

public class MapSegment4 extends MapSegment {

	public MapSegment4() {
		// initialize texture
		setType(MAPTYPES.GRASS_DESERT);
		setTextureRegion(Game.textureAtlas.findRegion("desert1"));
	}
}
