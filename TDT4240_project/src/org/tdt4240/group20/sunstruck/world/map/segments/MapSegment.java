package org.tdt4240.group20.sunstruck.world.map.segments;

import com.badlogic.gdx.graphics.Texture;

public abstract class MapSegment {

	public static enum MAPTYPES {WINTER, JUNGLE, PLAINS, TUNDRA, DEFAULT}
	
	private MapSegment.MAPTYPES type = MAPTYPES.DEFAULT;
	
	public abstract Texture getTexture();
	
	public MAPTYPES getType() {
		return type;
	}
	
	public void setType(MapSegment.MAPTYPES t) {
		type = t;
	}
}
