package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MediumKamikazeShip extends GameObject {
	public static TextureRegion shipTexture = Game.textureAtlas
			.findRegion("shipSmall2");

	public MediumKamikazeShip() {
		super(shipTexture, 0.7f);
		BURST_COUNT = 2;
		behavior = BEHAVIOR.KAMIKAZE_VEL;
		impactDamage = 12;
		hull = 5;
		currentHull = hull;
		shield = 0;
		currentShield = shield;
	}

}
