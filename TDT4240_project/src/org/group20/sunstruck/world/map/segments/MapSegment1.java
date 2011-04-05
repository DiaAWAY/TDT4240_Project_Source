package org.group20.sunstruck.world.map.segments;

import org.group20.sunstruck.Game;

public class MapSegment1 extends MapSegment {

	public MapSegment1() {
		// initialize texture
		setType(MAPTYPES.DEFAULT);
		setTextureRegion(Game.textureAtlas.findRegion("stars1"));
	}
}
