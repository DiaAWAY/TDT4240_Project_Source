package org.group20.sunstruck.world.map.segments;

import org.group20.sunstruck.Game;

public class MapSegment3 extends MapSegment {

	public MapSegment3() {
		// initialize texture
		setType(MAPTYPES.DEFAULT);
		setTextureRegion(Game.textureAtlas.findRegion("backgroundDesert"));
	}
}
