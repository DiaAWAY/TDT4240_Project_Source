package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.physics.box2d.WorldManifold;

public class SmallKamikazeEnemy extends GameObject {

	public SmallKamikazeEnemy() {
		super(Game.textureAtlas.findRegion("shipSmall3"), TYPES.ENEMY,
				BEHAVIOR.KAMIKAZE_VEL, false, true, new Laser(), 1, 20, 5, 0,
				10, 1, 200, 3, 9, 1);
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		dispose();
	}

}
