package org.group20.sunstruck.world.map.segments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class MapSegment {

	public static enum MAPTYPES {
		WINTER, JUNGLE, PLAINS, TUNDRA, WINTER_PLAINS, PLAINS_WINTER, WINTER_TUNDRA, TUNDRA_WINTER, JUNGLE_PLAINS, PLAINS_JUNGLE, TUNDRA_PLAINS, PLAINS_TUNDRA, DEFAULT
	}

	private MapSegment.MAPTYPES type = MAPTYPES.DEFAULT;
	private TextureRegion texReg = null;

	public TextureRegion getTextureRegion() {
		return texReg; 
	}
	
	public void setTextureRegion(TextureRegion t) {
		texReg = t;
	}

	public MAPTYPES getType() {
		return type;
	}

	public void setType(MapSegment.MAPTYPES t) {
		type = t;
	}
}
