package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Laser extends GameObject {
	public boolean isBomb = false;

	@Override
	public void dispose() {
		if (!isDisposed) {
			Game.getInstance().getDestroyedBodiesList().add(this.body);
			isDisposed = true;
		}
	}

	@Override
	public String toString() {
		return "Shot";
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		dispose();

	}

}