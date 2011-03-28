package org.group20.sunstruck.world.map.segments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MapSegment1 extends MapSegment {

	public MapSegment1() {
		// initialize texture
		setType(MAPTYPES.TUNDRA);
		setTexture(new Texture(Gdx.files.internal("data/background.bmp")));
	}
}
