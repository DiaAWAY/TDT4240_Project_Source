package org.group20.sunstruck.world.map.segments;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MapSegment1 extends MapSegment {

	public MapSegment1() {
		// initialize texture
		setType(MAPTYPES.TUNDRA);
		setTextureRegion(Game.textureAtlas.findRegion("seaworldBGPlaceholder"));
	}
}
