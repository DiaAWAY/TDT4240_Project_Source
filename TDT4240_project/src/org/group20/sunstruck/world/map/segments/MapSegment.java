package org.group20.sunstruck.world.map.segments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class MapSegment {

	public static enum MAPTYPES {
		DESERT, GRASS, WATER, ROCK, LAVA, 
		DESERT_GRASS, GRASS_DESERT, 
		GRASS_WATER, WATER_GRASS,
		GRASS_ROCK, ROCK_GRASS,
		ROCK_WATER, WATER_ROCK,
		ROCK_LAVA, LAVA_ROCK,
		DEFAULT
	}
	
	private MapSegment.MAPTYPES type = MAPTYPES.DEFAULT;
	private TextureRegion texReg = null;
	private boolean isTransitional = false;

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
	
	public boolean isTransitional() {
		return isTransitional;
	}
}
