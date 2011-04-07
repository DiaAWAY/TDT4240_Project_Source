package org.group20.sunstruck.world.map.segments;

import org.group20.sunstruck.Game;

public class MapSegment2 extends MapSegment {

	public MapSegment2() {
		// initialize texture
		setType(MAPTYPES.DEFAULT);
		setTextureRegion(Game.textureAtlas.findRegion("desert2"));
	}
}
