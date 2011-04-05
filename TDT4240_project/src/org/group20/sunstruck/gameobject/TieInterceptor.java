package org.group20.sunstruck.gameobject;

import com.badlogic.gdx.physics.box2d.WorldManifold;

public class TieInterceptor extends GameObject {

	@Override
	public void update() {
		if (Math.random() > 0.95)
			shoot();
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		if (shield > 0)
			shield -= impactDamage;
		if (shield < 0) {
			hull -= impactDamage - shield;
			shield = 0;
		}
		if (shield == 0)
			hull -= impactDamage;
		if (hull < 0)
			dispose();
	}

	private void shoot() {

	}

}
