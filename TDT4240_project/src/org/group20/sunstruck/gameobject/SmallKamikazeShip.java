package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SmallKamikazeShip extends GameObject {
	public static TextureRegion shipTexture = Game.textureAtlas
			.findRegion("shipSmall3");

	public SmallKamikazeShip() {
		super(shipTexture, 0.5f);
		BURST_COUNT = 0;
		PAUSE_COUNT = 9;
		behavior = BEHAVIOR.KAMIKAZE_VEL;
		weaponType = new LaserTiny2();
		shield = 0;
		currentShield = 0;
	}
	@Override
	public void update() {
		Behavior.applyBehavior(this);
		shieldRegeneration();
		long time = 0;

		if (isExploding)
			time = System.currentTimeMillis() - startExplosionTime;
		if (time > explosionTime) {
			explode();
			startExplosionTime = System.currentTimeMillis();
		} else 
			
		if (weaponType != null)
				time = System.currentTimeMillis() - start;
				if (time > reloadTime) {
					shoot();
					start = System.currentTimeMillis();
				}
	}

}
