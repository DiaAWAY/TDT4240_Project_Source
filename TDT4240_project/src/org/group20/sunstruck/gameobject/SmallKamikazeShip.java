package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SmallKamikazeShip extends GameObject {
	public static TextureRegion shipTexture = Game.textureAtlas.findRegion("shipSmall3");

	public SmallKamikazeShip() {
		super(shipTexture, 0.5f);
		BURST_COUNT = 0;
		behavior = BEHAVIOR.KAMIKAZE_VEL;

		shield = 0;
		currentShield = 0;
	}

}
