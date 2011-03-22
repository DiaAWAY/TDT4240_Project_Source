package org.group20.sunstruck.world.map.segments;

import com.badlogic.gdx.graphics.Texture;

public abstract class MapSegment {

	public static enum MAPTYPES {
		WINTER, JUNGLE, PLAINS, TUNDRA, WINTER_PLAINS, PLAINS_WINTER, WINTER_TUNDRA, TUNDRA_WINTER, JUNGLE_PLAINS, PLAINS_JUNGLE, TUNDRA_PLAINS, PLAINS_TUNDRA, DEFAULT
	}

	private MapSegment.MAPTYPES type = MAPTYPES.DEFAULT;
	private Texture texture = null;

	public Texture getTexture() {
		return texture;
	}

	public MAPTYPES getType() {
		return type;
	}

	public void setType(MapSegment.MAPTYPES t) {
		type = t;
	}
}
