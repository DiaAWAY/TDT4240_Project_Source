package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Enemy1 extends GameObject {


	@Override
	public void dispose() {
		if (!isDisposed) {
			Game.getInstance().getDestroyedBodiesList().add(this.body);
			isDisposed = true;
		}
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
