package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;


public class MediumKamikazeShip extends GameObject {

	public MediumKamikazeShip() {
		super(Game.textureAtlas.findRegion("shipSmall2"), 0.7f);
		BURST_COUNT = 0;
		behavior = BEHAVIOR.KAMIKAZE_FOR;
		impactDamage = 12;
		shield = 0;
	}

}
