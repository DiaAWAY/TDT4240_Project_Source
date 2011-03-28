package org.group20.sunstruck.world.map.segments;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MapSegment2 extends MapSegment {
	
	public MapSegment2() {
		// initialize texture
		setType(MAPTYPES.WINTER);
		setTexture(Game.textureAtlas.findRegion("seaworldBGPlaceholder2").getTexture());
	}
}
