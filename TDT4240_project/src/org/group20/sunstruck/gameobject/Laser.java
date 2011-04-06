package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.physics.box2d.Contact;

public class Laser extends GameObject {

	public Laser() {
		super(Game.textureAtlas.findRegion("redLaser"), TYPES.BULLET,
				BEHAVIOR.LINE, null, 15, 12, 0, 0, 0, 0,
				0, 0, 0, 1, 0);
		isProjectile = true;
		width = 0.5f;
		height = 0.1f;
	}

	public boolean isBomb = false;

	@Override
	public void contact(Contact contact, float impactDamage) {
		dispose();

	}

}