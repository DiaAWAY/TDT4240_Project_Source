package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

public class SmallLaserShip extends GameObject {

	public SmallLaserShip() {
		super(Game.textureAtlas.findRegion("shipSmall1"), 0.5f);
		behavior = BEHAVIOR.LINE;
		hull = 5;
		currentHull = hull;
		shield = 5;
		currentShield = shield;
		weaponType = new LaserTiny2();
		speed = 1;
		reloadTime = 1500;
		PAUSE_COUNT = 0;
	}

}