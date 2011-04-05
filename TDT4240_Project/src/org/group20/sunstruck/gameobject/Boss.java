package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Boss extends GameObject {

	public Boss() {
		super(Game.textureAtlas.findRegion("shipColossal"), TYPES.ENEMY,
				BEHAVIOR.LINE, false, true, new Laser(), 5, 20, 5, 0, 10, 4,
				200, 3, 9, 10);
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		shield -= impactDamage;
		if (shield < 0) {
			hull += shield;
			shield = 0;
			if (hull < 0)
				dispose();
		}
	}
}
