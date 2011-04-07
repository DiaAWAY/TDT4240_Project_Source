package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;


public class SmallKamikazeShip extends GameObject {

	public SmallKamikazeShip() {
		super(Game.textureAtlas.findRegion("shipSmall3"), 0.5f);
		BURST_COUNT = 0;
		behavior = BEHAVIOR.KAMIKAZE_VEL;
		
		shield = 0;
		currentShield = 0;
	}

}
