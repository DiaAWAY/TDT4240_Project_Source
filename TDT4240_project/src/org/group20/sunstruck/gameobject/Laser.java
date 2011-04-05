package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Laser extends GameObject {

	public Laser() {
		super(Game.textureAtlas.findRegion("redLaser"), TYPES.BULLET,
				BEHAVIOR.LINEAR_MOVEMENT, true, true, null, 10, 10, 0, 0, 0, 0,
				0, 0, 0, 1, 0);
		width = 1;
		height = 0.1f;
	}

	public boolean isBomb = false;

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		dispose();

	}

}