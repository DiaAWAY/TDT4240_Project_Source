package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Contact;

public class LaserTiny1 extends GameObject {
	public static TextureRegion laserTexture = Game.textureAtlas
			.findRegion("laserTiny1");

	public LaserTiny1() {
		super(laserTexture, 0.3f);
		isProjectile = true;
		speed = 15;
		impactDamage = 15;
		type = TYPES.BULLET;
		density = 1;
		score = 0;

	}

	@Override
	public void contact(Contact contact, float impactDamage) {
		dispose();

	}

}