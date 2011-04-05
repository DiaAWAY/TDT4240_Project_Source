package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Enemy1 extends GameObject {
	private long reloadTime = 200;
	private long start;

	boolean shoot = true;
	private int shotCount = 0;

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

	@Override
	public void update() {
		Behavior.applyBehavior(this);
		long time = System.currentTimeMillis() - start;
		if (behavior != BEHAVIOR.KAMIKAZE_FOR)
			if (time > reloadTime) {
				shoot();
				start = System.currentTimeMillis();
			}
	}

	private void shoot() {
		if (shoot) {
			Game.getInstance().getGoFactory()
					.generateWeaponShot(weaponType, this);
			shotCount++;
			if (shotCount == 3) {
				shoot = false;
				shotCount *= 2;
			}

		} else {
			shotCount--;
			if (shotCount == 0)
				shoot = true;
		}

	}
}
